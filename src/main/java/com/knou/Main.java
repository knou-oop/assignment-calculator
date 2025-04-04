package com.knou;

import com.knou.calculator.Calculator;
import com.knou.calculator.Registry;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Registry.register_custom(",", "+");
        Registry.register_custom(":", "+");
        calculator.run();
    }
}