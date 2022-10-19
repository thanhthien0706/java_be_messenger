package com.messenger.java_be_web_messenger.service;

import java.util.List;
import java.util.Optional;

import com.messenger.java_be_web_messenger.dto.SignUpDTO;
import com.messenger.java_be_web_messenger.dto.UserDTO;
import com.messenger.java_be_web_messenger.entities.UserEntity;
import com.messenger.java_be_web_messenger.form.SignInForm;
import com.messenger.java_be_web_messenger.form.SignUpForm;

public interface IUserService {
    UserEntity findOneById(Long id);

    Optional<UserEntity> findById(Long id);

    UserEntity findOneByEmail(String email);

    Boolean existsByEmail(String email);

    SignUpDTO save(SignUpForm userInfor);

    String signIn(SignInForm signInForm);

    String validatePasswordResetToken(long id, String token);

    Boolean createPasswordResetTokenForUser(UserEntity user, String token);

    Boolean structSendMailResetPassword(String baseUrl, String token, UserEntity user);

    String validatePasswordResetToken(String token);

    UserEntity getUserByPasswordResetToken(String token);

    Boolean changeUserPassword(UserEntity user, String newPassword);

    List<UserDTO> searchUsers(String text_search);

    boolean existsById(Long id);

    UserDTO getMe(Long id);
}
