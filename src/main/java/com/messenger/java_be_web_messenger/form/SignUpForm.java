package com.messenger.java_be_web_messenger.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    private String username;
    private String fullName;
    // MultipartFile
    private MultipartFile avatar;
    private String email;
    private String phone;
    private String password;
    private String roleName;
}
