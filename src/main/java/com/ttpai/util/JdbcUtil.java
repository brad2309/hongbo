package com.ttpai.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcUtil {

	public final static JdbcUtil INSTANCE = new JdbcUtil();
	
	private JdbcUtil(){
		String jdbcurl = "jdbc:mysql://172.16.2.19:3306/ttpai_boss_v1?useUnicode=true&characterEncoding=utf-8";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcurl, "testuser", "dkkmtj");
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	private Connection conn;
	
	public String queryOne(String sql) throws Exception{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			return rs.getString(1);
		}
		return null;
	}
	
}
