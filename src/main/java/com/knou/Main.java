package com.knou;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(new UserInputDataReader());
//        Calculator calculator = new Calculator(new StaticDataReader());

        try {
            calculator.readData();
            calculator.calculate();
            calculator.printResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}