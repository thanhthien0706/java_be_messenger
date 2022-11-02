package com.messenger.java_be_web_messenger.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationDTO2 extends BaseDTO {
	private String title;
	private Long creatorId;
	private String channelId;
	private String avatar;
	private String decription;
	private boolean isBlocked;
	private List<MessageDTO> messages;
}
