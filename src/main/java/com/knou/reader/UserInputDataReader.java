package com.knou.reader;

import com.knou.log.Logger;

import java.util.Scanner;

public class UserInputDataReader implements DataReader {

    private final Logger logger;

    public UserInputDataReader(Logger logger) {
        this.logger = logger;
    }

    @Override
    public String readData() {
        logger.logInfo("Reading data from user input.");
        Scanner scanner = new Scanner(System.in);
        final String input = scanner.nextLine();
        logger.logInfo("User Input received: " + input);
        return input;
    }
}
