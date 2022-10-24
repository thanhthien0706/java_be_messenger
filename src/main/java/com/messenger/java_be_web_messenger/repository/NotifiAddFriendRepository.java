package com.messenger.java_be_web_messenger.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.messenger.java_be_web_messenger.entities.NotifiAddfriendEnity;

@Repository
public interface NotifiAddFriendRepository extends JpaRepository<NotifiAddfriendEnity, Long> {
    @Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 FROM notifi_addfriend af WHERE af.receiver_id = :receiver_id AND af.requester_id = :requester_id")
    BigInteger checkExistNotifiAddFriend(Long receiver_id, Long requester_id);

    List<NotifiAddfriendEnity> findByReceiver(Long receiverId);
}
