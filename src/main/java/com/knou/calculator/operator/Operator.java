package com.knou.calculator.operator;

import java.util.function.BiFunction;

public class Operator {
    private final String symbol;
    private final int precedence;
    private final BiFunction<Double, Double, Double> function;

    public Operator(String symbol, int precedence, BiFunction<Double, Double, Double> function){
        this.symbol = symbol;
        this.precedence = precedence;
        this.function = function;
    }

    public String getSymbol(){
        return symbol;
    }

    public int getPrecedence(){
        return precedence;
    }

    public double apply(double a, double b){
        return function.apply(a, b);
    }
}
