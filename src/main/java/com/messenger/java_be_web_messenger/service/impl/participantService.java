package com.messenger.java_be_web_messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.java_be_web_messenger.convert.ParticipantConvert;
import com.messenger.java_be_web_messenger.dto.ParticipaintDTO;
import com.messenger.java_be_web_messenger.entities.ParticipantEntity;
import com.messenger.java_be_web_messenger.form.ParticipantForm;
import com.messenger.java_be_web_messenger.repository.ConversationRepository;
import com.messenger.java_be_web_messenger.repository.ParticipantRepository;
import com.messenger.java_be_web_messenger.repository.UserRepository;
import com.messenger.java_be_web_messenger.service.IParticipantService;

@Service
public class participantService implements IParticipantService {

	@Autowired
	ParticipantRepository participantRepository;

	@Autowired
	ParticipantConvert participantConvert;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ConversationRepository conversationRepository;

	@Override
	public ParticipaintDTO createParticipant(ParticipantForm participantForm) {
		boolean checkExist = participantRepository.existsByConversationIdAndUserId(participantForm.getConversationId(),
				participantForm.getUserId());
		System.out.println("Id can tim" + participantForm.getConversationId());
		if (!checkExist) {
			ParticipantEntity participantEntity = participantConvert.toEntity(participantForm);
			participantEntity.setConversation(conversationRepository.findOneById(participantForm.getConversationId()));
			participantEntity.setUser(userRepository.findOneById(participantForm.getUserId()));

			participantEntity = participantRepository.save(participantEntity);

			if (participantEntity != null) {
				ParticipaintDTO dto = participantConvert.toDto(participantEntity);
				return dto;
			}
		}
		return null;
	}

}
