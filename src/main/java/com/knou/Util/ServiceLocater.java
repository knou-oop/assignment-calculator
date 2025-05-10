package com.knou.Util;

import com.knou.Input.ArgsInput;
import com.knou.Input.ConsoleInput;
import com.knou.Input.FileInput;
import com.knou.Input.Interface.Input;
import com.knou.Log.DBLog;
import com.knou.Log.FileLog;
import com.knou.Log.Interface.Log;

public class ServiceLocater {

    private static Input inputer;
    private static Log logger;

    public static void loadService(){
        inputer = createInputService();
        logger = createLogService();
    }

    /**
     * 입력 방식에 따라 입력 객체를 생성하는 함수
     * @return 생성된 입력 객체
     */
    private static Input createInputService(){
        String input = Setting.getPropery("input.mode");
        
        if (input.equalsIgnoreCase("file")) {
            return new FileInput();
        } else if (input.equalsIgnoreCase("console")) {
            return new ConsoleInput();
        } else if (input.equalsIgnoreCase("args")) {
            return new ArgsInput();
        } else {
            return new FileInput();
        }
    }
    
    /**
     * 로그 출력 방식에 따라 로그 객체를 생성하는 함수
     * @return 생성된 출력 객체
     */
    private static Log createLogService(){
        String log= Setting.getPropery("log.mode");

        if (log.equalsIgnoreCase("file")) {
            return new FileLog();
        } else if (log.equalsIgnoreCase("db")) {
            return new DBLog();
        } else {
            return new FileLog();
        }
    }

    public static Input getInput(){
        return inputer;
    }

    public static Log getLogger(){
        return logger;
    }
}
