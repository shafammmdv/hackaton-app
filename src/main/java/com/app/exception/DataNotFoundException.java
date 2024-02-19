package com.app.exception;

import lombok.Getter;

@Getter
public class DataNotFoundException extends AppException {
    int code;

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, int code) {
        super(message);
        this.code = code;
    }
}
