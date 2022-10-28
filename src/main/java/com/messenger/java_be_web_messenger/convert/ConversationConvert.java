package com.messenger.java_be_web_messenger.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.messenger.java_be_web_messenger.dto.ConversationDTO;
import com.messenger.java_be_web_messenger.entities.ConversationEntity;
import com.messenger.java_be_web_messenger.form.ConversationForm;

@Component
public class ConversationConvert {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserConvert userConvert;

	public ConversationEntity toEntity(ConversationForm conversationForm) {
		ConversationEntity entity = modelMapper.map(conversationForm, ConversationEntity.class);
		return entity;
	}

	public ConversationDTO toDto(ConversationEntity conversationEntity) {
		ConversationDTO entDto = modelMapper.map(conversationEntity, ConversationDTO.class);
		entDto.setUserCreator(userConvert.toDTOUser(conversationEntity.getUserCreator()));
		return entDto;
	}
}
