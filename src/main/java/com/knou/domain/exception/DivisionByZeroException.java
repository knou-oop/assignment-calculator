package com.knou.domain.exception;

import com.knou.common.CalculatorException;
import com.knou.view.ErrorMessage;

public class DivisionByZeroException extends CalculatorException {
    public DivisionByZeroException() {
        super(ErrorMessage.ERROR_DIVISION_BY_ZERO);
    }
}
