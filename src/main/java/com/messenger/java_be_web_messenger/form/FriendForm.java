package com.messenger.java_be_web_messenger.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendForm {
	private Long me_id;
	private Long friend_id;
}
