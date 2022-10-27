package com.example.demo.domain.exceptions;

public class NotFoundException extends RuntimeException{

    private static final String TITLE = "NOT FOUND";

    public NotFoundException(String message){
        super(TITLE + "." + message);
    }
}
