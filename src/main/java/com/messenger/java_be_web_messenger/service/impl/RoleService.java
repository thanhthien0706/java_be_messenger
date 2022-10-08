package com.messenger.java_be_web_messenger.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.java_be_web_messenger.entities.RoleEntity;
import com.messenger.java_be_web_messenger.repository.RoleRepository;
import com.messenger.java_be_web_messenger.service.IRoleService;

@Service
public class RoleService implements IRoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<RoleEntity> findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

}
