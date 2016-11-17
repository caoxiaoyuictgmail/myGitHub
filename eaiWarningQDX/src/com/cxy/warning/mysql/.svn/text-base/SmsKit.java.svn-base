package com.cxy.warning.mysql;

import java.sql.Connection;
import java.sql.Statement;

public class SmsKit {
	public static void sendMessage(String content, String receiver) {
		if(!"".equals(content)) {
			try {
			InitMysql initMysql = new InitMysql();
			
			Connection connSms = initMysql.InitSms();
			Statement st = connSms.createStatement();

			//send message
			Message msg = new Message(content, receiver);
			String msgResult = msg.generateMessage();
			st.execute(msgResult);

			if (!st.isClosed())
				st.close();
			if (!connSms.isClosed())
				connSms.close();
			} catch (Exception e) {
				Window.resultConsole.append("短信服务器连接失败，短信未发送！！\r\n");
			}
		}
	}
}
