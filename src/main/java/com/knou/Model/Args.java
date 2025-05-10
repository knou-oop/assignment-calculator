package com.knou.Model;

import com.knou.Main;
import com.knou.Util.Setting;

public class Args {

    private String[] args;
    // -f 인자 다음에 받을 수식 리스트
    private String formula;

    public Args(){
        this.args = Main.getStaticArgs();

        if (!invalidArgs()) {
            System.out.println("인자가 없습니다.");
            System.exit(-1); // 인자 없음 에러 반환 종료
        }
        
        // 인자 파서 호출
        parse();
        
    }


    /**
     * 실행 인자 입력 방식 검증 메서드
     * @return true: 정상 인자, false: 비정상 인자
     */
    public boolean invalidArgs(){
        if (0 >= this.args.length || this.args == null) {
            return false;
        }
        return true;
    }

    /**
     * 인자 파싱 메서드
     */
    public void parse(){
        for(int i=0; i < args.length; i++){
            if (args[i].equalsIgnoreCase("-f")) {
                this.formula = args[i+1];
                i++;
            } else {
                System.exit(-2); // 잘못된 인자 입력.
            }
        }
    }

    /**
     * formula getter
     * @return formula
     */
    public String getFormula(){
        return this.formula;
    }
}
