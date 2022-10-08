package com.messenger.java_be_web_messenger.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notifi_texts")
public class NotifiTextEntity extends BaseEntity {
    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "receiver_id", insertable = false, updatable = false)
    private UserEntity receiver;
}
