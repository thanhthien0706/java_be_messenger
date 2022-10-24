package com.messenger.java_be_web_messenger.dto;

import java.util.Date;

import com.messenger.java_be_web_messenger.dto.EnumTypes.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationDTO {
    private String username;
    private String content;
    private String thumbnail;
    private Status status;
    private String date;
}
