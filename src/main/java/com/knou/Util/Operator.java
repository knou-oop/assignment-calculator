package com.knou.Util;

/**
 * 연산자 우선 순위 비교 관련 열거형 클래스
 */
public enum Operator {
    NOT_OPTR("!",-1), // 연산자가 아님
    ADD(Setting.getPropery("ADD_OPTR"),0),
    MINUS(Setting.getPropery("MIN_OPTR"),0),
    MUL(Setting.getPropery("MUR_OPTR"),1),
    DIV(Setting.getPropery("DIV_OPTR"),1);

    private String operator;
    private int level; // 연산자 우선순위. 값이 작을수록 우선순위가 낮음

    public int getLevel(){
        return this.level;
    }

    public String getOperator(){
        return this.operator;
    }

    private Operator(String operator, int level){
        this.operator = operator;
        this.level = level;
    }


    /**
     * operator를 입력받아 해당 연산자와 똑같은 열거형으로 반환해주는 메서드
     * @param operator 연산자
     * @return NOT_OPTR: 입력받은 연산자에 해당하는 열거형이 없음, default: 해당 연산자에 해당하는 열거형
     */
    public static Operator conversionStringToOperator(String operator) {
        Operator op;
        if(operator == null){
            op = Operator.NOT_OPTR;
        }else if (operator.equals(Setting.getPropery("ADD_OPTR"))) {
            op = Operator.ADD;
        } else if(operator.equals(Setting.getPropery("MIN_OPTR"))){
            op = Operator.MINUS;
        } else if(operator.equals(Setting.getPropery("MUL_OPTR"))){
            op = Operator.MUL;
        } else if(operator.equals(Setting.getPropery("DIV_OPTR"))){
            op = Operator.DIV;
        } else {
            op = op = Operator.NOT_OPTR;
        }
        return op;
    }
}
