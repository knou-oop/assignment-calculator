package com.knou.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class Calculator {

    public static String OPERATOR_REGEX = "[+\\-*/=]";
    private final ArrayList<String> inputExpression;
    Map<String, Integer> operatorRank = Map.of("+", 1, "-", 1, "*", 2, "/", 2);

    public Calculator(ArrayList<String> inputExpression) {
        this.inputExpression = inputExpression;
    }

    public Log calculateStart(Log log) {
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
            if (!s.matches(OPERATOR_REGEX)) {
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
                String resultString = String.valueOf((int) Double.parseDouble(result));
                return resultString;
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
