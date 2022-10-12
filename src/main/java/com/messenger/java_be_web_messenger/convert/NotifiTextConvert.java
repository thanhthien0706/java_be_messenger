package com.messenger.java_be_web_messenger.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.messenger.java_be_web_messenger.dto.NotifiTextDTO;
import com.messenger.java_be_web_messenger.entities.NotifiTextEntity;
import com.messenger.java_be_web_messenger.form.NotifiTextForm;

@Component
public class NotifiTextConvert {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserConvert userConvert;

    public NotifiTextEntity toEntity(NotifiTextForm notifiText) {
        return modelMapper.map(notifiText, NotifiTextEntity.class);
    };

    public NotifiTextDTO toDTO(NotifiTextEntity notifiTextEntity) {
        NotifiTextDTO dto = modelMapper.map(notifiTextEntity, NotifiTextDTO.class);
        dto.setReceiver(userConvert.toDTOUser(notifiTextEntity.getReceiver()));
        return dto;
    }

}
