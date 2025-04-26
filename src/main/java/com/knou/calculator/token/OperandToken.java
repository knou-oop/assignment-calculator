package com.knou.calculator.token;

public class OperandToken extends Token {
    public OperandToken(double value) {
        this.type = TokenType.OPERAND;
        this.value = value;
    }
}
