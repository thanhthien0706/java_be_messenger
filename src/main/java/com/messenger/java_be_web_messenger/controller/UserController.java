package com.messenger.java_be_web_messenger.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.messenger.java_be_web_messenger.dto.UserDTO;
import com.messenger.java_be_web_messenger.form.ResponseObject;
import com.messenger.java_be_web_messenger.jwt.JwtProvider;
import com.messenger.java_be_web_messenger.jwt.JwtTokenFilter;
import com.messenger.java_be_web_messenger.service.impl.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @GetMapping("/me")
    private ResponseEntity<ResponseObject> getMe(HttpServletRequest req) {
        Boolean status = true;
        String text = "Get user information successfully";
        UserDTO infoUser;

        try {
            Long id_requester = jwtProvider.getUserIdFromToken(jwtTokenFilter.getToken(req));
            if (id_requester != null) {
                infoUser = userService.getMe(id_requester);
                if (infoUser == null) {
                    status = false;
                    text = "User not found";
                }
            } else {
                infoUser = null;
                status = false;
                text = "Not foud jwt";
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(status, text, infoUser));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(false, "", null));

    }
}
