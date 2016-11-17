package com.cxy.warning.mysql;

import java.util.List;
import java.util.UUID;

public class Message {

	private String content;
	private String receiver;
	
	public Message(String strContent, String strReceiver) {
		this.content = strContent;
		this.receiver = strReceiver;
	}
	
	public String generateMessage() {
		
		StringBuffer sb = new StringBuffer();
		String str_uuid = UUID.randomUUID().toString();
		
//		String strReceiver = "";
		
//		for(String singleNum : receiver) {
//			strReceiver += singleNum + ";";
//		}
//		
//		strReceiver = strReceiver.substring(0,strReceiver.length()-1);
		
		sb.append(
		"insert into sms_outbox(sismsid, extcode, destaddr, messagecontent,reqdeliveryreport,msgfmt,sendmethod,requesttime,applicationid,ecid) ")
		.append("values('")
		.append(str_uuid)
		.append(
				"','','")
		.append(receiver)
		.append("','")
		.append(content).append(
				"',0,15,0,now(),'ZPOA01','defaultema')");
		
		return sb.toString();
	}
}
