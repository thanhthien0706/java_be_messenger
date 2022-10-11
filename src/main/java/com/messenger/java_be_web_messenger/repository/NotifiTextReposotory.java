package com.messenger.java_be_web_messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messenger.java_be_web_messenger.entities.NotifiTextEntity;

@Repository
public interface NotifiTextReposotory extends JpaRepository<NotifiTextEntity, Long> {

}