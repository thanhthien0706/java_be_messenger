package com.messenger.java_be_web_messenger.dto;

import com.messenger.java_be_web_messenger.entities.BaseEntity;
import com.messenger.java_be_web_messenger.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotifiAddFriendDTO extends BaseEntity {
    private String description;
    private UserDTO receiver;
    private UserDTO requester;
}
