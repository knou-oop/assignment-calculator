package com.knou.view;

import com.knou.domain.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class View {
    static final String CALCULATOR_NUMBER_INPUT_GUIDE = "계산할 숫자를 입력해주세요.";
    static final String CALCULATOR_OPERATOR_INPUT_GUIDE = "+,-,*,/,= 등의 계산할 부호를 입력해주세요.";
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final InputValidator inputValidator;

    public View(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public ArrayList<String> inputCalculator() {
        ArrayList<String> expression = new ArrayList<>();
        while (true) {
            expression.add(inputNumber());
            String inputOperator = inputOperator();
            if (inputOperator.equals("=")) {
                break;
            }
            expression.add(inputOperator);
        }
        return expression;
    }

    private String inputNumber() {
        try {
            System.out.println(CALCULATOR_NUMBER_INPUT_GUIDE);
            String number = br.readLine();
            inputValidator.validateNumber(number);
            return number;
        } catch (IOException | NumberFormatException e) {
            System.out.println("입력이 올바르지 않습니다. 숫자를 다시 입력해 주십시오");
            return inputNumber();
        }
    }

    private String inputOperator() {
        try {
            System.out.println(CALCULATOR_OPERATOR_INPUT_GUIDE);
            String operator = br.readLine();
            inputValidator.validateOperator(operator);
            return operator;
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("입력이 올바르지 않습니다. 부호를 다시 입력해 주십시오");
            return inputOperator();
        }
    }

    public void displayResult(Log log) {
        System.out.println("사용자가 입력한 계산식 : " + log.getExpression());
        for (String s : log.getCalculateHistory()) {
            System.out.println(s);
        }
        System.out.println("계산 결과 : " + log.getResult());
    }
    public boolean shouldContinue(){
        System.out.println("계속 계산하시겠습니까?y or n 를 입력해주세요");
        try {
            if(br.readLine().equals("y")){
                return true;
            } else if (br.readLine().equals("n")) {
                return false;
            }else {
                throw new IllegalArgumentException();
            }

        } catch (IOException |IllegalArgumentException e) {
            System.out.println("입력이 올바르지 않습니다. y 또는 n으로 입력해주세요");
            return shouldContinue();
        }

    }


}
