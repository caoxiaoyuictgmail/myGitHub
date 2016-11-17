package com.cxy.warning.mysql;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Running implements Runnable {
	private boolean isChange = false;
	private int cc = 0;
	
	//收件人
	private String receiver;
	//条数
	private String ts;
	//分
	private String fz;
	
	private List<String> listTs;
	
	private List<String> listFz;
	
	private String content = "";
	
	private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Running(String receiver, String ts, String fz) {
		this.receiver = receiver;
		this.ts = ts;
		this.fz = fz;
		
	}
	
	public Running(String receiver, List<String> listTs, List<String> listFz) {
		this.receiver = receiver;
		this.listTs = listTs;
		this.listFz = listFz;
	}

	public void run() {
		Thread thisThread = Thread.currentThread(); 
		
		while (Window.blinker == thisThread) {
			try {
				try {
					EAIKit eaiKit = null;
					
					if(cc != 0 && cc>Integer.parseInt(listTs.get(1))) {
						eaiKit = new EAIKit(listTs.get(1));
					} else {
						eaiKit = new EAIKit(listTs.get(0));
					}
					
					Map<String, String> mapTmp = new HashMap<String, String>();
					//check count
					mapTmp = eaiKit.checkMySql();
					
					String strPrint = mapTmp.get("strPrint").toString();
					cc = Integer.parseInt(mapTmp.get("count").toString());
					
					if(cc>Integer.parseInt(listTs.get(1))) {
						isChange = true;
					} else {
						isChange = false;
					}
					
					//send message
					SmsKit.sendMessage(strPrint, receiver);
					
					Window.resultConsole.setCaretPosition(Window.resultConsole.getText().length());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(isChange) {
					Thread.sleep(1000 * 60 * Integer.parseInt(listFz.get(1)));
				} else {
					Thread.sleep(1000 * 60 * Integer.parseInt(listFz.get(0)));
				}
//				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("The thread is stopped!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
