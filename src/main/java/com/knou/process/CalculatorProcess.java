package com.knou.process;

import com.knou.reader.DataReader;
import com.knou.reader.StaticDataReader;
import com.knou.reader.UserInputDataReader;
import com.knou.service.CalculatorService;
import com.knou.log.Logger;

import java.io.IOException;
import java.util.Scanner;

public class CalculatorProcess {

    private final CalculatorService calculatorService;
    private final Scanner scanner;
    private final Logger logger;

    public CalculatorProcess(Logger logger) {
        this.logger = logger;
        this.calculatorService = new CalculatorService(logger);
        this.scanner = new Scanner(System.in);
    }
    public void run() throws IOException {
        logger.logInfo("Starting calculator process...");
        this.selectDataReader();
        this.dataReader();
        this.calculate();
        this.printResult();
    }

    private void selectDataReader() throws IOException {
        logger.logInfo("Select data reader...");

        DataReader reader = null;
        while(true) {
            logger.logInfo("Please select data reader:");
            logger.logInfo("1. User Input");
            logger.logInfo("2. Static Data");
            String code = this.scanner.nextLine();
            if(code.equals("1")) {
                logger.logInfo("User Input selected");
                reader = new UserInputDataReader(logger);
                break;
            } else if (code.equals("2")) {
                logger.logInfo("Static Data selected");
                reader = new StaticDataReader();
                break;
            } else {
                logger.logWarm("Invalid selection. Please try again.");
            }
        }
        this.calculatorService.setReader(reader);
    }

    private void calculate() {
        logger.logInfo("Calculating...");
        this.calculatorService.calculate();
    }

    private void dataReader() {
        logger.logInfo("Reading data...");
        this.calculatorService.readData();
    }

    private void printResult() {
        this.calculatorService.printResult();
    }
}
