package com.knou.controller;

import com.knou.domain.Log;
import com.knou.exception.DivisionByZeroException;
import com.knou.service.CalculatorService;
import com.knou.view.View;
import java.util.ArrayList;

/**
 * 계산기 프로그램의 컨트롤러
 * View와 Service 계층을 연결하고 프로그램의 흐름을 제어
 */
public class CalculatorController {
    private final View view;
    private final CalculatorService calculatorService;

    public CalculatorController(View view, CalculatorService calculatorService) {
        this.view = view;
        this.calculatorService = calculatorService;
    }

    /**
     * 계산기 프로그램의 메인 실행 메서드
     */
    public void calculatorRun() {
        try{
            // 사용자에게 계산식 입력받기
            ArrayList<String> expression = view.inputCalculator();
            Log log = calculatorService.calculateExpression(expression);
            view.displayResult(log);
            if(view.shouldContinue()){
                calculatorRun();
            }else {
                ArrayList<Log> allHistory = calculatorService.getAllHistory();
                view.displayAllHistory(allHistory);
            }
        }catch (DivisionByZeroException e){
            System.out.println("0으로 나눌 수 없습니다. 다시 처음부터 입력해주세요.");
            calculatorRun();
        }

    }
}
