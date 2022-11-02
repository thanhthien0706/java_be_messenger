package com.messenger.java_be_web_messenger.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messenger.java_be_web_messenger.dto.ChatGroupDTO;
import com.messenger.java_be_web_messenger.form.ResponseObject;
import com.messenger.java_be_web_messenger.jwt.JwtProvider;
import com.messenger.java_be_web_messenger.jwt.JwtTokenFilter;
import com.messenger.java_be_web_messenger.service.impl.ChatService;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	JwtTokenFilter jwtTokenFilter;

	@Autowired
	ChatService chatService;

	@GetMapping("/group-chat")
	private ResponseEntity<ResponseObject> getListChatMe(HttpServletRequest req) {
		try {
			Long meId = jwtProvider.getUserIdFromToken(jwtTokenFilter.getToken(req));
			ChatGroupDTO listChats = chatService.getGroupChatMe(meId);
			if (listChats != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(true, "Get list chat of me sucessfully", listChats));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseObject(false, "Get list chat of me fail", null));
	}
}
