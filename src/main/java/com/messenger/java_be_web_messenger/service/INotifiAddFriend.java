package com.messenger.java_be_web_messenger.service;

import com.messenger.java_be_web_messenger.entities.NotifiAddfriendEnity;
import com.messenger.java_be_web_messenger.form.NotifiAddFriendForm;

public interface INotifiAddFriend {
    NotifiAddfriendEnity save(NotifiAddFriendForm notifiForm);
}
