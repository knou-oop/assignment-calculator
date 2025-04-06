package com.knou;

import com.knou.Service.Calculate;
import com.knou.Util.Setting;

public class Main {
    public static void main(String[] args) {
        Setting.loadProps();
        String formula1 = "15+15-3*5"; // 15 15 + 3 5 * - : 15
        String formula2 = "15+15*5"; // 15 15 5 * + : 90
        String formula3 = "15*15/3+5"; // 15 15 * 3 / 5 + : 80
        String formula4 = "1.1*2.2"; // 1.1 2.2 * : 2.42
        String formula5 = "5.43432*5.43"; // 5.43432 5.43 * : 29.5083576
        Calculate calculate = new Calculate(formula5);
        calculate.startOperator();

    }
}