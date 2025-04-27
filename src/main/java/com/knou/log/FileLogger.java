package com.knou.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Clock;
import java.time.LocalDateTime;

public class FileLogger implements Logger {

    private final File logFile;
    private final Clock clock;

    public FileLogger(String filePath, Clock clock) {
        this.logFile = new File(filePath);
        this.clock = clock;
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create log file", e);
        }
    }

    @Override
    public void logError(String message) {
        String formatted = formatLog(LogLevel.ERROR, message);
        writeToFile(formatted);
        System.out.println("ERROR: " + message);
    }

    @Override
    public void logWarm(String message) {
        String formatted = formatLog(LogLevel.WARN, message);
        writeToFile(formatted);
        System.out.println("WARNING: " + message);
    }

    @Override
    public void logInfo(String message) {
        String formatted = formatLog(LogLevel.INFO, message);
        writeToFile(formatted);
        System.out.println("INFO: " + message);
    }

    @Override
    public void logDebug(String message) {
        String formatted = formatLog(LogLevel.DEBUG, message);
        writeToFile(formatted);
        System.out.println("DEBUG: " + message);
    }

    @Override
    public String getLog() {
        try {
            return Files.readString(logFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read log file", e);
        }
    }

    private void writeToFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to log file", e);
        }
    }

    private String formatLog(LogLevel level, String message) {
        return level + " " + getCurrentTime() + " " + message + "\n";
    }

    private LocalDateTime getCurrentTime() {
        return LocalDateTime.now(clock);
    }
}
