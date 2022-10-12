package com.messenger.java_be_web_messenger.service;

import com.messenger.java_be_web_messenger.dto.NotifiTextDTO;
import com.messenger.java_be_web_messenger.entities.NotifiTextEntity;
import com.messenger.java_be_web_messenger.form.NotifiTextForm;

public interface INotifiText {
    NotifiTextDTO save(NotifiTextForm notifiText);
}
