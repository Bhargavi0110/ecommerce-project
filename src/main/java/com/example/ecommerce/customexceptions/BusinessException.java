package com.example.ecommerce.customexceptions;

import org.springframework.stereotype.Component;

@Component
public class BusinessException extends RuntimeException{

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BusinessException() {
    }

    public BusinessException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }
}
