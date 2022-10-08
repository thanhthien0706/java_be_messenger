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

    public UserEntity(String username2, String email2, String encode) {
        this.username = username2;
        this.email = email2;
        this.password = encode;
    }

    @Column
    // @NotBlank
    private String username;

    @Column
    private String fullName;

    @Column
    @Lob
    private String avatar = "https://res.cloudinary.com/dd1yamek1/image/upload/v1665204147/web_messenger/download_zbxrqz.png";

    @Column
    private String email;

    @Column
    // @NotBlank
    private String phone;

    @Column
    private boolean active = true;

    @Column
    @JsonIgnore
    // @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
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
