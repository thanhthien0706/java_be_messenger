package com.messenger.java_be_web_messenger.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "message_recipient")
public class MessageRecipientEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "recipient_id", insertable = false, updatable = false)
    private UserEntity recipient;

    @ManyToOne
    @JoinColumn(name = "recipient_group_id", insertable = false, updatable = false)
    private PartacipantEntity recipientGroup;

    @ManyToOne
    @JoinColumn(name = "message_id", insertable = false, updatable = false)
    private MessageEntity message;

    @Column
    private Boolean isReaded;
}