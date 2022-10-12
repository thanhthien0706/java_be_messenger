package com.messenger.java_be_web_messenger.service.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.java_be_web_messenger.convert.NotifiAddFriendConvert;
import com.messenger.java_be_web_messenger.dto.NotifiAddFriendDTO;
import com.messenger.java_be_web_messenger.entities.NotifiAddfriendEnity;
import com.messenger.java_be_web_messenger.entities.UserEntity;
import com.messenger.java_be_web_messenger.form.NotifiAddFriendForm;
import com.messenger.java_be_web_messenger.repository.NotifiAddFriendRepository;
import com.messenger.java_be_web_messenger.service.INotifiAddFriend;

@Service
public class NotifiAddFriendService implements INotifiAddFriend {

    @Autowired
    NotifiAddFriendRepository notifiAddFriendRepository;

    @Autowired
    NotifiAddFriendConvert notifiAddFriendConvert;

    @Autowired
    UserService userService;

    @Override
    public NotifiAddFriendDTO save(NotifiAddFriendForm notifiForm) {
        Boolean check = true;
        BigInteger resultCheck = notifiAddFriendRepository.checkExistNotifiAddFriend(notifiForm.getReceiver_id(),
                notifiForm.getRequester_id());
        if (BigInteger.valueOf(0).compareTo(resultCheck) < 0) {
            check = false;
        }

        if (check) {
            NotifiAddfriendEnity notifiAddfriendEnity = notifiAddFriendConvert.toEntity(notifiForm);

            notifiAddfriendEnity.setReceiver(userService.findById(notifiForm.getReceiver_id())
                    .orElseThrow(() -> new RuntimeException("User not found")));
            notifiAddfriendEnity.setRequester(userService.findById(notifiForm.getRequester_id())
                    .orElseThrow(() -> new RuntimeException("User not found")));

            NotifiAddfriendEnity resultSave = notifiAddFriendRepository.save(notifiAddfriendEnity);

            if (notifiAddFriendRepository != null) {
                return notifiAddFriendConvert.toDTO(resultSave);
            }
        }

        return null;
    }
}
