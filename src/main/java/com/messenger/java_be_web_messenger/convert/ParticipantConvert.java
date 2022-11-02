package com.messenger.java_be_web_messenger.convert;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.messenger.java_be_web_messenger.dto.ParticipaintDTO;
import com.messenger.java_be_web_messenger.dto.ParticipantDTO2;
import com.messenger.java_be_web_messenger.entities.ConversationEntity;
import com.messenger.java_be_web_messenger.entities.ParticipantEntity;
import com.messenger.java_be_web_messenger.form.ParticipantForm;

@Component
public class ParticipantConvert {
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserConvert userConvert;

	@Autowired
	ConversationConvert conversationConvert;

	public ParticipantEntity toEntity(ParticipantForm participantForm) {
		ParticipantEntity participantEntity = new ParticipantEntity();
		return participantEntity;
	}

	public ParticipaintDTO toDto(ParticipantEntity participantEntity) {
		ParticipaintDTO participaintDTO = modelMapper.map(participantEntity, ParticipaintDTO.class);
		participaintDTO.setConversation(conversationConvert.toDto(participantEntity.getConversation()));
		participaintDTO.setUser(userConvert.toDTOUser(participantEntity.getUser()));

		return participaintDTO;
	}

	public List<ParticipantDTO2> toListDtoParticipant2(List<ParticipantEntity> participants) {
		List<ParticipantDTO2> listDto = new ArrayList<>();
		for (ParticipantEntity entity : participants) {
			listDto.add(new ParticipantDTO2(conversationConvert.toDto2(entity.getConversation()), entity.getType()));
		}
		return listDto;
	}
}
