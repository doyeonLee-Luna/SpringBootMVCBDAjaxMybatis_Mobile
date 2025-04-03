package com.mycom.myapp.common;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// Connection 객체 반납

public class DBManager {
	
	
	// Connection Pool ( DataSource ) 객체를 직접 획득 하는 것이 아닌 <- Spring DI 를 이용한다.
//    public static Connection getConnection() {
//        Connection con = null;
//        try {
//        	Context context = new InitialContext();
//        	DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/madang"); // Connection pool
//            con = ds.getConnection();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        
//        return con;     
//    }
    
    // Connection pool 로 부터 얻는 Connection 객체의 close() 메소드는 overriding 되어 있다.
    // Connection 을 끊지 않고 Connection Pool 에 반납되도록 되어 있다. 
    public static void releaseConnection(PreparedStatement pstmt, Connection con) {
        try {
            if( pstmt != null ) pstmt.close();
            if( con != null ) con.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void releaseConnection(ResultSet rs, PreparedStatement pstmt, Connection con) {
        try {
            if( rs != null ) rs.close();
            if( pstmt != null ) pstmt.close();
            if( con != null ) con.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}