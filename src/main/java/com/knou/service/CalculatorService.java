package com.knou.service;

import com.knou.domain.Calculator;
import com.knou.domain.Log;
import com.knou.exception.CalculatorException;
import com.knou.exception.DivisionByZeroException;
import com.knou.repository.LogRepository;
import java.util.ArrayList;

/**
 * 계산기 서비스 클래스
 * 계산 로직과 저장소 접근을 담당하는 중간 계층
 */
public class CalculatorService {
    private final LogRepository logRepository;

    /**
     * CalculatorService 생성자
     * @param logRepository 계산 기록을 저장할 저장소 객체
     */
    public CalculatorService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    /**
     * 계산식을 계산하고 결과를 저장하는 메서드
     * @return 계산 과정과 계산결과가 들어있는 log
     */
    public Log calculateExpression(ArrayList<String> expression) throws CalculatorException {
        Calculator calculator = new Calculator(expression);
        Log log = Log.createLog(expression);
        logRepository.save(calculator.calculateStart(log));
        return logRepository.findLatestLog();
    }

    /**
     * 모든 계산 기록을 조회하는 메서드
     * @return 저장된 모든 계산 기록
     */
    public ArrayList<Log> getAllHistory() {
        return logRepository.getAllLogs();
    }
}
