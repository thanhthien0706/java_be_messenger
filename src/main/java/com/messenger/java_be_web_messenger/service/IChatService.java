package com.messenger.java_be_web_messenger.service;

import java.util.List;

import com.messenger.java_be_web_messenger.dto.ChatGroupDTO;

public interface IChatService {
	ChatGroupDTO getGroupChatMe(Long meId);
}
