package com.messenger.java_be_web_messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.java_be_web_messenger.entities.NotifiAddfriendEnity;
import com.messenger.java_be_web_messenger.form.NotifiAddFriendForm;
import com.messenger.java_be_web_messenger.repository.NotifiAddFriendRepository;
import com.messenger.java_be_web_messenger.service.INotifiAddFriend;

@Service
public class NotifiAddFriendService implements INotifiAddFriend {

    @Autowired
    NotifiAddFriendRepository notifiAddFriendRepository;

    @Override
    public NotifiAddfriendEnity save(NotifiAddFriendForm notifiForm) {
        return notifiAddFriendRepository.save(notifi);
    }
}
