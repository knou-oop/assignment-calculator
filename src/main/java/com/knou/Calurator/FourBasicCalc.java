package com.knou.Calurator;

/**
 * 사칙연산 관련 클래스
 */
public class FourBasicCalc {
    
    /**
     * 덧셈 연산 수행
     * @param number1 피연산자 1
     * @param number2 피연산자 2
     * @return
     */
    public String add(String number1, String number2) {
        double num1 = Double.parseDouble(number1);
        double num2 = Double.parseDouble(number2);
        return String.valueOf(num1 + num2);
    }

    /**
     * 뺄셈 연산 수행
     * @param number1 피연산자 1
     * @param number2 피연산자 2
     * @return
     */
    public String minus(String number1, String number2) {
        double num1 = Double.parseDouble(number1);
        double num2 = Double.parseDouble(number2);
        return String.valueOf(num1 - num2);
    }

    /**
     * 곱셈 연산 수행
     * @param number1 피연산자 1
     * @param number2 피연산자 2
     * @return
     */
    public String mul(String number1, String number2) {
        double num1 = Double.parseDouble(number1);
        double num2 = Double.parseDouble(number2);
        return String.valueOf(num1 * num2);
    }

    /**
     * 나눗셈 연산 수행
     * @param number1 피연산자 1
     * @param number2 피연산자 2
     * @return
     */
    public String div(String number1, String number2) {
        double num1 = Double.parseDouble(number1);
        double num2 = Double.parseDouble(number2);
        return String.valueOf(num1 / num2);
    }

}
