package com.revature.MKPG.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomerNotFoundException extends ResponseStatusException {

    public CustomerNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
