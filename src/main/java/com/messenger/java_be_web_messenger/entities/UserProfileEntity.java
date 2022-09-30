package com.messenger.java_be_web_messenger.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_profiles")
public class UserProfileEntity extends BaseEntity {
    @Column
    private String shortDescription;

    @Column
    private Date birthday;

    @Column
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
