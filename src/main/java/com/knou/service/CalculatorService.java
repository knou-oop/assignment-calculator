package com.knou.service;

import com.knou.reader.DataReader;
import com.knou.log.Logger;

public class CalculatorService {

    private DataReader reader;
    private final Logger logger;
    private Calculator calculator;

    public CalculatorService(Logger logger) {
        this.logger = logger;
    }


    public void setReader(DataReader reader) {
        this.reader = reader;
        this.createCalculator();
    }

    public void calculate() {
        if (reader == null) {
            throw new IllegalStateException("DataReader is not set.");
        }
        this.calculator.calculate();
    }

    private void createCalculator() {
        this.calculator = new Calculator(this.reader, this.logger);
    }

    public void readData() {
        if (calculator == null) {
            throw new IllegalStateException("Calculator is not created.");
        }
        this.calculator.readData();
    }


    public void printResult() {
        if (calculator == null) {
            throw new IllegalStateException("Calculator is not created.");
        }
        this.calculator.printResult();
    }
}
