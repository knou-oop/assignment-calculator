package com.knou.Log.Interface;

/**
 * 로그 기록 관련 인터페이스
 */
public interface Log {

    /**
     * 수식을 입력받아 로그를 저장하는 메서드 
     * @param formula 계산식
     * @param backFormula 후위식
     * @param result 결과값
     * @return true: 정상 저장, false: 저장 실패
     */
    public boolean writeLog(String formula, String backFormula, String result);
}
