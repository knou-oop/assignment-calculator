package com.knou;

import com.knou.log.FileLogger;
import com.knou.process.CalculatorProcess;
import com.knou.log.InMemoryLogger;
import com.knou.clock.ClockInstance;
import com.knou.log.Logger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
//        Logger logger = new InMemoryLogger(new ClockInstance().getClock());
        Logger logger = new FileLogger("calculator.log", new ClockInstance().getClock());
        CalculatorProcess calculatorProcess = new CalculatorProcess(logger);

        try {
            while(true) {
                logger.logDebug("Starting calculator process...");
                calculatorProcess.run();
                if(logger instanceof InMemoryLogger) {
                    logger.logInfo("Do you want show log? (y/n)");
                    String code = scanner.nextLine();
                    if(code.equals("y")) {
                        System.out.println("==========");
                        logger.logInfo(logger.getLog());
                        System.out.println("==========");
                    } else if (code.equals("n")) {
                        break;
                    } else {
                        logger.logWarm("Invalid selection. Please try again.");
                    }
                }

            }
        } catch (Exception e) {
            logger.logError("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }


    }
}