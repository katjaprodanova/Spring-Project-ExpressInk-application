package com.tattooshop.shop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class TattooAlreadyInFavorites extends RuntimeException{

    public TattooAlreadyInFavorites(Long id, String username) {
        super(String.format("Tattoo with id %d  is already favorited by user with username %s", id, username));
    }
}