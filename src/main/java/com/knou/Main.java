package com.knou;

import com.knou.controller.CalculatorController;
import com.knou.domain.LogWriter;
import com.knou.repository.LogRepository;
import com.knou.service.CalculatorService;
import com.knou.view.InputValidator;
import com.knou.view.View;

/**
 * 계산기 프로그램의 진입점
 * 프로그램 실행에 필요한 객체들을 생성하고 초기화
 */
public class Main {
    /**
     * 프로그램의 메인 메서드
     * View, Service, Controller 객체를 생성하고 프로그램을 실행
     * @param args 커맨드 라인 인자 (사용하지 않음)
     */
    public static void main(String[] args) {
        View view = new View(new InputValidator(),new LogWriter());
        CalculatorService calculatorService = new CalculatorService(new LogRepository());
        CalculatorController calculatorController = new CalculatorController(view, calculatorService);
        calculatorController.calculatorRun();
    }
}