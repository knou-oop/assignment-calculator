package com.knou.exception;

import com.knou.view.ErrorMessage;

public class InvalidOperatorFormatException extends CalculatorException{
    public InvalidOperatorFormatException() {
        super(ErrorMessage.ERROR_INVALID_OPERATOR_INPUT);
    }
}
