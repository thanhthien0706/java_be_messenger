package com.messenger.java_be_web_messenger.dto.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    String token;
    private String type = "Bearer";
    private String name;
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(String token2, String name2, Collection<? extends GrantedAuthority> authorities) {
        this.token = token2;
        this.name = name2;
        this.roles = authorities;
    }
}
