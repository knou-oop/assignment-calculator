package com.knou.Service;

import java.util.ArrayList;
import java.util.List;

import com.knou.Calurator.FourBasicCalc;
import com.knou.Process.Preprocessing;
import com.knou.Util.Log;
import com.knou.Util.Setting;

/**
 * 계산 로직 수행 클래스
 */
public class Calculate {
    private String formula;
    private Preprocessing preprocessing;
    private FourBasicCalc fourBasicCalc;

    public Calculate(String formula) {
        this.formula = formula;
        this.preprocessing = new Preprocessing();
        this.fourBasicCalc = new FourBasicCalc();
    }

    /**
     * 계산에 필요한 전처리, 계산, 로그 기록까지의 로직을 정리한 메서드
     * @return true: 정상 처리, false: 비정상 처리
     */
    public boolean startOperator(){
        List<String> formulaList; // 계산 리스트
        List<String> backFomulaList = new ArrayList<>();// 후위식 저장 리스트

        formulaList = preprocessing.conversionStringToList(formula);
        formulaList = preprocessing.conversionFormualToBackFormula(formulaList);
        backFomulaList.addAll(formulaList);
        String result = calc(formulaList);
        // 포맷 변환
        result = changeFormat(result);
        Log.writeLogFile(formula, backFomulaList.toString(), result);
        return false;
    }

    /**
     * 후위식으로 변환된 List<String> 타입의 수식을 읽어 연산자에 맞는 알맞은 계산을 실행하는 메서드
     * @param backFormulaList List<String> 타입의 후위식 리스트
     * @return 계산된 값
     */
    public String calc(List<String> backFormulaList){
        String result;
        for(int i=0; i < backFormulaList.size(); i++){
            String obj = backFormulaList.get(i);
            if (obj.equals("+")) {
                result = fourBasicCalc.add(backFormulaList.get(i-2), backFormulaList.get(i-1));
            } else if (obj.equals("-")) {
                result = fourBasicCalc.minus(backFormulaList.get(i-2), backFormulaList.get(i-1));
            } else if (obj.equals("*")) {
                result = fourBasicCalc.mul(backFormulaList.get(i-2), backFormulaList.get(i-1));
            } else if (obj.equals("/")) {
                result = fourBasicCalc.div(backFormulaList.get(i-2), backFormulaList.get(i-1));
            } else{
                continue;
            }
            backFormulaList.set(i-2, result);
            backFormulaList = reorder(backFormulaList, i-1, 2);
            i=i-2;
        }
        result = backFormulaList.get(0);
        return result;
    }

    /**
     * 계산 후 리스트 정리해주는 메서드
     * @param backFormulaList 정리할 List<String>
     * @param removeIndex 제거할 기준 인덱스
     * @param removeCnt 제거할 횟수
     * @return 정리된 List<String>
     */
    public List<String> reorder(List<String> backFormulaList, int removeIndex, int removeCnt){
        for (int i = 0; i < removeCnt; i++) {
            backFormulaList.remove(removeIndex);
        }
        return backFormulaList;
    }

    /**
     * 전달받은 값이 정수인지 확인하는 메서드
     * @param number 확인할 수
     * @return true: 입력받은 값이 정수, false: 입력받은 값이 실수
     */
    public boolean isInteger(Double number){
        if (number % 1 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 전달받은 String 타입의 수를 정수, 실수로 구분하여 각 포멧에 맞도록 포맷 변경하는 메서드
     * @param result
     * @return
     */
    public String changeFormat(String result){
        String roundingDigits = "%." + Setting.getPropery("ROUNDING_DIGITS") + "f";
        if (isInteger(Double.parseDouble(result))) {
            return String.format("%.0f", Double.parseDouble(result));
        }else {
            return String.format(roundingDigits, Double.parseDouble(result));
        }
    }
}
