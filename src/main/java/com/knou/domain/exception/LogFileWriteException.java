package com.knou.domain.exception;

import com.knou.common.CalculatorException;
import com.knou.view.ErrorMessage;

public class LogFileWriteException extends CalculatorException {

    public LogFileWriteException() {
        super(ErrorMessage.ERROR_LOG_FILE_WRITE);
    }
}
