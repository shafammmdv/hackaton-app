package com.app.handler;

import com.app.exception.DataNotFoundException;
import com.app.exception.FailedToGetSuccessfulResponseException;
import com.app.model.ErrorResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.util.Optional;
import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FeignCustomErrorHandler implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorResponseModel errorResponseModel = Optional.ofNullable(getResponse(response)).orElse(new ErrorResponseModel());
        log.error("Exception response: {}", errorResponseModel);
        if (response.status() == 400) {
            return new ConstraintViolationException(errorResponseModel.getMessage(), null);
        }

        if (response.status() == 404) {
            return new DataNotFoundException(errorResponseModel.getMessage());
        }

        if (response.status() >= 400 && response.status() <= 599) {
            return new FailedToGetSuccessfulResponseException(errorResponseModel.getMessage());
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }

    private ErrorResponseModel getResponse(Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        try {
            if (response.body() != null) {
                return objectMapper.readValue(response.body().asReader(), ErrorResponseModel.class);
            }
        } catch (IOException e) {
            log.error("Could not parse error response, check client endpoints", e);
        }
        return null;
    }
}
