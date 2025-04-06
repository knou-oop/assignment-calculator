package com.knou.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * 로그 기록 관련 클래스
 */
public class Log {

    /**
     * 수식을 입력받아 로그 파일에 저장하는 메서드 
     * @param formula 계산식
     * @param backFormula 후위식
     * @param result 결과값
     * @return true: 정상 저장, false: 저장 실패
     */
    public static boolean writeLogFile(String formula, String backFormula, String result){
        File file = new File(System.getProperty("user.dir") + File.separator +Setting.getPropery("LOG_FILE_NAME"));
        System.out.println(file.getAbsolutePath());
        
        String content = "formula(계산식):" + formula + ", back-end formula(후위식):" + backFormula + ", result(결과 값):" + result;

        try (BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8))) {
            // 파일이 없으면 생성
            if (!file.exists()) {
                file.createNewFile();
            }
            // 문자열을 파일에 저장
            writer.write(content);
            writer.newLine(); // 줄바꿈 추가
        } catch (IOException e) {
            System.err.println("파일 저장 중 오류 발생: " + e.getMessage());
            return false;
        }
        return true;
    }
}
