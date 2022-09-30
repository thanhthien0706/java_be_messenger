package com.messenger.java_be_web_messenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewController {
    @GetMapping("/new")
    public String getNew() {
        return "Thanh thien";
    }
}
