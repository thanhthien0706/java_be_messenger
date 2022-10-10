package com.messenger.java_be_web_messenger.service;

import com.messenger.java_be_web_messenger.dto.EmailDetails;

public interface IEmailService {
    Boolean sendSimpleMail(EmailDetails details);

    Boolean sendMailWithAttachment(EmailDetails details);
}
