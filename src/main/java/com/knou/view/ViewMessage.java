package com.knou.view;

public enum ViewMessage {
    CALCULATE_RESULT("계산 결과 : "),
    USER_INPUT_EXPRESSION("사용자가 입력한 계산식 : "),
    PROMPT_CONTINUE_CALCULATION("계속 계산하시겠습니까? y or n 를 입력해주세요"),
    CALCULATOR_NUMBER_INPUT_GUIDE("계산할 숫자를 입력해주세요."),
    CALCULATOR_OPERATOR_INPUT_GUIDE("+,-,*,/,= 등의 계산할 부호를 입력해주세요."),
    CALCULATOR_ALL_HISTORY("-----------<계산결과 히스토리>--------------"),
    CALCULATOR_END("-----------<계산기를 종료합니다.>--------------");
    private final String message;

    ViewMessage(String message) {
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
}
