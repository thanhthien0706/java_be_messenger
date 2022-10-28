package com.messenger.java_be_web_messenger.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conversations")
public class ConversationEntity extends BaseEntity {
	@Column
	private String title;

	@ManyToOne
	@JoinColumn(name = "creator_id")
	private UserEntity userCreator;

	@Column
	private String channelId;

	@Column
	@Lob
	private String avatar;

	@Column
	private String description;

	@Column
	private Boolean isBlocked;

	@OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY)
	private List<MessageEntity> messages;
}
