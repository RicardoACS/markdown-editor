package com.burea.markdowneditor.error;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Ricardo Carrasco S
 * 19-03-2020
 **/
@ResponseStatus(INTERNAL_SERVER_ERROR)
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
