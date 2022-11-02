package com.messenger.java_be_web_messenger.service;

import java.util.List;

import com.messenger.java_be_web_messenger.dto.FriendDTO;
import com.messenger.java_be_web_messenger.dto.NotifiAddFriendDTO;
import com.messenger.java_be_web_messenger.form.FriendForm;

public interface IFriendService {
	FriendDTO addFriend(FriendForm friendForm);

}
