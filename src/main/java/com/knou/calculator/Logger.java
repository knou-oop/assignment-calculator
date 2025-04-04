package com.knou.calculator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FILE = "logs/calculator.log";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static BufferedWriter writer;

    static {
        try{
            new File("logs").mkdirs();
            writer = new BufferedWriter(new FileWriter(LOG_FILE, true));
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try{
                    if (writer != null) writer.close();
                } catch (IOException e) {
                    System.err.println("Logger 종료 중 오류 발생: " + e.getMessage());
                }
            }));
        } catch (IOException e) {
            System.err.println("Logger 초기화 중 오류 발생: " + e.getMessage());

        }
    }

    public static void log(String expression, String result) {
        try {
            String timestamp = LocalDateTime.now().format(FORMATTER);
            writer.write(String.format("[%s] %s = %s", timestamp, expression, result));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("로그 파일에 기록하는 중 오류 발생: " + e.getMessage());
        }
    }
}
