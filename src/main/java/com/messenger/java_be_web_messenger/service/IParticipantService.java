package com.messenger.java_be_web_messenger.service;

import java.util.List;

import com.messenger.java_be_web_messenger.dto.ParticipaintDTO;
import com.messenger.java_be_web_messenger.dto.ParticipantDTO2;
import com.messenger.java_be_web_messenger.form.ParticipantForm;

public interface IParticipantService {
	ParticipaintDTO createParticipant(ParticipantForm participantForm);

	List<ParticipantDTO2> getListParticipantDto2Me(Long meId);
}
