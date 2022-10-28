package com.messenger.java_be_web_messenger.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attachments")
public class AttachmentEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "message_id")
    private MessageEntity message;

    @Lob
    private String thumbUrl;

    @Lob
    private String fileUrl;
}
