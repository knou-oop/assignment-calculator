package com.knou.calculator.token;

public class ParenthesisToken extends Token {
    public ParenthesisToken(String symbol) {
        this.type = TokenType.PARENTHESIS;
        this.symbol = symbol;
    }
}
