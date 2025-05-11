package com.knou.exception;

import com.knou.common.CalculatorException;
import com.knou.view.ErrorMessage;

public class LogFileDeleteException extends CalculatorException {

    public LogFileDeleteException() {
        super(ErrorMessage.ERROR_FAIL_DELETE_OLD_LOG_FILE);
    }
}
