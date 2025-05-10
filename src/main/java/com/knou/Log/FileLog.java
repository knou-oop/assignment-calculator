package com.knou.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import com.knou.Log.Interface.Log;
import com.knou.Util.Setting;
import com.knou.Util.TimeUtil;

/**
 * 로그 데이터를 파일에 저장하는 클래스
 */
public class FileLog implements Log{
    
    private File file;

    public FileLog(){
        String path = Setting.getPropery("log.file.path");
        this.file = new File(
            path.equalsIgnoreCase("default") ? System.getProperty("user.dir") : path, Setting.getPropery("log.file.name")
        );
    }


    /**
     * 수식을 입력받아 로그 파일에 저장하는 메서드 
     * @param formula 계산식
     * @param backFormula 후위식
     * @param result 결과값
     * @return true: 정상 저장, false: 저장 실패
     */
    public boolean writeLog(String formula, String backFormula, String result){
        String time = TimeUtil.getNow();
        boolean rt=false;
        String content = "formula(계산식):" + formula + ", back-end formula(후위식):" + backFormula + ", result(결과 값):" + result +", 저장 시간:"+time;

        try (BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8))) {
            // 파일이 없으면 생성
            if (!file.exists()) {
                file.createNewFile();
            }
            
            // 문자열을 파일에 저장
            writer.write(content);
            writer.newLine(); // 줄바꿈 추가
            rt=true;
        } catch (IOException e) {
            System.err.println("파일 저장 중 오류 발생: " + e.getMessage());
        }
        System.out.println("파일에 로그 저장 완료!");
        return rt;
    }
}
