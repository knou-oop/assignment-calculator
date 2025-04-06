package com.knou.service;

import com.knou.domain.Calculator;
import com.knou.domain.Log;
import com.knou.repository.LogRepository;
import java.util.ArrayList;

public class CalculatorService {
    private final LogRepository logRepository;

    public CalculatorService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    /**
     *
     * @param expression 사용자가 입력한 계산식
     * @return 계산 과정과 계산결과가 들어있는 log
     */
    public Log calculateExpression(ArrayList<String> expression) {
        Calculator calculator = new Calculator(expression);
        Log log = Log.createLog(expression);
        logRepository.save(calculator.calculateStart(log));
        return logRepository.findLatestLog();
    }

    public ArrayList<Log> getAllHistory() {
        return logRepository.getAllLogs();
    }
}
