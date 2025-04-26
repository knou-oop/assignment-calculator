package com.knou.calculator.token;

public class OperatorToken extends Token {
    public OperatorToken(String symbol) {
        this.type = TokenType.OPERATOR;
        this.symbol = symbol;
    }
}
