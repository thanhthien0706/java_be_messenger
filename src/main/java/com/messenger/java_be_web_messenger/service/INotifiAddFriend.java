package com.messenger.java_be_web_messenger.service;

import java.util.List;

import com.messenger.java_be_web_messenger.dto.NotifiAddFriendDTO;
import com.messenger.java_be_web_messenger.entities.NotifiAddfriendEnity;
import com.messenger.java_be_web_messenger.form.NotifiAddFriendForm;

public interface INotifiAddFriend {
	NotifiAddFriendDTO save(NotifiAddFriendForm notifiForm);

	List<NotifiAddFriendDTO> getAllFriendOfMe(Long id);

	Boolean removeNotifiAddfriend(Long idReceiver, Long idRequest);
}
