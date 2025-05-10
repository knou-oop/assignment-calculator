package com.knou.exception;

import com.knou.view.ErrorMessage;

public class ExceptionHandler {
    private static final ExceptionHandler instance = new ExceptionHandler();

    private ExceptionHandler() {}

    public static ExceptionHandler getInstance() {
        return instance;
    }

    public void handleCalculatorException(CalculatorException e) {
        System.out.println(e.getMessage());
    }
}
