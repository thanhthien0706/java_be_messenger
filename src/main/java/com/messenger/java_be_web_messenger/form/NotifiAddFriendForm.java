package com.messenger.java_be_web_messenger.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotifiAddFriendForm {
    private String description;
    private Long receiver_id;
    private Long requester_id;
}
