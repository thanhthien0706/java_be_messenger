package com.messenger.java_be_web_messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messenger.java_be_web_messenger.entities.ConversationEntity;

@Repository
public interface ConversationRepository extends JpaRepository<ConversationEntity, Long> {
	Boolean existsByChannelId(String channelId);

	ConversationEntity findOneById(Long id);
}
