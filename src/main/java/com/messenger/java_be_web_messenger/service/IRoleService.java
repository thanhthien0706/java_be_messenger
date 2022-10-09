package com.messenger.java_be_web_messenger.service;

import java.util.Optional;

import com.messenger.java_be_web_messenger.entities.RoleEntity;

public interface IRoleService {
    RoleEntity findOneByName(String name);

    RoleEntity findOneById(Long id);
}