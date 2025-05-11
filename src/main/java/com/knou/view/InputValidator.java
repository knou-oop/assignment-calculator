package com.knou.view;

import com.knou.common.CalculatorException;
import com.knou.exception.InvalidNumberFormatException;
import com.knou.exception.InvalidOperatorFormatException;

public class InputValidator {
    public static String OPERATOR_REGEX = "[+\\-*/=]";
    public static String NUMBER_REGEX = "-?\\d+";

    /**
     * 입력된 숫자의 유효성을 검증하는 메서드
     */
    public void validateNumber(String inputNumber) throws CalculatorException {
        if (!inputNumber.matches(NUMBER_REGEX)) {
            throw new InvalidNumberFormatException();
        }
    }

    /**
     * 입력된 연산자의 유효성을 검증하는 메서드
     */
    public void validateOperator(String input) throws CalculatorException {
        if (input.matches(OPERATOR_REGEX)) {
            return;
        }
        throw new InvalidOperatorFormatException();
    }
}
