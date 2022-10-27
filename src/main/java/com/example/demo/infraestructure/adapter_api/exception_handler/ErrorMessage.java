package com.example.demo.infraestructure.adapter_api.exception_handler;

public class ErrorMessage {

    private String error;
    private String message;

    public ErrorMessage(Exception exception){
        this.error = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
    }
}
