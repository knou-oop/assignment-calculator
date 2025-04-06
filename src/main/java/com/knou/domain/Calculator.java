package com.knou.domain;

import static com.knou.view.InputValidator.NUMBER_REGEX;
import static com.knou.view.InputValidator.OPERATOR_REGEX;

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
     * 계산기를 통한 연산
     * @param log 비어있는 log 받음
     * @return 계산과정 및 결과가 포함된 log 반환
     */
    public Log calculateStart(Log log) {
        //중위표현법을 후위표현법으로 변환
        ArrayList<String> postfix = convertToPostfix(inputExpression);
        return calculatePostfix(postfix, log);
    }

    public ArrayList<String> convertToPostfix(ArrayList<String> expression) {
        ArrayList<String> postfix = new ArrayList<>();
        Stack<String> operateStack = new Stack<>();
        for (String s : expression) {
            if (!s.matches(OPERATOR_REGEX)) {
                postfix.add(s);
            } else {
                //스택이 비어있지 않거나, 우선순위가 작거나 같으면 스택에 넣지않고 배열에 넣는다.
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

    public Log calculatePostfix(ArrayList<String> postfix, Log log) {
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

    public String applyOperator(String number, String number2, String operator, Log log) {
        switch (operator) {
            case ("+") -> {
                int result = Integer.parseInt(number) + Integer.parseInt(number2);
                String resultString = String.valueOf(result);
                log.addHistory(number, number2, operator, resultString);
                return resultString;
            }
            case ("-") -> {
                int result = Integer.parseInt(number) - Integer.parseInt(number2);
                String resultString = String.valueOf(result);
                log.addHistory(number, number2, operator, resultString);
                return resultString;
            }
            case ("/") -> {
                double doubleResult = Double.parseDouble(number) / Double.parseDouble(number2);
                String result = new BigDecimal(doubleResult).setScale(2, RoundingMode.HALF_UP).toString();
                log.addHistory(number, number2, operator, result);
                return String.valueOf((int) Double.parseDouble(result));
            }
            case ("*") -> {
                int result = Integer.parseInt(number) * Integer.parseInt(number2);
                String resultString = String.valueOf(result);
                log.addHistory(number, number2, operator, resultString);
                return resultString;
            }
        }
        return "";
    }
}
