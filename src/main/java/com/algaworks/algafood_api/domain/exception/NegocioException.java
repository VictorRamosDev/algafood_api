package com.algaworks.algafood_api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NegocioException extends ResponseStatusException {

    public NegocioException(String message, Exception e) {
        super(HttpStatus.BAD_REQUEST, message, e);
    }

}
