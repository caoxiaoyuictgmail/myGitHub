package com.cxy.warning.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class InitMysql {
	
	private Properties prop;
	private InputStream in;
	
	
	public InitMysql() {
		try {
			prop = new Properties();
			in = InitMysql.class.getResourceAsStream("jdbc.properties");
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Connection InitJboss1 () {
		MysqlConn jbossmq1Conn = new MysqlConn(prop.getProperty("driverJboss1").trim(), prop.getProperty("urlJboss1").trim(), prop.getProperty("userJboss1").trim(), prop.getProperty("passwordJboss1").trim());
		return jbossmq1Conn.getConn();
	}
	
	public Connection InitJboss2 () {
		MysqlConn jbossmq1Conn = new MysqlConn(prop.getProperty("driverJboss2").trim(), prop.getProperty("urlJboss2").trim(), prop.getProperty("userJboss2").trim(), prop.getProperty("passwordJboss2").trim());
		return jbossmq1Conn.getConn();
	}
	
	public Connection InitJboss3 () {
		MysqlConn jbossmq1Conn = new MysqlConn(prop.getProperty("driverJboss3").trim(), prop.getProperty("urlJboss3").trim(), prop.getProperty("userJboss3").trim(), prop.getProperty("passwordJboss3").trim());
		return jbossmq1Conn.getConn();
	}
	
	public Connection InitJboss4 () {
		MysqlConn jbossmq1Conn = new MysqlConn(prop.getProperty("driverJboss4").trim(), prop.getProperty("urlJboss4").trim(), prop.getProperty("userJboss4").trim(), prop.getProperty("passwordJboss4").trim());
		return jbossmq1Conn.getConn();
	}
	
	public Connection InitSms () {
		MysqlConn jbossmq1Conn = new MysqlConn(prop.getProperty("driverSms").trim(), prop.getProperty("urlSms").trim(), prop.getProperty("userSms").trim(), prop.getProperty("passwordSms").trim());
		return jbossmq1Conn.getConn();
	}
}
