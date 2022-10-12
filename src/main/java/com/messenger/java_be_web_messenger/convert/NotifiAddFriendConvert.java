package com.messenger.java_be_web_messenger.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.messenger.java_be_web_messenger.dto.NotifiAddFriendDTO;
import com.messenger.java_be_web_messenger.entities.NotifiAddfriendEnity;
import com.messenger.java_be_web_messenger.form.NotifiAddFriendForm;

@Component
public class NotifiAddFriendConvert {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserConvert userConvert;

    public NotifiAddfriendEnity toEntity(NotifiAddFriendForm addFriendForm) {
        return modelMapper.map(addFriendForm, NotifiAddfriendEnity.class);
    }

    public NotifiAddFriendDTO toDTO(NotifiAddfriendEnity notifiAddfriendEnity) {
        NotifiAddFriendDTO result = modelMapper.map(notifiAddfriendEnity, NotifiAddFriendDTO.class);
        result.setReceiver(userConvert.toDTOUser(notifiAddfriendEnity.getReceiver()));
        result.setRequester(userConvert.toDTOUser(notifiAddfriendEnity.getRequester()));

        return result;
    }

}
