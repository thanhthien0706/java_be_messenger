package com.messenger.java_be_web_messenger.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.messenger.java_be_web_messenger.convert.UserConvert;
import com.messenger.java_be_web_messenger.dto.EmailDetails;
import com.messenger.java_be_web_messenger.dto.SignUpDTO;
import com.messenger.java_be_web_messenger.dto.UserDTO;
import com.messenger.java_be_web_messenger.entities.PasswordResetTokenEntity;
import com.messenger.java_be_web_messenger.entities.RoleEntity;
import com.messenger.java_be_web_messenger.entities.UserEntity;
import com.messenger.java_be_web_messenger.form.SignInForm;
import com.messenger.java_be_web_messenger.form.SignUpForm;
import com.messenger.java_be_web_messenger.jwt.JwtProvider;
import com.messenger.java_be_web_messenger.repository.PasswordResetTokenRepository;
import com.messenger.java_be_web_messenger.repository.RoleRepository;
import com.messenger.java_be_web_messenger.repository.UserRepository;
import com.messenger.java_be_web_messenger.security.CustomUserDetails;
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

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmailService emailService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

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
            if (userInfor.getAvatar() != null) {
                String urlImg = storageService.storageFile(userInfor.getAvatar());
                user.setAvatar(urlImg);
            } else {
                user.setAvatar(
                        "https://res.cloudinary.com/dd1yamek1/image/upload/v1665718619/web_messenger/user_x1slpc.jpg");
            }

            RoleEntity role = roleRepository.findOneByName("ROLE_USER");
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

    @Override
    public String signIn(SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        if (token != null) {
            return token;
        }
        return null;
    }

    @Override
    public String validatePasswordResetToken(long id, String token) {
        PasswordResetTokenEntity passToken = passwordResetTokenRepository.findOneByToken(token);
        if (passToken == null || (passToken.getUser().getId() != id)) {
            return "invalidToken";
        }

        Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime() <= 0)) {
            return "expired";
        }

        UserEntity user = passToken.getUser();
        return null;
    }

    @Override
    public UserEntity findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Boolean createPasswordResetTokenForUser(UserEntity user, String token) {
        boolean result = true;
        PasswordResetTokenEntity myToken = new PasswordResetTokenEntity(token, user);
        PasswordResetTokenEntity reset = passwordResetTokenRepository.save(myToken);

        if (reset == null) {
            result = false;
        }

        return result;
    }

    @Override
    public Boolean structSendMailResetPassword(String baseUrl, String token, UserEntity user) {
        Boolean statusSend = true;

        String url = baseUrl + "/quen-mat-khau?token=" + token;

        EmailDetails emailDetails = new EmailDetails(user.getEmail(), "Email reset password. Click link: " + url,
                "RESET PASSWORD", null);
        statusSend = emailService.sendSimpleMail(emailDetails);

        return statusSend;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetTokenEntity passToken = passwordResetTokenRepository.findOneByToken(token);
        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                        : null;
    }

    private boolean isTokenFound(PasswordResetTokenEntity passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetTokenEntity passToken) {
        final Calendar cal = Calendar.getInstance();
        if (passToken.getExpiryDate().getTime() - cal.getTime().getTime() <= 0) {
            return false;
        }
        return false;
        // final Calendar cal = Calendar.getInstance();
        // return passToken.getExpiryDate().before(cal.getTime());
    }

    @Override
    public UserEntity getUserByPasswordResetToken(String token) {
        return passwordResetTokenRepository.findOneByToken(token).getUser();
    }

    @Override
    public Boolean changeUserPassword(UserEntity user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        UserEntity result = userRepository.save(user);
        if (result != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<UserDTO> searchUsers(String text_search) {
        if (text_search != null && text_search != "") {
            return userConvert.toListDto(userRepository.findLikeByEmailAndPhone(text_search));
        }
        return null;

    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserEntity findOneById(Long id) {
        return userRepository.findOneById(id);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDTO getMe(Long id) {
        return userConvert.toDTOUser(userRepository.findOneById(id));
    }

}
