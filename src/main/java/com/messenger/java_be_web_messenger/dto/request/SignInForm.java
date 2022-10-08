package com.messenger.java_be_web_messenger.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInForm {
    private String username;
    private String password;
}
