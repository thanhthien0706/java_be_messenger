package com.messenger.java_be_web_messenger.convert;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.messenger.java_be_web_messenger.dto.SignUpDTO;
import com.messenger.java_be_web_messenger.dto.UserDTO;
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

    public List<UserDTO> toListDto(List<UserEntity> listUser) {
        List<UserDTO> list_user_dto = new ArrayList();
        for (UserEntity user : listUser) {
            UserDTO user_dto = modelMapper.map(user, UserDTO.class);
            user_dto.setRole_name(user.getRole().getName());
            list_user_dto.add(user_dto);
        }

        return list_user_dto;
    }
}
