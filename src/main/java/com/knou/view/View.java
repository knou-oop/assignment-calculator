package com.knou.view;

import com.knou.domain.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class View {
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
            System.out.println(ViewMessage.CALCULATOR_NUMBER_INPUT_GUIDE.getMessage());
            String number = br.readLine();
            inputValidator.validateNumber(number);
            return number;
        } catch (IOException | NumberFormatException e) {
            System.out.println(ErrorMessage.ERROR_INVALID_NUMBER_INPUT.getMessage());
            return inputNumber();
        }
    }

    private String inputOperator() {
        try {
            System.out.println(ViewMessage.CALCULATOR_OPERATOR_INPUT_GUIDE.getMessage());
            String operator = br.readLine();
            inputValidator.validateOperator(operator);
            return operator;
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(ErrorMessage.ERROR_INVALID_OPERATOR_INPUT.getMessage());
            return inputOperator();
        }
    }

    public void displayResult(Log log) {
        System.out.println(ViewMessage.USER_INPUT_EXPRESSION.getMessage() + log.getExpression());
        for (String s : log.getCalculateHistory()) {
            System.out.println(s);
        }
        System.out.println(ViewMessage.CALCULATE_RESULT.getMessage() + log.getResult());
    }
    public boolean shouldContinue(){
        System.out.println(ViewMessage.PROMPT_CONTINUE_CALCULATION.getMessage());
        try {
            String userResponse = br.readLine();
            if(userResponse.equals("y")){
                return true;
            } else if (userResponse.equals("n")) {
                return false;
            }else {
                throw new IllegalArgumentException();
            }

        } catch (IOException |IllegalArgumentException e) {
            System.out.println(ErrorMessage.ERROR_INVALID_YES_NO_INPUT.getMessage());
            return shouldContinue();
        }
    }

    public void displayAllHistory(ArrayList<Log> allHistory) {
        System.out.println(ViewMessage.CALCULATOR_ALL_HISTORY.getMessage());
        for (Log log:allHistory) {
            System.out.println(log.getExpression()+" " + log.getResult());
        }
        System.out.println(ViewMessage.CALCULATOR_END.getMessage());
    }
}
