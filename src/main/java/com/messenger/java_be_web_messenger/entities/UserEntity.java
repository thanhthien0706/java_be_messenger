package com.messenger.java_be_web_messenger.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column
    private String username;

    @Column
    private String fullName;

    @Column
    private String avatar;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String password;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserProfileEntity userProfile;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private RoleEntity role;

    @OneToMany(mappedBy = "friendRequester", fetch = FetchType.LAZY)
    private List<FriendEntity> myFriends;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<RoomEntity> myRooms;

    @OneToMany(mappedBy = "user")
    private List<PartacipantEntity> partacipants;

    @OneToMany(mappedBy = "creator")
    private List<MessageEntity> messages;

    @OneToMany(mappedBy = "recipient")
    private List<MessageRecipientEntity> messageRecipients;
}
