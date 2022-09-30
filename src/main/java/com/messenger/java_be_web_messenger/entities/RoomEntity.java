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
@Table(name = "rooms")
public class RoomEntity extends BaseEntity {

    @Column
    private String nameRoom;

    @Column
    private Boolean isGroup;

    @Column
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private UserEntity owner;

    @OneToMany(mappedBy = "room")
    private List<PartacipantEntity> partacipants;
}
