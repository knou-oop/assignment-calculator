package com.knou.exception;

import com.knou.view.ErrorMessage;

public class CalculatorException extends RuntimeException{
    private final ErrorMessage errorMessage;
    public CalculatorException(ErrorMessage message){
        super(message.getMessage());
        this.errorMessage = message;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
