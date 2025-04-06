package com.knou.view;

import java.util.List;

public class InputValidator {

    public void validationFilterChain(List<String> input) {

    }

    public void validateNumber(String inputNumber) throws NumberFormatException {
        if (!inputNumber.matches("-?\\d+")) {
            throw new NumberFormatException("숫자를 입력해 주세요: " + inputNumber);
        }
    }

    public void validateOperator(String input) throws IllegalArgumentException {
        if (input.matches("[+\\-*/=]")) {
            return;
        }
        throw new IllegalArgumentException("부호를 바르게 입력해주세요" + input);
    }
}
