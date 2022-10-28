package com.messenger.java_be_web_messenger.form;

import com.messenger.java_be_web_messenger.entities.enum_type.TypeGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantForm {
	private Long conversationId;
	private Long userId;
	private TypeGroup type;
}
