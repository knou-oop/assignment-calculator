package com.knou.service;

import com.knou.log.Logger;
import com.knou.reader.DataReader;

import java.util.Stack;

public class Calculator {

    private final DataReader reader;
    private final Logger logger;
    private String data = null;
    private Double result = null;

    public Calculator(DataReader reader, Logger logger) {
        this.reader = reader;
        this.logger = logger;
    }


    public void readData() {
        this.data = this.reader.readData();
    }

    public void calculate() {
        if (!this.isReadData())
            throw new IllegalStateException("data not read yet");

        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        StringBuilder numBuffer = new StringBuilder();

        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);

            // 숫자 판별
            if (Character.isDigit(ch)) {
                numBuffer.append(ch);
            } else if (this.isOperator(ch)) {
                // 연산자라면
                // 숫자 버퍼에 있는 숫자를 스택에 넣기
                int num = Integer.parseInt(numBuffer.toString());
                numBuffer.setLength(0);
                numStack.push(num);

                // 연산자 우선순위 판단
                // 스택에 있는 연산자와 현재 연산자 비교
                // 스택에 있는 연산자가 현재 연산자보다 우선순위가 높거나 같다면
                // 스택에서 꺼내서 계산
                // 아니라면 현재 연산자를 스택에 넣기
                if (!opStack.isEmpty() && precedence(opStack.peek()) >= precedence(ch)) {
                    int b = numStack.pop();
                    int a = numStack.pop();
                    char op = opStack.pop();

                    int res;
                    if (op == '+') {
                        res = a + b;
                    } else if (op == '-') {
                        res = a - b;
                    } else if (op == '*') {
                        res = a * b;
                    } else if (op == '/') {
                        res = a / b;
                    } else {
                        throw new IllegalStateException("Invalid operator: " + op);
                    }
                    numStack.push(res);
                }
                // 현재 연산자를 opStack에 push
                opStack.push(ch);
            }
        }

        // 마지막 숫자 스택에 넣기
        if (!numBuffer.isEmpty()) {
            numStack.push(Integer.parseInt(numBuffer.toString()));
        }

        // 스택에 남아있는 연산들 처리
        while (!opStack.isEmpty()) {
            int b = numStack.pop();
            int a = numStack.pop();
            char op = opStack.pop();

            int res;
            if (op == '+') {
                res = a + b;
            } else if (op == '-') {
                res = a - b;
            } else if (op == '*') {
                res = a * b;
            } else if (op == '/') {
                res = a / b;
            } else {
                throw new IllegalStateException("Invalid operator: " + op);
            }
            numStack.push(res);
        }

        // 최종 결과
        this.result = (double) numStack.pop();
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    // 우선순위 판단 ( +, - 는 1 / *, / 는 2 )
    private int precedence(char op) {
        return (op == '+' || op == '-') ? 1 : 2;
    }


    private boolean isReadData() {
        return this.data != null;
    }

    private boolean isCalculated() {
        return this.result != null;
    }

    public void printResult() {
        if(!this.isCalculated())
            throw new IllegalStateException("result not calculated yet");
        logger.logInfo("result: " + this.result);
    }
}
