package com.messenger.java_be_web_messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.messenger.java_be_web_messenger.convert.UserConvert;
import com.messenger.java_be_web_messenger.dto.SignUpDTO;
import com.messenger.java_be_web_messenger.entities.RoleEntity;
import com.messenger.java_be_web_messenger.entities.UserEntity;
import com.messenger.java_be_web_messenger.form.SignUpForm;
import com.messenger.java_be_web_messenger.repository.RoleRepository;
import com.messenger.java_be_web_messenger.repository.UserRepository;
import com.messenger.java_be_web_messenger.service.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserConvert userConvert;

    @Autowired
    StorageService storageService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    // SignUpDTO
    public SignUpDTO save(SignUpForm userInfor) {
        Boolean hasEmail = userRepository.existsByEmail(userInfor.getEmail());
        Boolean hasPhone = userRepository.existsByPhone(userInfor.getPhone());
        Boolean hasUsername = userRepository.existsByUsername(userInfor.getUsername());

        System.out.println("Kiem tra : " + hasEmail + "," + hasPhone + ", " + hasUsername);

        if (!hasEmail && !hasPhone && !hasUsername) {
            UserEntity user = userConvert.toEntity(userInfor);
            user.setPassword(passwordEncoder.encode(userInfor.getPassword()));
            if (userInfor.getAvatar() != null || !userInfor.getAvatar().isEmpty()) {
                String urlImg = storageService.storageFile(userInfor.getAvatar());
                user.setAvatar(urlImg);
            }

            RoleEntity role = roleRepository.findOneByName(userInfor.getRoleName());
            if (role != null) {
                user.setRole(role);
            }

            UserEntity result = userRepository.save(user);
            if (result != null) {
                SignUpDTO signUpDTO = userConvert.toDTO(result);
                return signUpDTO;
            }

        }
        return null;
    }

}
