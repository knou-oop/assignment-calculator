package com.knou.domain;

import com.knou.exception.CalculatorException;
import com.knou.exception.LogFileWriteException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogWriter {
    private static final String LOG_FILE_PATH = "src/main/resources/logFiles/";
    /**
     * 계산 기록을 파일에 저장하는 메서드
     * @param logs 저장되어있는 계산기록
     */
    public String writeLog(ArrayList<Log> logs) throws CalculatorException {
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss"));
        String extension = ".txt";
        String logFilePath = LOG_FILE_PATH+"Log_"+fileName+extension;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath,true))){
            for (Log log:logs) {
                writer.write(log.getHistoryLog()+"\n");
            }
            return logFilePath;
        }catch (IOException e) {
            throw new LogFileWriteException();
        }
    }


    /**
     * 로그 파일에서 기록을 읽어오는 메서드
     * @return 저장된 모든 로그 기록
     */
    public List<String> readLogs(String logFilePath) {
        List<String> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logs.add(line);
            }
        } catch (IOException e) {
            System.err.println("로그 파일 읽기 실패: " + e.getMessage());
        }
        return logs;
    }




}
