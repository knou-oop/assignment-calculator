package com.knou.calculator.token;

import com.knou.calculator.operator.OperatorRegistry;

public class TokenFactory {

    public static Token createToken(String rawToken) {
        TokenType type = classify(rawToken);
        return type.create(rawToken);
    }

    private static TokenType classify(String rawToken) {
        if (rawToken.matches("\\d+")) {
            return TokenType.OPERAND;
        } else if (OperatorRegistry.contains(rawToken)) {
            return TokenType.OPERATOR;
        } else if (rawToken.equals("(") || rawToken.equals(")")) {
            return TokenType.PARENTHESIS;
        } else {
            throw new IllegalArgumentException("알 수 없는 토큰: " + rawToken);
        }
    }
}