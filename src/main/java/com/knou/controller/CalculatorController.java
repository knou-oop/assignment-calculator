package com.knou.controller;

import com.knou.domain.Log;
import com.knou.service.CalculatorService;
import com.knou.view.View;
import java.util.ArrayList;

public class CalculatorController {
    private final View view;
    private final CalculatorService calculatorService;

    public CalculatorController(View view, CalculatorService calculatorService) {
        this.view = view;
        this.calculatorService = calculatorService;
    }

    public void calculatorRun() {
        ArrayList<String> expression = view.inputCalculator();
        Log log = calculatorService.calculateExpression(expression);
        view.displayResult(log);
        if(view.shouldContinue()){
            calculatorRun();
        };
    }
}
