package com.knou.exception;

import com.knou.view.ErrorMessage;

public class LogFileReadException extends CalculatorException{

    public LogFileReadException() {
        super(ErrorMessage.ERROR_LOG_FILE_WRITE);
    }
}
