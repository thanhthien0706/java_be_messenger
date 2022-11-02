package com.messenger.java_be_web_messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.java_be_web_messenger.convert.FriendConvert;
import com.messenger.java_be_web_messenger.dto.FriendDTO;
import com.messenger.java_be_web_messenger.entities.FriendEntity;
import com.messenger.java_be_web_messenger.form.FriendForm;
import com.messenger.java_be_web_messenger.repository.FriendRepository;
import com.messenger.java_be_web_messenger.repository.UserRepository;
import com.messenger.java_be_web_messenger.service.IFriendService;

@Service
public class FriendService implements IFriendService {

	@Autowired
	FriendRepository friendRepository;

	@Autowired
	FriendConvert friendConvert;

	@Autowired
	UserRepository userRepository;

	@Override
	public FriendDTO addFriend(FriendForm friendForm) {
		FriendEntity friendEntity = friendConvert.toEntity(friendForm);
		friendEntity.setMe(userRepository.findOneById(friendForm.getMe_id()));
		friendEntity.setFriend(userRepository.findOneById(friendForm.getFriend_id()));
		friendEntity.setBlocked(false);

		friendEntity = friendRepository.save(friendEntity);

		if (friendEntity != null) {
			return friendConvert.toDto(friendEntity);
		}

		return null;
	}

}
