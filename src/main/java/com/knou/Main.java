package com.knou;

import com.knou.Input.Interface.Input;
import com.knou.Service.Calculate;
import com.knou.Util.ServiceLocater;
import com.knou.Util.Setting;

public class Main {

    private static String[] staticArgs;

    public static void main(String[] args) {
        staticArgs = args;
        Setting.loadProps();
        ServiceLocater.loadService();
        Input inputer = ServiceLocater.getInput();
        Calculate calculate = new Calculate();

        calculate.startOperator(inputer.read());
        //calculate.startOperator(formula5);
    }

    public static String[] getStaticArgs(){
        return staticArgs;
    }
}