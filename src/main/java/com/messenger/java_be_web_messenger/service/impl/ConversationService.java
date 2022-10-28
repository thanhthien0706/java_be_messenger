package com.messenger.java_be_web_messenger.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.java_be_web_messenger.convert.ConversationConvert;
import com.messenger.java_be_web_messenger.dto.ConversationDTO;
import com.messenger.java_be_web_messenger.entities.ConversationEntity;
import com.messenger.java_be_web_messenger.form.ConversationForm;
import com.messenger.java_be_web_messenger.repository.ConversationRepository;
import com.messenger.java_be_web_messenger.repository.UserRepository;
import com.messenger.java_be_web_messenger.service.IConversationService;

@Service
public class ConversationService implements IConversationService {

	@Autowired
	private ConversationConvert conversationConvert;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConversationRepository conversationRepository;

	@Override
	public ConversationDTO createConversation(ConversationForm conversationForm) {
		ConversationEntity conversationEntity = conversationConvert.toEntity(conversationForm);
		conversationEntity.setUserCreator(userRepository.findOneById(conversationForm.getIdCreator()));
		conversationEntity.setIsBlocked(false);

		String uniqueID = UUID.randomUUID().toString();
		while (conversationRepository.existsByChannelId(uniqueID)) {
			uniqueID = UUID.randomUUID().toString();
		}

		conversationEntity.setChannelId(uniqueID);

		ConversationEntity resultSave = conversationRepository.save(conversationEntity);

		if (resultSave != null) {
			ConversationDTO conversationDTO = conversationConvert.toDto(resultSave);
			if (conversationDTO != null) {
				return conversationDTO;
			}
		}

		return null;
	}

}
