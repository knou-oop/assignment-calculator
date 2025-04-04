package com.knou.calculator;

import java.util.HashMap;
import java.util.Map;


public class Registry {
    private static final Map<String, Operator> operators = new HashMap<>();

    static {
        register(new Operator("+", 1, (a, b) -> a + b));
        register(new Operator("-", 1, (a, b) -> a - b));
        register(new Operator("*", 2, (a, b) -> a * b));
        register(new Operator("/", 2, (a, b) -> a / b));
    }

    public static void register(Operator op) {
        operators.put(op.getSymbol(), op);
    }

    public static void register_custom(String symbol, String operator){
        operators.put(symbol, get(operator));
    }

    public static Operator get(String symbol) {
        return operators.get(symbol);
    }

    public static boolean contains(String symbol) {
        return operators.containsKey(symbol);
    }
}
