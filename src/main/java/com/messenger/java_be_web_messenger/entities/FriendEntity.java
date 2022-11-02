package com.messenger.java_be_web_messenger.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "friends")
public class FriendEntity extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "me_id")
	private UserEntity me;

	@ManyToOne
	@JoinColumn(name = "friend_id")
	private UserEntity friend;

	private boolean isBlocked;

}
