package com.knou.domain;

import com.knou.common.CalculatorException;
import com.knou.domain.exception.LogFileReadException;
import com.knou.domain.exception.LogFileWriteException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogFile {
    public void writeCalculationHistory(ArrayList<Log> logs, String logFilePath) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath,true))){
            for (Log log: logs) {
                writer.write(log.getHistoryLog()+"\n");
            }
        }catch (IOException e) {
            throw new LogFileWriteException();
        }
    }

    /**
     * 로그 파일에서 기록을 읽어오는 메서드
     * @return 저장된 모든 로그 기록
     */
    public ArrayList<String> readLogs(String logFilePath) {
        ArrayList<String> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logs.add(line);
            }
        } catch (IOException e) {
            throw new LogFileReadException();
        }
        return logs;
    }
}
