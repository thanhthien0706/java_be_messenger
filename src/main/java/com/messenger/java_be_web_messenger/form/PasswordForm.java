package com.messenger.java_be_web_messenger.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordForm {
    private String oldPassword;

    private String token;

    private String newPassword;
}
