package com.messenger.java_be_web_messenger.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {
    private String name;
    private String username;
    private String email;
    private String password;
    private String roles;
}
