package com.messenger.java_be_web_messenger.service;

import java.util.Optional;

import com.messenger.java_be_web_messenger.dto.SignUpDTO;
import com.messenger.java_be_web_messenger.entities.UserEntity;
import com.messenger.java_be_web_messenger.form.SignUpForm;

public interface IUserService {
    SignUpDTO save(SignUpForm userInfor);
}
