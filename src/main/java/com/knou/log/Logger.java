package com.knou.log;

public interface Logger {

    void logError(String message);

    void logWarm(String message);

    void logInfo(String message);

    void logDebug(String message);

    String getLog();
}
