package com.messenger.java_be_web_messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messenger.java_be_web_messenger.dto.SignUpDTO;
import com.messenger.java_be_web_messenger.form.ResponseObject;
import com.messenger.java_be_web_messenger.form.SignUpForm;
import com.messenger.java_be_web_messenger.service.impl.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseObject> signUp(@ModelAttribute SignUpForm signUpForm) {
        SignUpDTO resule = userService.save(signUpForm);
        String text;

        if (resule == null) {
            text = "Account is exist. Check Username, Email and Phone";
        } else {
            text = "Account is created successfully";
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(true, text, resule));
    }
}
