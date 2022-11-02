package com.messenger.java_be_web_messenger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.java_be_web_messenger.convert.ParticipantConvert;
import com.messenger.java_be_web_messenger.dto.ParticipaintDTO;
import com.messenger.java_be_web_messenger.dto.ParticipantDTO2;
import com.messenger.java_be_web_messenger.entities.ParticipantEntity;
import com.messenger.java_be_web_messenger.entities.enum_type.TypeGroup;
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
		if (!checkExist) {
			ParticipantEntity participantEntity = participantConvert.toEntity(participantForm);
			participantEntity.setType(TypeGroup.SINGLE);
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

	@Override
	public List<ParticipantDTO2> getListParticipantDto2Me(Long meId) {
		List<ParticipantEntity> participants = participantRepository.findByUserId(meId);
		if (participants != null) {
			return participantConvert.toListDtoParticipant2(participants);
		}
		return null;
	}

}
