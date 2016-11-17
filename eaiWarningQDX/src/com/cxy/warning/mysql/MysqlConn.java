package com.cxy.warning.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConn {
	
	private String driver = "";
	private String url = "";
	private String user = "";
	private String password = "";
	
	
	/**
	 * 构造成员对象
	 * @param strDriver
	 * @param strUrl
	 * @param strUser
	 * @param strPassword
	 */
	public MysqlConn(String strDriver, String strUrl, String strUser, String strPassword) {
		this.driver = strDriver;
		this.url = strUrl;
		this.user = strUser;
		this.password = strPassword;
	}
	
	/**
	 * 取得Mysql连接coon
	 */
	public Connection getConn() {
		try { 
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			
			return conn;
		} catch(ClassNotFoundException e) {
			System.out.println("cannot find the driver!!");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void closeConn(Connection conn) {
		try {
			if(!conn.isClosed()) {
				conn.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
