package com.messenger.java_be_web_messenger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO extends BaseDTO {
	private Long conversationId;
	private Long senderId;
	private String content;
	private String type;
}
