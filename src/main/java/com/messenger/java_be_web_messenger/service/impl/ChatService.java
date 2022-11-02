package com.messenger.java_be_web_messenger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.java_be_web_messenger.convert.ChatConvert;
import com.messenger.java_be_web_messenger.dto.ChatGroupDTO;
import com.messenger.java_be_web_messenger.dto.ParticipantDTO2;
import com.messenger.java_be_web_messenger.service.IChatService;

@Service
public class ChatService implements IChatService {

	@Autowired
	ChatConvert chatConvert;

	@Autowired
	participantService participantService;

	@Override
	public ChatGroupDTO getGroupChatMe(Long meId) {
		List<ParticipantDTO2> participantDTO2 = participantService.getListParticipantDto2Me(meId);
		if (participantDTO2 != null) {
			return new ChatGroupDTO(meId, participantDTO2);
		}
		return null;
	}

}
