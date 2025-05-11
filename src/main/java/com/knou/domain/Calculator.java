package com.knou.domain;

import static com.knou.view.InputValidator.NUMBER_REGEX;
import static com.knou.view.InputValidator.OPERATOR_REGEX;

import com.knou.exception.CalculatorException;
import com.knou.exception.DivisionByZeroException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class Calculator {
    private final ArrayList<String> inputExpression;
    Map<String, Integer> operatorRank = Map.of("+", 1, "-", 1, "*", 2, "/", 2);

    public Calculator(ArrayList<String> inputExpression) {
        this.inputExpression = inputExpression;
    }

    /**
     * 계산기의 메인 연산 메서드
     * @param log 계산 과정과 결과를 저장할 Log 객체
     * @return 계산이 완료된 Log 객체
     */
    public Log calculateStart(Log log) throws CalculatorException {
        ArrayList<String> postfix = convertToPostfix(inputExpression);
        return calculatePostfix(postfix, log);
    }

    /**
     * 중위표현법을 후위표현법으로 변환하는 메서드
     * @return 후위표현법으로 변환된 수식
     */
    public ArrayList<String> convertToPostfix(ArrayList<String> expression) {
        ArrayList<String> postfix = new ArrayList<>();
        Stack<String> operateStack = new Stack<>();
        for (String s : expression) {
            if (!s.matches(OPERATOR_REGEX)) {
                postfix.add(s);
            } else {
                if (!operateStack.isEmpty() && operatorRank.get(operateStack.peek()) >= operatorRank.get(s)) {
                    postfix.add(operateStack.pop());
                }
                operateStack.push(s);
            }
        }
        while (!operateStack.isEmpty()) {
            postfix.add(operateStack.pop());
        }

        System.out.println("후위계산식 : " + Arrays.toString(postfix.toArray()));
        return postfix;
    }

    /**
     * 후위표현법 수식을 계산하는 메서드
     * @return 계산이 완료된 Log 객체
     */
    public Log calculatePostfix(ArrayList<String> postfix, Log log)throws CalculatorException {
        Stack<String> operateStack = new Stack<>();
        for (String s : postfix) {
            if (s.matches(NUMBER_REGEX)) {
                operateStack.push(s);
            } else {
                String number2 = operateStack.pop();
                String number1 = operateStack.pop();
                operateStack.push(applyOperator(number1, number2, s, log));
            }
        }
        int parseInt = Integer.parseInt(operateStack.pop());
        log.generateResult(String.valueOf(parseInt));
        return log;
    }

    /**
     * 연산자를 적용하여 계산을 수행하는 메서드
     * @return 계산 결과
     */
    public String applyOperator(String number, String number2, String operator, Log log) throws CalculatorException {
        switch (operator) {
            case ("+") -> {
                int result = Integer.parseInt(number) + Integer.parseInt(number2);
                return String.valueOf(result);
            }
            case ("-") -> {
                int result = Integer.parseInt(number) - Integer.parseInt(number2);
                return String.valueOf(result);
            }
            case ("/") -> {
                if (Integer.parseInt(number2) == 0) {
                    throw new DivisionByZeroException();
                }
                double doubleResult = Double.parseDouble(number) / Double.parseDouble(number2);
                String result = new BigDecimal(doubleResult).setScale(2, RoundingMode.HALF_UP).toString();
                return String.valueOf((int) Double.parseDouble(result));
            }
            case ("*") -> {
                int result = Integer.parseInt(number) * Integer.parseInt(number2);
                return String.valueOf(result);
            }
        }
        return "";
    }
}
