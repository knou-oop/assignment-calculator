package com.knou.view;

public enum ErrorMessage {
    ERROR_INVALID_NUMBER_INPUT("입력이 올바르지 않습니다. 숫자를 다시 입력해 주십시오"),
    ERROR_INVALID_OPERATOR_INPUT("입력이 올바르지 않습니다. 부호를 다시 입력해 주십시오"),
    ERROR_INVALID_YES_NO_INPUT("입력이 올바르지 않습니다. y 또는 n으로 입력해주세요"),
    ERROR_DIVISION_BY_ZERO("0으로 나눌 수 없습니다."),
    ERROR_NUMBER_RANGE("계산 결과가 허용된 범위를 초과했습니다."),
    ERROR_CALCULATION("계산 중 오류가 발생했습니다");


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
}
