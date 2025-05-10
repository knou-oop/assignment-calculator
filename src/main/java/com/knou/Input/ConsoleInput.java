package com.knou.Input;

import java.util.Scanner;

import com.knou.Input.Interface.Input;

public class ConsoleInput implements Input {
    
    public String read(){
        System.out.println("계산하고자 하는 수식을 입력하세요. 단, 수식에 띄어쓰기가 포함돼서는 안됩니다.");
        Scanner sc = new Scanner(System.in); // Scanner 객체 생성
        String formula = sc.nextLine();
        sc.close();
        return formula;
    }
}
