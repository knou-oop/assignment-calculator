package com.knou.exception;

import com.knou.view.ErrorMessage;

public class DivisionByZeroException extends CalculatorException{
    public DivisionByZeroException(){
        super(ErrorMessage.ERROR_DIVISION_BY_ZERO);
    }
}
