package com.knou.view;

/**
 * 사용자 입력값의 유효성을 검증한다. 숫자와 부호(+,-,*,/)외의 다른 문자는 올 수 없다.
 */
public class InputValidator {

    public static String OPERATOR_REGEX = "[+\\-*/=]";
    public static String NUMBER_REGEX = "-?\\d+";
    public void validateNumber(String inputNumber) throws NumberFormatException {
        if (!inputNumber.matches(NUMBER_REGEX)) {
            throw new NumberFormatException();
        }
    }

    public void validateOperator(String input) throws IllegalArgumentException {
        if (input.matches(OPERATOR_REGEX)) {
            return;
        }
        throw new IllegalArgumentException();
    }
}
