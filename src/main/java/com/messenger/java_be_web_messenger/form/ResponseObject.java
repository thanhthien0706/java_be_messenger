package com.messenger.java_be_web_messenger.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponseObject
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject {
    private Boolean status;
    private String message;
    private Object data;
}