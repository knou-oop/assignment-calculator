package com.knou.repository;

import com.knou.domain.Log;
import java.util.ArrayList;

public class LogRepository {
    private final ArrayList<Log> logRepository = new ArrayList<>();

    public void save(Log log) {
        logRepository.add(log);
    }

    public Log findLatestLog() {
        return logRepository.get(logRepository.size() - 1);
    }

    public ArrayList<Log> getAllLogs(){
        return new ArrayList<>(logRepository);
    }
}
