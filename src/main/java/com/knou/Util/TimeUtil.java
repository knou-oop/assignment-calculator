package com.knou.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 시간 관련 유틸리티 클래스
 */
public class TimeUtil {

    /**
     * 호출 시점의 시간을 String 타입으로 반환해주는 메서드
     * @return 변환된 시간
     */
    public static String getNow(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
