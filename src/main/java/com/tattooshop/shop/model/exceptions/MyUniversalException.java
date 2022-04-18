package com.tattooshop.shop.model.exceptions;

public class MyUniversalException extends RuntimeException{
    public MyUniversalException() {
        super("Exception occured!");
    }
}
