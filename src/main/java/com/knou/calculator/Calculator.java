package com.knou.calculator;

import com.knou.calculator.operator.OperatorRegistry;
import com.knou.calculator.token.Token;
import com.knou.calculator.token.Tokenizer;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Calculator {
    private final Set<String> operations = Set.of("+", "-", "*", "/", "0");

    public void printMenu() {
        System.out.println("===== 문자열 계산기 =====");
        System.out.println("1. 계산식 입력");
        System.out.println("2. 연산자 등록");
        System.out.println("3. 종료");
        System.out.println("======================");
    }

    public void printCalculateMenu() {
        System.out.println("===== 1. 계산식 입력 =====");
        System.out.println("계산식을 입력해주세요 예) 1,2 또는 (1,2:3)");
        System.out.println("이전 메뉴로 돌아가기: 0");
        System.out.println("=======================");
    }

    public void printRegisterOperatorMenu() {
        System.out.println("===== 2. 연산자 등록 =====");
        System.out.println("등록할 연산자를 한 글자 입력해주세요. 예) ! 또는 @");
        System.out.println("이전 메뉴로 돌아가기: 0");
        System.out.println("=======================");
    }

    public void printRegisterOperationMenu() {
        System.out.println("===== 2. 연산자 등록 =====");
        System.out.println("등록할 연산을 입력해주세요.");
        System.out.println("+ - * / 중 1가지");
        System.out.println("이전 메뉴로 돌아가기: 0");
        System.out.println("=======================");
    }

    public void calculate(Scanner sc) {
        printCalculateMenu();
        String input = sc.nextLine().trim();
        if (input.equals("0")) return;

        List<Token> tokens = Tokenizer.tokenize(input);
        List<Token> postfix = Converter.toPostfix(tokens);
        double result = Evaluator.evaluate(postfix);

        String result_string = Formatter.format(result);
        System.out.println("결과: " + result_string);
        Logger.log(input, result_string);
    }

    public String getInputSymbol(Scanner sc){
        while (true) {
            printRegisterOperatorMenu();
            String symbol = sc.nextLine().trim();
            if (symbol.length() != 1) {
                System.out.println("잘못된 입력입니다! 연산자로 사용할 문자 한 글자만 입력해주세요!");
            } else if (Character.isDigit(symbol.charAt(0)) && !symbol.equals("0")) {
                System.out.println("숫자는 연산자로 사용할 수 없습니다! 연산자로 사용할 문자 한 글자만 입력해주세요!");
            } else if (OperatorRegistry.contains(symbol)) {
                System.out.println("이미 등록된 연산자입니다!");
            } else {
                return symbol;
            }
        }
    }

    public String getInputOperation(Scanner sc) {
        while (true) {
            printRegisterOperationMenu();
            String operation = sc.nextLine().trim();
            if (operations.contains(operation)) {
                return operation;
            }
            System.out.println("잘못된 입력입니다! 알맞은 연산자를 입력해주세요!");
        }
    }

    public void registerOperator(Scanner sc){
        String symbol = getInputSymbol(sc);
        if (symbol.equals("0")) return;

        String operation = getInputOperation(sc);
        if (operation.equals("0")) return;

        OperatorRegistry.registerCustom(symbol, operation);
        System.out.println(symbol + "는 " + operation + "연산으로 등록됩니다.");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> calculate(sc);
                case "2" -> registerOperator(sc);
                case "3" -> {
                    System.out.println("계산기를 종료합니다.");
                    return;
                }

                default -> System.out.println("잘못된 입력입니다. 1, 2, 3 중 하나를 입력하세요!");
            }
        }
    }
}
