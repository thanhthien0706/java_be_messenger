package com.messenger.java_be_web_messenger.dto;

import com.messenger.java_be_web_messenger.entities.enum_type.TypeGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO2 extends BaseDTO {
	private ConversationDTO2 conversation;
	private TypeGroup type;
}
