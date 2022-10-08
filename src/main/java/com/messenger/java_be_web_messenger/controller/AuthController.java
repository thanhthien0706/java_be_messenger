package com.messenger.java_be_web_messenger.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messenger.java_be_web_messenger.dto.request.SignUpForm;
import com.messenger.java_be_web_messenger.dto.response.ResponseMessage;
import com.messenger.java_be_web_messenger.entities.RoleEntity;
import com.messenger.java_be_web_messenger.entities.UserEntity;
import com.messenger.java_be_web_messenger.jwt.JwtProvider;
import com.messenger.java_be_web_messenger.service.impl.RoleService;
import com.messenger.java_be_web_messenger.service.impl.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {

        UserEntity user = new UserEntity(signUpForm.getUsername(), signUpForm.getEmail(),
                passwordEncoder.encode(signUpForm.getPassword()));

        RoleEntity role = roleService.findByName(RoleEntity.AUTHOR_USER)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("Create successfully!"), HttpStatus.OK);
    }

    // @PostMapping("/signin")
    // public ResponseEntity<?> login(@Valid @RequestBody SignUpForm signUpForm) {
    // Authentication authentication = authenticationManager.authenticate(
    // new UsernamePasswordAuthenticationToken(signUpForm.getUsername(),
    // signUpForm.getPassword()));

    // SecurityContextHolder.getContext().setAuthentication(authentication);
    // String token = jwtProvider.createToken(authentication);
    // UserPrinCiple userPrinCiple = (UserPrinCiple) authentication.getPrincipal();
    // return ResponseEntity.ok(new JwtResponse(token, userPrinCiple.getName(),
    // userPrinCiple.getAuthorities()));
    // }
}
