package com.messenger.java_be_web_messenger.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.messenger.java_be_web_messenger.dto.SignUpDTO;
import com.messenger.java_be_web_messenger.entities.UserEntity;
import com.messenger.java_be_web_messenger.form.PasswordForm;
import com.messenger.java_be_web_messenger.form.ResponseObject;
import com.messenger.java_be_web_messenger.form.SignInForm;
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
        Boolean status;

        if (resule == null) {
            text = "Account is exist. Check Username, Email and Phone";
            status = false;
        } else {
            text = "Account is created successfully";
            status = true;
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(status, text, resule));
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseObject> signIn(@RequestBody SignInForm signInForm) {
        String token = userService.signIn(signInForm);
        String text;
        Boolean status;

        if (token == null) {
            text = "Login Failed";
            status = false;
        } else {
            text = "Login successfully";
            status = true;
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(status, text, token));
    }

    @PostMapping("/reset-password")
    private ResponseEntity<ResponseObject> resetPassword(HttpServletRequest request,
            @RequestParam("email") String userEmail) {
        UserEntity user = userService.findOneByEmail(userEmail);
        String text;
        Boolean status;
        Boolean statusSendMail;

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        String token = UUID.randomUUID().toString();
        Boolean createTokenReset = userService.createPasswordResetTokenForUser(user, token);

        if (createTokenReset) {
            String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build()
                    .toUriString();
            statusSendMail = userService.structSendMailResetPassword(baseUrl, token, user);

            if (statusSendMail) {
                text = "Send mail reset password successfully";
                status = true;
            } else {
                text = "Send mail reset password failed";
                status = false;
            }
        } else {
            text = "Send mail reset password failed";
            status = false;
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(status, text, ""));

    }

    @GetMapping("/changePassword")
    private ResponseEntity<ResponseObject> changePassword(@RequestParam("token") String token) {
        String result = userService.validatePasswordResetToken(token);
        Boolean status;
        String text;
        if (result != null) {
            status = false;
            text = "Not reset password";
        } else {
            status = true;
            text = "Maybe reset password";
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(status, text, ""));
    }

    @PostMapping("/savePassword")
    private ResponseEntity<ResponseObject> savePassword(@Valid @RequestBody PasswordForm passwordForm) {
        String result = userService.validatePasswordResetToken(passwordForm.getToken());

        if (result != null) {
        }

        UserEntity user = userService.getUserByPasswordResetToken(passwordForm.getToken());

        if (user != null) {
            Boolean resultChangePassword = userService.changeUserPassword(user, passwordForm.getNewPassword());
            if (resultChangePassword) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(true, "Change password successfully", ""));

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject(false, "Change password failed ", ""));

            }
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "User not found", ""));
        }

    }
}
