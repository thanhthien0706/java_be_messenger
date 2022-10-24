package com.messenger.java_be_web_messenger.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationForm {
    private Long receiverId;
    private String thumbnail;
    private String message;
    private String date;
}
