package com.messenger.java_be_web_messenger.service;

import com.messenger.java_be_web_messenger.dto.ConversationDTO;
import com.messenger.java_be_web_messenger.form.ConversationForm;

public interface IConversationService {
	ConversationDTO createConversation(ConversationForm conversationForm);
}
