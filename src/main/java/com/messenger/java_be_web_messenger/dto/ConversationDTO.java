package com.messenger.java_be_web_messenger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationDTO extends BaseDTO {
	private String title;
	private UserDTO userCreator;
	private String channelId;
	private String avatar;
	private String decription;
	private boolean isBlocked;
}
