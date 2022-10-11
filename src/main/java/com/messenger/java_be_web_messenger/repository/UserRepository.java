package com.messenger.java_be_web_messenger.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.messenger.java_be_web_messenger.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    UserEntity findOneByEmail(String email);

    UserEntity findOneById(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    boolean existsById(Long id);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.email LIKE CONCAT('%',:email,'%') OR u.phone LIKE CONCAT('%',:phone,'%')")
    List<UserEntity> findLikeByEmailAndPhone(String email, String phone);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.email LIKE CONCAT('%',:email,'%')")
    List<UserEntity> findLikeByEmail(String email);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.phone LIKE CONCAT('%',:phone,'%')")
    List<UserEntity> findLikeByPhone(String phone);

    // List<UserEntity> findByEmailOrPhoneContaemailining(String email, String
    // phone);
}
