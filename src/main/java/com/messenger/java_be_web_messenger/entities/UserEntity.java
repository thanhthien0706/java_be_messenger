package com.messenger.java_be_web_messenger.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
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
    private boolean active = true;

    @Column
    @JsonIgnore
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @ManyToMany(mappedBy = "userContacts")
    List<ContactEntity> contacts;

    @OneToMany(mappedBy = "userCreator", fetch = FetchType.LAZY)
    private List<ConversationEntity> conversations;

    @OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY)
    private List<MessageEntity> sender;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<NotifiAddfriendEnity> notifiReceivers;

    @OneToMany(mappedBy = "requester", fetch = FetchType.LAZY)
    private List<NotifiAddfriendEnity> notifiRequesters;

    @OneToMany(mappedBy = "receiver")
    private List<NotifiTextEntity> notifiTexts;
}
