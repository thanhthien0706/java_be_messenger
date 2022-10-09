package com.messenger.java_be_web_messenger.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.messenger.java_be_web_messenger.dto.SignUpDTO;
import com.messenger.java_be_web_messenger.entities.UserEntity;
import com.messenger.java_be_web_messenger.form.SignUpForm;

@Component
public class UserConvert {

    @Autowired
    private ModelMapper modelMapper;

    public UserEntity toEntity(SignUpForm signUpForm) {
        UserEntity user = modelMapper.map(signUpForm, UserEntity.class);
        return user;
    }

    public SignUpDTO toDTO(UserEntity user) {
        SignUpDTO userDto = modelMapper.map(user, SignUpDTO.class);
        return userDto;
    }
}
