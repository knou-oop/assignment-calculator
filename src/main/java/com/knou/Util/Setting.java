package com.knou.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import com.knou.Main;

/**
 * application.properties 초기화 및 설정 값 사용 관련 클래스
 */
public class Setting {
    private static Properties properties = new Properties();

    /**
     * resources에 존재하는 application.properties 설정 파일을 읽어오는 메서드
     */
    public static void loadProps() {
        try {
            InputStream ips = Main.class.getResourceAsStream("/application.properties");
            
            if (ips != null) {
                properties.load(new InputStreamReader(ips, StandardCharsets.UTF_8));
            } else {
                System.out.println("ConfigFile Not Load");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

    /**
     * application.properties에 작성된 설정 중 전달받은 key에 해당하는 값을 반환하는 메서드
     * @param key 반환받을 값의 키
     * @return 설정 파일 특정 키의 값
     */
    public static String getPropery(String key) {
        return properties.getProperty(key);
    }
}
