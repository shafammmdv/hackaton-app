package com.app.handler;

import com.app.exception.AppException;
import com.app.exception.DataNotFoundException;
import com.app.exception.FailedToGetSuccessfulResponseException;
import com.app.model.ErrorResponseModel;
import com.app.model.ResponseModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@Log4j2
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleException(AppException exception) {
        if (exception instanceof FailedToGetSuccessfulResponseException) {
            return handleException(exception, HttpStatus.BAD_REQUEST);
        } else if (exception instanceof DataNotFoundException) {
            return handleException(exception, HttpStatus.NOT_FOUND);
        }
        return handleException(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        if (exception instanceof HttpMessageNotReadableException)
            return handleException(exception, 7000);
        else if (exception instanceof MissingServletRequestParameterException)
            return handleException(exception, 7001);
        else if (exception instanceof HttpRequestMethodNotSupportedException)
            return handleException(exception, 7002);
//        else if (exception instanceof MalformedJwtException)
//            return handleException(exception, 7003);
//        else if (exception instanceof SignatureException)
//            return handleException(exception, 7004);
//        else if (exception instanceof ExpiredJwtException)
//            return handleException(exception, 7005);
//        else if (exception instanceof UnsupportedJwtException)
//            return handleException(exception, 7006);
        else if (exception instanceof MethodArgumentNotValidException)
            return handleException(exception, 7007);
        else if (exception instanceof ConstraintViolationException)
            return handleException(exception, 7008);
        return handleException(exception, 9999);
    }

    private ResponseEntity<?> handleException(AppException exception, HttpStatus status) {
        log.error(exception.getMessage());
        return ResponseEntity.status(status)
                .body(ResponseModel.builder()
                        .status(status)
                        .body(ErrorResponseModel.builder()
                                .message(exception.getMessage())
                                .code(exception.getCode())
                                .build())
                        .build());
    }
    private ResponseEntity<?> handleException(Exception exception, int code) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseModel.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponseModel.builder()
                                .message(exception.getMessage())
                                .code(code)
                                .build())
                        .build());
    }
}

