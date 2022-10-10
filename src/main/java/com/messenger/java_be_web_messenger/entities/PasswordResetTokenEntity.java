package com.messenger.java_be_web_messenger.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "password_reset_token")
public class PasswordResetTokenEntity extends BaseEntity {

    private static final int EXPIRATION = 60 * 24;

    private String token;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;

    private Date expiryDate;

    public PasswordResetTokenEntity(String token2, UserEntity user2) {
        this.token = token2;
        this.user = user2;
        this.expiryDate = new Date(System.currentTimeMillis() + EXPIRATION);
    }
}
