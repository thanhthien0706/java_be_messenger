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

    @Query(nativeQuery = true, value = "SELECT * FROM users u WHERE (u.email LIKE CONCAT('%',:text_search,'%') OR u.phone LIKE CONCAT('%',:text_search,'%')) AND u.id != :id_requester")
    List<UserEntity> findLikeByEmailAndPhone(String text_search, Long id_requester);

}
