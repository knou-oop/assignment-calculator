package com.knou.log;

import java.time.Clock;
import java.time.LocalDateTime;

public class InMemoryLogger implements Logger {

    private final StringBuilder log;
    private final Clock clock;

    public InMemoryLogger(Clock clock) {
        this.log = new StringBuilder();
        this.clock = clock;
    }

    @Override
    public void logError(String message) {
        log.append(this.formatLog(LogLevel.ERROR, message));
        System.out.println("ERROR: " + message);
    }

    @Override
    public void logWarm(String message) {
        log.append(this.formatLog(LogLevel.WARN, message));
        System.out.println("WARNING: " + message);
    }

    @Override
    public void logInfo(String message) {
        log.append(this.formatLog(LogLevel.INFO, message));
        System.out.println("INFO: " + message);
    }

    @Override
    public void logDebug(String message) {
        log.append(this.formatLog(LogLevel.DEBUG, message));
        System.out.println("DEBUG: " + message);
    }

    public String getLog() {
        return log.toString();
    }

    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now(clock);
    }

    private String formatLog(LogLevel level, String message) {
        return level + " " + getCurrentTime() + " " + message + "\n";
    }
}