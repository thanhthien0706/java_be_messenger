package com.messenger.java_be_web_messenger.service;

import java.util.Optional;

import com.messenger.java_be_web_messenger.entities.UserEntity;

public interface IUserService {
    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    UserEntity save(UserEntity user);
}
