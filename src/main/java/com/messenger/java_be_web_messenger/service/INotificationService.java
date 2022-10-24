package com.messenger.java_be_web_messenger.service;

import java.util.List;

import org.springframework.messaging.handler.annotation.SendTo;

import com.messenger.java_be_web_messenger.dto.NotificationDTO;
import com.messenger.java_be_web_messenger.form.NotificationForm;

public interface INotificationService {

    boolean sendNotifiAllUserFromSystem(NotificationForm notificationForm);

    boolean sendNotifiSpecificUserFromSystem(NotificationForm notificationForm);

    List<String> getAllUserExceptMe(Long id_me);

    List<String> getAllUser();

    NotificationDTO testNotifi(NotificationForm notificationForm);

}
