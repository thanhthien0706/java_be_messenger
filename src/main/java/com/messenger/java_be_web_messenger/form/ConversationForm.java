package com.messenger.java_be_web_messenger.form;

import com.messenger.java_be_web_messenger.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationForm {
	private String title;
	private Long idCreator;
	private String avatar;
	private String decription;
}
