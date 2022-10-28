package com.messenger.java_be_web_messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.messenger.java_be_web_messenger.entities.ParticipantEntity;
import com.messenger.java_be_web_messenger.form.ParticipantForm;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
	boolean existsByConversationIdAndUserId(@Param("ConversationId") Long conversationId, @Param("UserId") long userId);
}
