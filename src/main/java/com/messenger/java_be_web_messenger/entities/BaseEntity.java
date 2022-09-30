package com.messenger.java_be_web_messenger.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createAt", updatable = false)
    @CreatedDate
    private Date createAt;

    @Column
    @LastModifiedDate
    private Date updateAt;

    @Column
    private boolean deleted = Boolean.FALSE;

    @Column
    private Date deleteAt;
}
