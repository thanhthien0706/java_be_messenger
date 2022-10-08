package com.messenger.java_be_web_messenger.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactEntity extends BaseEntity {
    @Column
    private String fullName;

    @Column
    private String Phone;

    @Column
    @Email
    private String email;

    @ManyToMany
    @JoinTable(name = "user_contact", joinColumns = @JoinColumn(name = "contact_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<UserEntity> userContacts;
}
