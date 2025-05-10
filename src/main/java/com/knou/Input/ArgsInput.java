package com.knou.Input;

import com.knou.Main;
import com.knou.Input.Interface.Input;
import com.knou.Model.Args;

public class ArgsInput implements Input{

    private Args args;

    public ArgsInput(){
        this.args = new Args();
    }

    public String read(){
        return args.getFormula();
    }
}
