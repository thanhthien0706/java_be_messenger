package com.messenger.java_be_web_messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.java_be_web_messenger.convert.NotifiTextConvert;
import com.messenger.java_be_web_messenger.dto.NotifiTextDTO;
import com.messenger.java_be_web_messenger.entities.NotifiTextEntity;
import com.messenger.java_be_web_messenger.form.NotifiTextForm;
import com.messenger.java_be_web_messenger.repository.NotifiTextReposotory;
import com.messenger.java_be_web_messenger.service.INotifiText;

@Service
public class NotifiTextService implements INotifiText {

    @Autowired
    NotifiTextReposotory notifiTextReposotory;

    @Autowired
    NotifiTextConvert notifiTextConvert;

    @Autowired
    UserService userService;

    @Override
    public NotifiTextDTO save(NotifiTextForm notifiText) {
        NotifiTextEntity notifiTextEntity = notifiTextConvert.toEntity(notifiText);
        notifiTextEntity.setReceiver(userService.findOneById(notifiText.getReceiver_id()));

        NotifiTextEntity result = notifiTextReposotory.save(notifiTextEntity);

        return notifiTextConvert.toDTO(result);
    }

}
