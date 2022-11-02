package com.messenger.java_be_web_messenger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendDTO extends BaseDTO {
	private UserDTO me;
	private UserDTO friend;
	private boolean isBlocked;
}
