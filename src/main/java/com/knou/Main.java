package com.knou;

import com.knou.calculator.Calculator;
import com.knou.calculator.operator.OperatorRegistry;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        OperatorRegistry.registerCustom(",", "+");
        OperatorRegistry.registerCustom(":", "+");
        calculator.run();
    }
}