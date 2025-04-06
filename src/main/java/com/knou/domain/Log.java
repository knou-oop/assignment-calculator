package com.knou.domain;

import java.util.ArrayList;

public class Log {
    private final String expression;
    private final ArrayList<String> calculateHistory;
    private String result;

    private Log(String expression) {
        this.expression = expression;
        this.result = "";
        this.calculateHistory = new ArrayList<>();
    }

    public static Log createLog(ArrayList<String> expression) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : expression) {
            stringBuilder.append(s);
        }
        stringBuilder.append("=");
        return new Log(stringBuilder.toString());
    }

    public void generateResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void addHistory(String number1, String number2, String operator, String result) {
        this.calculateHistory.add(number1 + operator + number2 + " = " + result);
    }

    public String getExpression() {
        return expression;
    }

    public ArrayList<String> getCalculateHistory() {
        return calculateHistory;
    }
}
