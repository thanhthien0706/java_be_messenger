package com.messenger.java_be_web_messenger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO extends BaseDTO {
    private String username;
    private String fullName;
    private String avatar;
    private String email;
    private String phone;
    private String password;
    private Boolean active;
    private String roleName;
}
