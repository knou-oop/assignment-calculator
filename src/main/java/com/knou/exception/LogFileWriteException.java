package com.knou.exception;

import com.knou.view.ErrorMessage;

public class LogFileWriteException extends CalculatorException{

    public LogFileWriteException() {
        super(ErrorMessage.ERROR_LOG_FILE_WRITE);
    }
}
