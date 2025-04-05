package com.knou;

import java.util.Scanner;

public class UserInputDataReader implements DataReader {
    @Override
    public String readData() {
        System.out.println("input expression: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
