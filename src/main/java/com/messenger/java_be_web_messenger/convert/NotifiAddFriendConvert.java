package com.messenger.java_be_web_messenger.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotifiAddFriendConvert {
    @Autowired
    private ModelMapper modelMapper;
}
