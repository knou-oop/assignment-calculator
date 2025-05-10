package com.knou.Input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.knou.Input.Interface.Input;
import com.knou.Util.Setting;

public class FileInput implements Input {
    
    public String read(){
        String path = Setting.getPropery("input.file.path");
        path = path.equalsIgnoreCase("default") ? System.getProperty("user.dir") : Setting.getPropery("input.file.path");
        String name = Setting.getPropery("input.file.name");
        String fileContent = null;

        File file = new File(path, name);
        try{
            fileContent = Files.readString(file.toPath());
        } catch(IOException e){
            System.out.println("input 파일 읽기 실패");
            e.printStackTrace();
        }
        return fileContent;
    }

}
