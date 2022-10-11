package com.messenger.java_be_web_messenger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BaseDTO {
    private String username;
    private String fullName;
    private String avatar;
    private String email;
    private String phone;
    private String active;
    private String role_name;
}
