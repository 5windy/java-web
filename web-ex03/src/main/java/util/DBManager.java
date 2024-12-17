package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// 반복적으로 재사용하는 데이터베이스 관련 객체 또는 메소드를 제공
public class DBManager {
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Context init = new InitialContext();
			Context context = (Context) init.lookup("java:comp/env");
			
			DataSource datasource = (DataSource) context.lookup("jdbc/SampleDB");
		
			conn = datasource.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
