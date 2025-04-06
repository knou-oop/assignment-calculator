package com.knou.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.knou.Util.Operator;
import com.knou.Util.Setting;


/**
 * 계산에 필요한 전처리 관련 클래스
 */
public class Preprocessing {

    /**
     * 문자열 수식을 전달받아 List<String> 형식의 수식 리스트로 변환해주는 메서드
     * @param formula 문자열 수식
     * @return 리스트로 변환된 수식
     */
    public List<String> conversionStringToList(String formula){
        List<String> numbers = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : formula.toCharArray()) {
            if (isOPTR(c)) {
                // sb가 유효한 수인지 확인하는 함수 넣어야됨 inInvalid(sb.toString());
                numbers.add(sb.toString());
                numbers.add(String.valueOf(c));
                sb.delete(0, sb.length());
                continue;
            } else if(isNumber(c)) {
                sb.append(c);
            } else{
                continue;
            }
        }
        numbers.add(sb.toString());
        return numbers;
    }


    /**
     * List<String> 타입의 수식을 전달받아 후위식 List<String> 타입으로 변환하는 메서드
     * @param formulaString List<String> 타입의 수식 리스트
     * @return 후위식으로 변환된 수식
     */
    public List<String> conversionFormualToBackFormula(List<String> formulaString){
        List<String> backFormula = new Stack<>();
        Stack operStack = new Stack<>();
        for(int i=0; i < formulaString.size(); i++){
            String str = formulaString.get(i);
            if (isOPTR(str)) {
                int operatorRank;
                // operStack이 비어있는 상태에서 operStack.peek() 호출시 에러 발생. 확인 과정 필요
                if(operStack.isEmpty()){
                    operatorRank = comparisonOperatorRank(null, str);
                } else {
                    operatorRank = comparisonOperatorRank(String.valueOf(operStack.peek()), str);
                }

                // 스택이 비었다. || 스택에 존재하는 연산자의 우선순위가 낮다.
                if (operStack.empty() || operatorRank == 2) {
                    operStack.push(str);
                }
                // 스택에 존재하는 연산자와 새로운 연산자의 우선 순위가 같다 || 새로운 연산자의 우선 순위가 낮다
                else if(operatorRank == 0 || operatorRank == 1){
                    backFormula.add(String.valueOf(operStack.pop()));
                    operStack.push(str);
                }
            } else {
                backFormula.add(str);
            }
        }
        // 스택에 남아있는 연산자 모두 pop
        int size = operStack.size();
        for (int i=0; i < size; i++) {
            backFormula.add(String.valueOf(operStack.pop()));
        }
        return backFormula;
    }


    /**
     * List<String> 타입의 수들을 입력받아 double, Operator(열거형) 타입들로 변환한 후 List<Object> 타입으로 반환하는 메서드
     * @param oldNumbers 타입 변환할 타입의 수들
     * @return double, Operator 타입으로 변환된 리스트
     * @throws NumberFormatException 변환 실패시 발생
     */
    public List<Object> conversionNumberType(List<String> oldNumbers) throws NumberFormatException {
        List<Object> newNumbers = new ArrayList<>();
        for (String numbers : oldNumbers) {
            // 연산자인 경우
            if(numbers.length() == 1 && isOPTR(numbers)) {
                switch (numbers) {
                    case "+":
                        newNumbers.add(Operator.ADD);
                        break;
                    case "-":
                        newNumbers.add(Operator.MINUS);
                        break;
                    case "*":
                        newNumbers.add(Operator.MUL);
                        break;
                    case "/":
                        newNumbers.add(Operator.DIV);
                        break;
                }
            }
            // 수인 경우 모두 double로 변환
            else {
                newNumbers.add(Double.parseDouble(numbers));
            }
        }
        return newNumbers;
    }


    /**
     * 입력받은 문자가 연산자인지 확인하는 메서드
     * @param c 확인할 문자
     * @return true: 연산자('+','-','*','/' 등등) , false 연산자가 아닌 문자들
     */
    public boolean isOPTR(char c){
        if(
            String.valueOf(c).equals(Setting.getPropery("ADD_OPTR")) ||
            String.valueOf(c).equals(Setting.getPropery("MIN_OPTR")) ||
            String.valueOf(c).equals(Setting.getPropery("MUL_OPTR")) ||
            String.valueOf(c).equals(Setting.getPropery("DIV_OPTR"))
        ){
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * 입력받은 문자가 연산자인지 확인하는 메서드
     * @param c 확인할 문자열
     * @return true: 연산자('+','-','*','/' 등등이 포함되어 있음) , false: 연산자가 아닌 문자들
     */
    public boolean isOPTR(String c){
        if(
            c.equals(Setting.getPropery("ADD_OPTR")) ||
            c.equals(Setting.getPropery("MIN_OPTR")) ||
            c.equals(Setting.getPropery("MUL_OPTR")) ||
            c.equals(Setting.getPropery("DIV_OPTR"))
        ){
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * 입력받은 문자가 숫자인지 확인하는 함수
     * @param c 확인할 문자
     * @return true: 숫자( 0 ~ 9, '.') , false 숫자가 아닌 문자들
     */
    public boolean isNumber(char c) {
        if ( 48 <= c && c <= 57 || c == 46) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 두 연산자의 우선순위를 비교하여 결과 반환
     * @param operator1 연산자 1
     * @param operator2 연산자 2
     * @return -1: 둘 다 해당하는 연산자가 없는 경우 && 비교 불가, 0: 두 연산자의 우선 순위가 같은 경우, 1: optr1의 우선 순위가 높은 경우, 2: optr2의 우선 순위가 높은 경우
     */
    public int comparisonOperatorRank(String operator1, String operator2) {
        Operator optr1 = Operator.conversionStringToOperator(operator1);
        Operator optr2 = Operator.conversionStringToOperator(operator2);
        
        // 둘 다 문자열에 해당하는 연산자가 없다.
        if (optr1.getLevel() == -1 && optr2.getLevel() == -1) {
            return -1;
        }
        // 연산자 1의 우선순위가 더 높다
        else if(optr1.getLevel() > optr2.getLevel()) {
            return 1;
        }
        // 연산자 2의 우선순위가 더 높다
        else if (optr1.getLevel() < optr2.getLevel()) {
            return 2;
        }
        // 두 연산자의 우선 순위가 같다
        else if (optr1.getLevel() == optr2.getLevel()){
            return 0;
        } 
        // 비교 불가
        else {
            return -1;
        }
    }

}
