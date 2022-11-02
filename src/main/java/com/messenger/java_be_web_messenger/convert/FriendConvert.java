package com.messenger.java_be_web_messenger.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.messenger.java_be_web_messenger.dto.FriendDTO;
import com.messenger.java_be_web_messenger.entities.FriendEntity;
import com.messenger.java_be_web_messenger.form.FriendForm;

@Component
public class FriendConvert {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserConvert userConvert;

	public FriendEntity toEntity(FriendForm friendForm) {
		return modelMapper.map(friendForm, FriendEntity.class);
	}

	public FriendDTO toDto(FriendEntity friendEntity) {
		FriendDTO dto = modelMapper.map(friendEntity, FriendDTO.class);
		dto.setMe(userConvert.toDTOUser(friendEntity.getMe()));
		dto.setFriend(userConvert.toDTOUser(friendEntity.getFriend()));

		return dto;
	}
}
