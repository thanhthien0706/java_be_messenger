package com.messenger.java_be_web_messenger.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import com.messenger.java_be_web_messenger.dto.UserDTO;
import com.messenger.java_be_web_messenger.form.NotificationForm;
import com.messenger.java_be_web_messenger.service.INotificationService;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    SimpUserRegistry simpUserRegistry;

    @Autowired
    UserService userService;

    @Override
    public boolean sendNotifiAllUserFromSystem(NotificationForm notificationForm) {
        List<String> listUserOnl = getAllUser();
        listUserOnl
                .forEach(sub -> simpMessagingTemplate.convertAndSendToUser(sub, "/notifi-all/message",
                        notificationForm));
        return true;
    }

    @Override
    public List<String> getAllUserExceptMe(Long id_me) {
        UserDTO user = userService.getMe(id_me);

        List<String> subscribers = simpUserRegistry.getUsers().stream()
                .map(SimpUser::getName)
                .filter(name -> !user.getUsername().equals(name))
                .collect(Collectors.toList());
        return subscribers;
    }

    @Override
    public List<String> getAllUser() {
        List<String> subscribers = simpUserRegistry.getUsers().stream()
                .map(SimpUser::getName)
                .collect(Collectors.toList());

        return subscribers;
    }

    @Override
    public boolean sendNotifiSpecificUserFromSystem(NotificationForm notificationForm) {
        UserDTO user = userService.getMe(notificationForm.getReceiverId());
        System.out.println("Thong bao da duc gui");
        simpMessagingTemplate.convertAndSendToUser(user.getUsername(), "/notifi-specific",
                notificationForm);
        return true;
    }

    @Override
    public NotificationForm testNotifi(NotificationForm notificationForm) {
        UserDTO user = userService.getMe(notificationForm.getReceiverId());
        simpMessagingTemplate.convertAndSendToUser(user.getUsername(), "/private-notifi",
                notificationForm);

        return notificationForm;
    }

}