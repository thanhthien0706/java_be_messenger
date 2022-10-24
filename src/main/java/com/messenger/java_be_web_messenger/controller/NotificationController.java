package com.messenger.java_be_web_messenger.controller;

import javax.mail.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.messenger.java_be_web_messenger.dto.MessageDTO;
import com.messenger.java_be_web_messenger.dto.UserDTO;
import com.messenger.java_be_web_messenger.form.NotificationForm;
import com.messenger.java_be_web_messenger.service.impl.NotificationService;
import com.messenger.java_be_web_messenger.service.impl.UserService;

@Controller
public class NotificationController {

    // @Autowired
    // private SimpMessagingTemplate simpMessagingTemplate;

    // @Autowired
    // private UserService userService;

    @Autowired
    NotificationService notificationService;

    @MessageMapping("/application")
    @SendTo("/all/messages")
    public MessageDTO send(final Message message) throws Exception {
        return null;
    }

    @MessageMapping("/private-notifi")
    public NotificationForm recPrivateNotification(@Payload NotificationForm notificationForm) {
        notificationService.testNotifi(notificationForm);
        return notificationForm;
    }
}