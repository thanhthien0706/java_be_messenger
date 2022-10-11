package com.messenger.java_be_web_messenger.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.messenger.java_be_web_messenger.convert.UserConvert;
import com.messenger.java_be_web_messenger.dto.UserDTO;
import com.messenger.java_be_web_messenger.entities.NotifiAddfriendEnity;
import com.messenger.java_be_web_messenger.entities.UserEntity;
import com.messenger.java_be_web_messenger.form.NotifiAddFriendForm;
import com.messenger.java_be_web_messenger.form.ResponseObject;
import com.messenger.java_be_web_messenger.jwt.JwtProvider;
import com.messenger.java_be_web_messenger.jwt.JwtTokenFilter;
import com.messenger.java_be_web_messenger.service.impl.NotifiAddFriendService;
import com.messenger.java_be_web_messenger.service.impl.NotifiTextService;
import com.messenger.java_be_web_messenger.service.impl.UserService;

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

    @GetMapping
    private ResponseEntity<ResponseObject> searchUserByEmailAndPhone(
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phone", required = false) String phone) {
        List<UserDTO> listUser = userService.searchUsers(email, phone);

        return ResponseEntity.ok(new ResponseObject(true, "Search User ", listUser));
    }

    @PostMapping("/add-friend")
    private ResponseEntity<ResponseObject> handleAddFriend(HttpServletRequest req,
            @RequestAttribute NotifiAddFriendForm addfriendForm) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            if (userService.existsById(addfriendForm.getReceiver_id())) {
                Long id_requester = jwtProvider.getUserIdFromToken(jwtTokenFilter.getToken(req));
                addfriendForm.setRequester_id(id_requester);
            }
        }
        return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
                .body(new ResponseObject(false, "User is not logged in", ""));
    }
}