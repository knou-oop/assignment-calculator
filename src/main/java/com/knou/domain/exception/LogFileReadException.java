package com.knou.domain.exception;

import com.knou.common.CalculatorException;
import com.knou.view.ErrorMessage;

public class LogFileReadException extends CalculatorException {

    public LogFileReadException() {
        super(ErrorMessage.ERROR_LOG_FILE_READ);
    }
}
