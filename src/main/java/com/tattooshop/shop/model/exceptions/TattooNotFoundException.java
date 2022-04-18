package com.tattooshop.shop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TattooNotFoundException extends RuntimeException{

    public TattooNotFoundException(Long id) {
        super(String.format("tattoo with id: %d was not found", id));
    }
}