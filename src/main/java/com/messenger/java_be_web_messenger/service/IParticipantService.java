package com.messenger.java_be_web_messenger.service;

import com.messenger.java_be_web_messenger.dto.ParticipaintDTO;
import com.messenger.java_be_web_messenger.form.ParticipantForm;

public interface IParticipantService {
	ParticipaintDTO createParticipant(ParticipantForm participantForm);
}
