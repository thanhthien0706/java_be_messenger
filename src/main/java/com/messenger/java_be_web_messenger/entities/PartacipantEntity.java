package com.messenger.java_be_web_messenger.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "partacipants")
public class PartacipantEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private RoomEntity room;

    @OneToMany(mappedBy = "recipientGroup")
    private List<MessageRecipientEntity> messageRecipients;
}
