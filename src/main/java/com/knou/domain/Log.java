package com.knou.domain;

import java.util.ArrayList;

/**
 * 계산 기록을 저장하는 클래스
 * 계산식, 계산 과정, 결과를 저장하고 관리
 */
public class Log {
    private final String expression;
    private final ArrayList<String> calculateHistory;
    private String result;

    /**
     * Log 생성자
     * @param expression 계산식
     */
    private Log(String expression) {
        this.expression = expression;
        this.result = "";
        this.calculateHistory = new ArrayList<>();
    }

    /**
     * Log 객체를 생성하는 정적 팩토리 메서드
     * @param expression 계산식의 각 요소가 담긴 리스트
     * @return 생성된 Log 객체
     */
    public static Log createLog(ArrayList<String> expression) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : expression) {
            stringBuilder.append(s);
        }
        stringBuilder.append("=");
        return new Log(stringBuilder.toString());
    }

    /**
     * 계산 결과를 설정하는 메서드
     * @param result 계산 결과
     */
    public void generateResult(String result) {
        this.result = result;
    }

    /**
     * 계산 결과를 반환하는 메서드
     * @return 계산 결과
     */
    public String getResult() {
        return result;
    }

    /**
     * 계산 과정을 기록하는 메서드
     * @param number1 첫 번째 피연산자
     * @param number2 두 번째 피연산자
     * @param operator 연산자
     * @param result 계산 결과
     */
    public void addHistory(String number1, String number2, String operator, String result) {
        this.calculateHistory.add(number1 + operator + number2 + " = " + result);
    }

    /**
     * 계산식을 반환하는 메서드
     * @return 계산식
     */
    public String getExpression() {
        return expression;
    }

    /**
     * 계산 과정을 반환하는 메서드
     * @return 계산 과정이 담긴 리스트
     */
    public ArrayList<String> getCalculateHistory() {
        return calculateHistory;
    }
}
