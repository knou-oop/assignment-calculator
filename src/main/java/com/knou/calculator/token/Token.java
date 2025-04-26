package com.knou.calculator.token;

public abstract class Token {
    protected TokenType type;
    protected String symbol;
    protected double value;

    public boolean isOperator() { return type.isOperator(); }
    public boolean isOperand() { return type.isOperand(); }
    public boolean isParenthesis() { return type.isParenthesis(); }
    public boolean isLeftParenthesis() { return isParenthesis() && symbol.equals("("); }
    public boolean isRightParenthesis() { return isParenthesis() && symbol.equals(")"); }
    public String getSymbol() { return symbol; }
    public double getValue() { return value; }
}
