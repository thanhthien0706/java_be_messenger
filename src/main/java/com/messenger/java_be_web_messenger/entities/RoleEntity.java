package com.messenger.java_be_web_messenger.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Column
    private String code;

    @Column
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<UserEntity> users;
}
