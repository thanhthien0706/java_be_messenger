package com.messenger.java_be_web_messenger.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BaseDTO {
    private Long id;
    private Date createAt;
    private Date updateAt;
    private Boolean deleted;
    private Date deleteAt;
}
