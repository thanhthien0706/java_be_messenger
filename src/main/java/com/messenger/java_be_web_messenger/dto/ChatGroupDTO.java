package com.messenger.java_be_web_messenger.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGroupDTO extends BaseDTO {
	private Long meId;
	private List<ParticipantDTO2> listGroupChat;
}
