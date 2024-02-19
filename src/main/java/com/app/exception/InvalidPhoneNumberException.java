package com.app.exception;

public class InvalidPhoneNumberException extends AppException {
    public InvalidPhoneNumberException(String message) {
        super(message);
        code = 1004;
    }
}
