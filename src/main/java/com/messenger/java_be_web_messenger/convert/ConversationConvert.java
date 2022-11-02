package com.messenger.java_be_web_messenger.convert;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.messenger.java_be_web_messenger.dto.ConversationDTO;
import com.messenger.java_be_web_messenger.dto.ConversationDTO2;
import com.messenger.java_be_web_messenger.dto.MessageDTO;
import com.messenger.java_be_web_messenger.entities.ConversationEntity;
import com.messenger.java_be_web_messenger.entities.MessageEntity;
import com.messenger.java_be_web_messenger.form.ConversationForm;

@Component
public class ConversationConvert {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserConvert userConvert;

	@Autowired
	MessageConvert messageConvert;

	public ConversationEntity toEntity(ConversationForm conversationForm) {
		ConversationEntity entity = modelMapper.map(conversationForm, ConversationEntity.class);
		return entity;
	}

	public ConversationDTO toDto(ConversationEntity conversationEntity) {
		ConversationDTO entDto = modelMapper.map(conversationEntity, ConversationDTO.class);
		entDto.setUserCreator(userConvert.toDTOUser(conversationEntity.getUserCreator()));
		return entDto;
	}

	public ConversationDTO2 toDto2(ConversationEntity conversationEntity) {
		ConversationDTO2 dto2 = modelMapper.map(conversationEntity, ConversationDTO2.class);
		dto2.setAvatar(conversationEntity.getAvatar());
		dto2.setChannelId(conversationEntity.getChannelId());
		dto2.setDecription(conversationEntity.getDescription());
		dto2.setTitle(conversationEntity.getTitle());
		dto2.setCreatorId(conversationEntity.getUserCreator().getId());
		dto2.setBlocked(conversationEntity.getIsBlocked());

		List<MessageDTO> message = new ArrayList<>();
		for (MessageEntity messEntity : conversationEntity.getMessages()) {
			message.add(messageConvert.toDto(messEntity));
		}

		dto2.setMessages(message);

		return dto2;
	}
}
