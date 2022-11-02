package com.messenger.java_be_web_messenger.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.messenger.java_be_web_messenger.convert.UserConvert;
import com.messenger.java_be_web_messenger.dto.ConversationDTO;
import com.messenger.java_be_web_messenger.dto.FriendDTO;
import com.messenger.java_be_web_messenger.dto.NotifiAddFriendDTO;
import com.messenger.java_be_web_messenger.dto.NotifiTextDTO;
import com.messenger.java_be_web_messenger.dto.ParticipaintDTO;
import com.messenger.java_be_web_messenger.dto.UserDTO;
import com.messenger.java_be_web_messenger.entities.NotifiAddfriendEnity;
import com.messenger.java_be_web_messenger.entities.UserEntity;
import com.messenger.java_be_web_messenger.entities.enum_type.TypeGroup;
import com.messenger.java_be_web_messenger.form.ConversationForm;
import com.messenger.java_be_web_messenger.form.FriendForm;
import com.messenger.java_be_web_messenger.form.NotifiAddFriendForm;
import com.messenger.java_be_web_messenger.form.NotifiTextForm;
import com.messenger.java_be_web_messenger.form.NotificationForm;
import com.messenger.java_be_web_messenger.form.ParticipantForm;
import com.messenger.java_be_web_messenger.form.ResponseObject;
import com.messenger.java_be_web_messenger.jwt.JwtProvider;
import com.messenger.java_be_web_messenger.jwt.JwtTokenFilter;
import com.messenger.java_be_web_messenger.service.impl.ConversationService;
import com.messenger.java_be_web_messenger.service.impl.FriendService;
import com.messenger.java_be_web_messenger.service.impl.NotifiAddFriendService;
import com.messenger.java_be_web_messenger.service.impl.NotifiTextService;
import com.messenger.java_be_web_messenger.service.impl.NotificationService;
import com.messenger.java_be_web_messenger.service.impl.UserService;
import com.messenger.java_be_web_messenger.service.impl.participantService;

@RestController
@RequestMapping("/api/v1/friend")
public class FriendController {

	@Autowired
	UserService userService;

	@Autowired
	UserConvert userConvert;

	@Autowired
	NotifiAddFriendService notifiAddFriendService;

	@Autowired
	NotifiTextService notifiTextService;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	JwtTokenFilter jwtTokenFilter;

	@Autowired
	NotificationService notificationService;

	@Autowired
	ConversationService conversationService;

	@Autowired
	participantService participantService;

	@Autowired
	FriendService friendService;

	@GetMapping
	private ResponseEntity<ResponseObject> searchUser(HttpServletRequest req,
			@RequestParam(name = "text_search", required = false) String text_search) {
		Long id_requester = jwtProvider.getUserIdFromToken(jwtTokenFilter.getToken(req));
		System.out.println("Nguoi gui:" + id_requester.toString());
		List<UserDTO> listUser = userService.searchUsers(text_search, id_requester);
		Boolean status = listUser != null ? true : false;

		return ResponseEntity.ok(new ResponseObject(status, "Search User ", listUser));
	}

	@PostMapping("/add-friend")
	private ResponseEntity<ResponseObject> handleAddFriend(HttpServletRequest req,
			@RequestBody NotifiAddFriendForm addfriendForm) {
		if (userService.existsById(addfriendForm.getReceiver_id())) {
			Long id_requester = jwtProvider.getUserIdFromToken(jwtTokenFilter.getToken(req));
			UserEntity userEntity = userService.findById(id_requester)
					.orElseThrow(() -> new RuntimeException("User not found"));

			addfriendForm.setRequester_id(id_requester);

			addfriendForm
					.setDescription("Xin chào, tôi là " + userEntity.getFullName() + " muốn được kết bạn với bạn!");

			if (id_requester != null) {
				NotifiAddFriendDTO resultNotifiAddFriend = notifiAddFriendService.save(addfriendForm);

				if (resultNotifiAddFriend != null) {
					NotifiTextForm textFrom = new NotifiTextForm(userEntity.getFullName() + " gui loi moi ket ban",
							addfriendForm.getReceiver_id());
					NotifiTextDTO rsNotifiTextDto = notifiTextService.save(textFrom);

					if (rsNotifiTextDto != null) {
						NotificationForm notificationForm = new NotificationForm(addfriendForm.getReceiver_id(),
								id_requester, null, userEntity.getFullName() + " gui loi moi ket ban", null);
						notificationService.sendNotifiSpecificUserFromSystem(notificationForm);
						return ResponseEntity.status(HttpStatus.OK)
								.body(new ResponseObject(true, "Addfriend sucessfully", ""));
					}

				}
			}

		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(false, "Addfriend Faild", ""));
	}

	@GetMapping("/list-addfriend")
	private ResponseEntity<ResponseObject> getListFriendOfMe(HttpServletRequest req) {
		try {
			Long id_user = jwtProvider.getUserIdFromToken(jwtTokenFilter.getToken(req));
			List<NotifiAddFriendDTO> result = notifiAddFriendService.getAllFriendOfMe(id_user);

			if (result != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(true, "Get list friend successfully", result));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(false, "Get list friend faild", ""));

	}

	@PostMapping("/response-addfriend")
	private ResponseEntity<ResponseObject> handleResponseAddFriend(HttpServletRequest req,
			@RequestParam("id-friend") Long idFriend, @RequestParam("status") Boolean status) {
		try {
			Long id_user = jwtProvider.getUserIdFromToken(jwtTokenFilter.getToken(req));
			if (id_user != null) {
				boolean resultDelete = notifiAddFriendService.removeNotifiAddfriend(id_user, idFriend);
				if (!status) {
					if (resultDelete) {
						return ResponseEntity.status(HttpStatus.OK)
								.body(new ResponseObject(true, "Delete notification successfully", ""));
					}
				}

				System.out.println("Xoa thong bao ket ban" + resultDelete);

				// chap nhan ket ban
				ConversationForm conversationForm = new ConversationForm(null, id_user, null, null);
				ConversationDTO conversationDTO = conversationService.createConversation(conversationForm);
				if (conversationDTO != null) {

					Long[] listUser = { id_user, idFriend };
					boolean checkStatus = true;
					for (Long id : listUser) {
						ParticipantForm participantForm = new ParticipantForm(conversationDTO.getId(), id);
						ParticipaintDTO participaintDTO = participantService.createParticipant(participantForm);

						if (participaintDTO == null) {
							checkStatus = false;
						}
					}

					FriendDTO dto1 = friendService.addFriend(new FriendForm(id_user, idFriend));
					FriendDTO dto2 = friendService.addFriend(new FriendForm(idFriend, id_user));

					if (dto1 == null || dto2 == null) {
						checkStatus = false;
					}

					if (checkStatus) {
						return ResponseEntity.status(HttpStatus.OK)
								.body(new ResponseObject(true, "Create chat successfully", conversationDTO));
					}

				}

			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(new ResponseObject(false, "User not login", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject(false, "Handle addfriend false", ""));
	}

}