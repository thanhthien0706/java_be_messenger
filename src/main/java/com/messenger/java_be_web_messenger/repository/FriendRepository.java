package com.messenger.java_be_web_messenger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messenger.java_be_web_messenger.entities.FriendEntity;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, Long> {
	List<FriendEntity> findByMeId(Long idme);
}
