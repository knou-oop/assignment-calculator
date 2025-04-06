package com.knou;

import com.knou.controller.CalculatorController;
import com.knou.repository.LogRepository;
import com.knou.service.CalculatorService;
import com.knou.view.InputValidator;
import com.knou.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new View(new InputValidator());
        CalculatorService calculatorService = new CalculatorService(new LogRepository());
        CalculatorController calculatorController = new CalculatorController(view, calculatorService);
        calculatorController.calculatorRun();
    }
}