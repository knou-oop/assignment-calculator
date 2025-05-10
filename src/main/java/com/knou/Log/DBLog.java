package com.knou.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.knou.Log.Interface.Log;
import com.knou.Util.Setting;
import com.knou.Util.TimeUtil;

/**
 * 로그 데이터를 DB에 저장하는 클래스
 */
public class DBLog implements Log {
    private Connection conn;
    private String driver;
    private String url;
    private String id;
    private String pw;
    private PreparedStatement pstmt;

    public DBLog(){
        this.driver = Setting.getPropery("driver");
        this.url = Setting.getPropery("url");
        this.id = Setting.getPropery("id");
        this.pw = Setting.getPropery("pw");
    }

    /**
     * DB 정보 사용하여 DB와 연결하는 메서드
     * @return true: 성공, false: 실패
     */
    private boolean connectDB() throws ClassNotFoundException, SQLException{
        boolean rt = false;
        try {
            // 드라이버 로드
            Class.forName(driver);

            // DB 연결
            conn = DriverManager.getConnection(url, id, pw);
            System.out.println("MySQL 연결 성공!");
            rt=true;
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new SQLException();
        }
        return rt;
    }

    /**
     * DB 연결 해제하는 메서드
     * @return true: 성공, false: 실패
     */
    private boolean disconnectDB(){
        boolean rt = false;
        // 연결 해제
        if (conn != null) {
            try {
                conn.close();
                System.out.println("연결 해제 완료");
                rt = true;
            } catch (SQLException e) {
                System.out.println("연결 해제 중 오류: " + e.getMessage());
            }
        }
        return rt;
    }

    public boolean writeLog(String formula, String backFormula, String result) {
        String time = TimeUtil.getNow();
        boolean rt=false;
        String sql = "INSERT INTO CalcLog(FORMULA, BACK_FORMULA, RESULT, DATE) VALUES (?, ?, ?, ?)";

        try{
            connectDB();
        } catch(ClassNotFoundException e){
            System.out.println("드라이버 로드 실패: " + e.getMessage());
            return rt;
        } catch(SQLException e){
            System.out.println("DB 연결 실패: " + e.getMessage());
            return rt;
        }

        try{
            this.pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, formula);
            pstmt.setString(2, backFormula);
            pstmt.setString(3, result);
            pstmt.setString(4, time);
            pstmt.executeUpdate();
            rt=true;
        } catch(SQLException e){
            System.out.println("쿼리 동작 실패: " + e.getMessage());
        } finally{
            disconnectDB();
        }
        return rt;
    }
}
