package com.messenger.java_be_web_messenger.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.messenger.java_be_web_messenger.entities.enum_type.TypeGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "participants")
public class ParticipantEntity extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "conversation_id")
	private ConversationEntity conversation;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@Column
	@Enumerated(EnumType.STRING)
	private TypeGroup type;
}
