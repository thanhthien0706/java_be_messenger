package com.messenger.java_be_web_messenger.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "friends")
public class FriendEntity extends BaseEntity {

    private enum Status {
        ACCEPT, WATTING
    }

    @ManyToOne
    @JoinColumn(name = "friend_requester_id", insertable = false, updatable = false)
    private UserEntity friendRequester;

    @ManyToOne
    @JoinColumn(name = "friend_accepter_id", insertable = false, updatable = false)
    private UserEntity friendAccepter;

    @Column
    private Status status;

    @Column
    private Boolean isBlocked = false;
}
