package com.knou.calculator;

import com.knou.calculator.operator.Operator;
import com.knou.calculator.operator.OperatorRegistry;
import com.knou.calculator.token.Token;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Evaluator {
    public static Double evaluate(List<Token> postfix) {
        Deque<Double> stack = new ArrayDeque<>();

        for (Token token : postfix) {
            if (token.isOperand()) {
                stack.push(token.getValue());
            } else {
                String symbol = token.getSymbol();
                if (stack.size() < 2) throw new RuntimeException("연산자 '" + symbol + "' 에 피연산자가 부족합니다");

                double b = stack.pop();
                double a = stack.pop();
                Operator op = OperatorRegistry.get(symbol);
                if (op == null) throw new RuntimeException("정의되지 않은 연산자: " + symbol);
                stack.push(op.apply(a, b));
            }
        }

        if (stack.size() != 1) throw new RuntimeException("수식이 잘못되었습니다");
        return stack.pop();
    }
}
