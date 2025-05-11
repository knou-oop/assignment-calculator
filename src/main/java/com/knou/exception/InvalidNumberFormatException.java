package com.knou.exception;

import com.knou.common.CalculatorException;
import com.knou.view.ErrorMessage;

public class InvalidNumberFormatException  extends CalculatorException {
    public InvalidNumberFormatException() {
        super(ErrorMessage.ERROR_INVALID_NUMBER_INPUT);
    }
}
