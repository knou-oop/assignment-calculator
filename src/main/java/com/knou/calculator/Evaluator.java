package com.knou.calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Evaluator {
    public static Double evaluate(List<String> postfix) {
        Deque<Double> stack = new ArrayDeque<>();

        for (String token : postfix) {
            if (token.matches("\\d+")) {
                stack.push(Double.parseDouble(token));
            } else {
                if (stack.size() < 2) throw new RuntimeException("연산자 '" + token + "' 에 피연산자가 부족합니다");

                double b = stack.pop();
                double a = stack.pop();
                Operator op = Registry.get(token);
                if (op == null) throw new RuntimeException("정의되지 않은 연산자: " + token);
                stack.push(op.apply(a, b));
            }
        }

        if (stack.size() != 1) throw new RuntimeException("수식이 잘못되었습니다");
        return stack.pop();
    }
}
