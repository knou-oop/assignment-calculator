package com.knou.exception;

import com.knou.view.ErrorMessage;
public class InvalidYesNoInputException extends CalculatorException {
    public InvalidYesNoInputException() {
        super(ErrorMessage.ERROR_INVALID_YES_NO_INPUT);
    }
} 