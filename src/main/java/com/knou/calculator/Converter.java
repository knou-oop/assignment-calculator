package com.knou.calculator;

import com.knou.calculator.operator.OperatorRegistry;
import com.knou.calculator.token.Token;

import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Converter {
    public static List<Token> toPostfix(List<Token> tokens) {
        List<Token> output = new ArrayList<>();
        Deque<Token> stack = new ArrayDeque<>();

        for (Token token : tokens) {
            if (token.isOperand()) {
                output.add(token);
            } else if (token.isLeftParenthesis()) {
                stack.push(token);
            } else if (token.isRightParenthesis()) {
                while (!stack.isEmpty() && !stack.peek().isLeftParenthesis()) {
                    output.add(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek().isLeftParenthesis()) {
                    stack.pop(); // remove (
                }
            } else if (token.isOperator() && OperatorRegistry.contains(token.getSymbol())) {
                while (!stack.isEmpty()
                        && !stack.peek().isLeftParenthesis()
                        && OperatorRegistry.get(stack.peek().getSymbol()).getPrecedence() >= OperatorRegistry.get(token.getSymbol()).getPrecedence()) {
                    output.add(stack.pop());
                }
                stack.push(token);
            } else {
                throw new RuntimeException("알 수 없는 연산자: " + token.getSymbol());
            }
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }
}
