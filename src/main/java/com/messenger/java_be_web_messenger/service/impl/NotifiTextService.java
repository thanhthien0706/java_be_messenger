package com.messenger.java_be_web_messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.java_be_web_messenger.entities.NotifiTextEntity;
import com.messenger.java_be_web_messenger.repository.NotifiTextReposotory;
import com.messenger.java_be_web_messenger.service.INotifiText;

@Service
public class NotifiTextService implements INotifiText {

    @Autowired
    NotifiTextReposotory notifiTextReposotory;

    @Override
    public NotifiTextEntity save(NotifiTextEntity notifi) {
        return notifiTextReposotory.save(notifi);
    }
}
