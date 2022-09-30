package com.messenger.java_be_web_messenger.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class MessageEntity extends BaseEntity {
    private enum TypeMessage {
        IMAGE, FILE, NOTIFI, CALL
    }

    @ManyToOne
    @JoinColumn(name = "creator_id", insertable = false, updatable = false)
    private UserEntity creator;

    @Column
    private TypeMessage typeMessage;

    @OneToMany(mappedBy = "message")
    private List<MessageRecipientEntity> messageRecipients;
}
