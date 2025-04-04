package com.knou.calculator;

import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Converter {
    public static List<String> toPostfix(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                output.add(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek().equals("(")) {
                    stack.pop(); // remove (
                }
            } else if (Registry.contains(token)) {
                while (!stack.isEmpty()
                        && !stack.peek().equals("(")
                        && Registry.get(stack.peek()).getPrecedence() >= Registry.get(token).getPrecedence()) {
                    output.add(stack.pop());
                }
                stack.push(token);
            } else {
                throw new RuntimeException("알 수 없는 연산자: " + token);
            }
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }
}
