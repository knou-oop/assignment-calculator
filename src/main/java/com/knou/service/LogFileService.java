package com.knou.service;

import com.knou.common.CalculatorException;
import com.knou.common.ExceptionHandler;
import com.knou.domain.Log;
import com.knou.domain.LogFile;
import com.knou.exception.LogFileDeleteException;
import com.knou.domain.exception.LogFileReadException;
import com.knou.domain.exception.LogFileWriteException;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class LogFileService {
    private static final int MAX_LOG_FILES = 5;
    private static final String LOG_DIRECTORY = "src/main/resources/logFiles/";
    private final File logDirectory = new File(LOG_DIRECTORY);
    private final LogFile logFile = new LogFile();

    public void cleanUpOldLogFiles() {
        //1. 디렉토리의 모든 로그 파일 목록 조회
        File[] logFiles = logDirectory.listFiles(((dir, name) -> name.endsWith(".txt")));
        //2. 파일들을 수정 시간 순으로 정렬
        try {
            if (logFiles != null && logFiles.length > MAX_LOG_FILES) {
                Arrays.sort(logFiles, Comparator.comparingLong(File::lastModified));
                int filesToDelete = logFiles.length - MAX_LOG_FILES;
                for (int i = 0; i < filesToDelete; i++) {
                    if (!logFiles[i].delete()) {
                        throw new LogFileDeleteException();
                    }
                }
            }
        } catch (LogFileWriteException e) {
            ExceptionHandler.getInstance().handleCalculatorException(e);
        }
    }

    /**
     * 로그파일 디렉토리에 파일이 존재하는지 확인
     *
     * @return 존재하면 true
     */
    public boolean hasLogFiles() {
        File[] logFiles = logDirectory.listFiles(((dir, name) -> name.endsWith(",txt")));
        return logFiles != null;
    }

    /**
     * 로그파일 디렉토리에 파일이 몇개인지 확인
     *
     * @return 파일개수
     */
    public Integer getLodFileLength() {
        File[] logFiles = logDirectory.listFiles(((dir, name) -> name.endsWith(",txt")));
        try {
            if (logFiles == null) {
                throw new LogFileReadException();
            }
            return logFiles.length;
        } catch (LogFileReadException e) {
            ExceptionHandler.getInstance().handleCalculatorException(e);
        }
        //파일 없음 간주
        return null;

    }

    /**
     * 로그파일 주소 생성
     * return logFilePath
     */
    public String createLogFilePath() throws CalculatorException {
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss"));
        String extension = ".txt";
        return LOG_DIRECTORY+"Log_"+fileName+extension;
    }

    /**
     * 로그 파일에서 기록을 읽어오는 메서드
     * @return 저장된 모든 로그 기록
     */
    public ArrayList<String> readLogs(String logFilePath) {
        return logFile.readLogs(logFilePath);
    }

    /**
     * 계산결과를 로그파일에 저장
     * @param allHistory 모든 계산 기록이 담긴 리스트
     * return createLogFilePath 생성된 로그파일 path
     */
    public void writeCalculationHistory(ArrayList<Log>  allHistory,String logFilePath) {
        logFile.writeCalculationHistory(allHistory, logFilePath);
    }

}
