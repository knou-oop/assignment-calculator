package com.knou.view;

import com.knou.domain.Log;
import com.knou.exception.CalculatorException;
import com.knou.exception.ExceptionHandler;
import com.knou.exception.InvalidYesNoInputException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 사용자 인터페이스를 담당하는 클래스
 * 사용자로부터 입력을 받고 결과를 출력하는 역할
 */
public class View {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final InputValidator inputValidator;

    /**
     * View 생성자
     * @param inputValidator 입력값 검증을 담당하는 객체
     */
    public View(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    /**
     * 사용자로부터 계산식을 입력받는 메서드
     * @return 완성된 계산식
     */
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

    /**
     * 사용자로부터 숫자를 입력받는 메서드
     * @return 유효한 숫자 문자열
     */
    private String inputNumber() {
        try {
            System.out.println(ViewMessage.CALCULATOR_NUMBER_INPUT_GUIDE.getMessage());
            String number = br.readLine();
            inputValidator.validateNumber(number);
            return number;
        } catch (CalculatorException e) {
            ExceptionHandler.getInstance().handleCalculatorException(e);
            return inputNumber();
        } catch (IOException | NumberFormatException e) {
            System.out.println(ErrorMessage.ERROR_INVALID_NUMBER_INPUT.getMessage());
            return inputNumber();
        }
    }

    /**
     * 사용자로부터 연산자를 입력받는 메서드
     * @return 유효한 연산자 문자열
     */
    private String inputOperator() {
        try {
            System.out.println(ViewMessage.CALCULATOR_OPERATOR_INPUT_GUIDE.getMessage());
            String operator = br.readLine();
            inputValidator.validateOperator(operator);
            return operator;
        } catch (CalculatorException e) {
            ExceptionHandler.getInstance().handleCalculatorException(e);
            return inputOperator();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(ErrorMessage.ERROR_INVALID_OPERATOR_INPUT.getMessage());
            return inputOperator();
        }
    }

    /**
     * 계산 결과를 출력하는 메서드
     * @param log 계산 과정과 결과가 담긴 Log 객체
     */
    public void displayResult(Log log) {
        System.out.println(ViewMessage.USER_INPUT_EXPRESSION.getMessage() + log.getExpression());
        System.out.println(ViewMessage.CALCULATE_RESULT.getMessage() + log.getResult());
    }

    /**
     * 사용자에게 계산 계속 여부를 묻는 메서드
     * @return true: 계속 계산, false: 계산 종료
     */
    public boolean shouldContinue() {
        System.out.println(ViewMessage.PROMPT_CONTINUE_CALCULATION.getMessage());
        try {
            String userResponse = br.readLine();
            if (userResponse.equals("y")) {
                return true;
            } else if (userResponse.equals("n")) {
                return false;
            } else {
                throw new InvalidYesNoInputException();
            }
        } catch (InvalidYesNoInputException e) {
            ExceptionHandler.getInstance().handleCalculatorException(e);
            return shouldContinue();
        } catch (IOException e){
            System.out.println(ErrorMessage.ERROR_INVALID_YES_NO_INPUT.getMessage());
            return shouldContinue();
        }
    }

    /**
     * 모든 계산 기록을 출력하는 메서드
     * @param allHistory 모든 계산 기록이 담긴 리스트
     */
    public void displayAllHistory(ArrayList<Log> allHistory) {
        System.out.println(ViewMessage.CALCULATOR_ALL_HISTORY.getMessage());
        for (Log log : allHistory) {
            System.out.println(log.getHistoryLog());
        }
        System.out.println(ViewMessage.CALCULATOR_END.getMessage());
    }
}
