package com.cxy.warning.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class EAIKit {
	private String ts;
	
	private String strResult = "";
	//private String count = "0";
	private int count = 0;
	
	public EAIKit (String strTs) {
		this.ts = strTs;
	}
	
	public Map<String, String> checkMySql() {
		Map tmp = new HashMap<String, Integer>();
		
		try {
			checkError(ts);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(count >= Integer.parseInt(ts)) {
			strResult += "堆积总条数为---->" + count;
		}
		
		Window.resultConsole.append(strResult + "\r\n");
		
		tmp.put("strPrint", strResult);
		tmp.put("count", "" + count);
		return tmp;
	}
	
	public  void checkError (String ts) {
		InitMysql initMysql = new InitMysql();
		//-- Jboss1
		Connection conn1 = initMysql.InitJboss1();
		checkEaiCount(conn1, "JBOSS1-");
		
		//-- Jboss2
		Connection conn2 = initMysql.InitJboss2();
		checkEaiCount(conn2, "JBOSS2-");
		
		//-- Jboss3
		Connection conn3 = initMysql.InitJboss3();
		checkEaiCount(conn3, "JBOSS3-");
		
		//-- jboss4
		Connection conn4 = initMysql.InitJboss4();
		checkEaiCount(conn4, "JBOSS4-");
	}
	
	
	public void checkEaiCount (Connection conn, String dbName) {
		boolean a = false;
		String tmp = "";
		
		try {
			String sql = "SELECT DESTINATION QueueName, COUNT(DESTINATION) Count FROM JMS_MESSAGES GROUP BY DESTINATION";
			
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {	
				a = true;
				tmp += rs.getString("QueueName") + "                            " + rs.getString("Count") + "\r\n";
				count += Integer.parseInt(rs.getString("Count"));
			}
		
			// if result count is > ts
			if(count >= Integer.parseInt(ts) && a) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date date= new java.util.Date();
				strResult += format.format(date) + "\r\n";
				strResult += dbName + "EAI堆积过多,请及时检查！！\r\n";
				strResult += "QueueName                 Count\r\n";
				strResult += tmp;
			}
			
			if (!rs.isClosed())
				rs.close();
	
			if (!statement.isClosed())
				statement.close();
	
			if (!conn.isClosed())
				conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
