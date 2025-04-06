package com.knou.view;

public enum ErrorMessage {
    ERROR_INVALID_NUMBER_INPUT("입력이 올바르지 않습니다. 숫자를 다시 입력해 주십시오"),
    ERROR_INVALID_OPERATOR_INPUT("입력이 올바르지 않습니다. 부호를 다시 입력해 주십시오"),
    ERROR_INVALID_YES_NO_INPUT("입력이 올바르지 않습니다. y 또는 n으로 입력해주세요");


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
}
