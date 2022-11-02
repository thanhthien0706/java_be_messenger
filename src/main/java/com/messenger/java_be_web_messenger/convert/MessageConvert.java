package com.messenger.java_be_web_messenger.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.messenger.java_be_web_messenger.dto.MessageDTO;
import com.messenger.java_be_web_messenger.entities.MessageEntity;

@Component
public class MessageConvert {

	@Autowired
	ModelMapper modelMapper;

	public MessageDTO toDto(MessageEntity entity) {
		MessageDTO dto = modelMapper.map(entity, MessageDTO.class);
		dto.setContent(entity.getContent());
		dto.setConversationId(entity.getConversation().getId());
		dto.setSenderId(entity.getSender().getId());
		dto.setType(entity.getType().toString());

		return dto;
	}
}
