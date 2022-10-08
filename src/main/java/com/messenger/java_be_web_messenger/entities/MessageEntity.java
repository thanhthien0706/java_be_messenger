package com.messenger.java_be_web_messenger.entities;

import java.util.List;

import javax.management.loading.PrivateClassLoader;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.messenger.java_be_web_messenger.entities.enum_type.TypeMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages")
public class MessageEntity extends BaseEntity {
    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeMessage type;

    @ManyToOne
    @JoinColumn(name = "conversation_id", insertable = false, updatable = false)
    private ConversationEntity conversation;

    @ManyToOne
    @JoinColumn(name = "sender_id", insertable = false, updatable = false)
    private UserEntity sender;

    @OneToMany(mappedBy = "message", fetch = FetchType.LAZY)
    private List<AttachmentEntity> attachments;
}
