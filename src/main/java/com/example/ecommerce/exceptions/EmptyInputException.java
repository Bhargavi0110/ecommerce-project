package com.example.ecommerce.exceptions;

public class EmptyInputException extends RuntimeException{
    public EmptyInputException(String message) {
        super(message);
    }
}
