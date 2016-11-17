package com.cxy.warning.mysql;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window extends JFrame implements ActionListener{
	//minute count
	private JTextField fz;
	
	private JTextField fz2;
	//the lowest warning count
	private JTextField ts;
	
	private JTextField ts2;
	
	// receiver num
	private JTextField receiver;
	
	private JButton jStart;
	private JButton jStop;
	
	private Running warningRun;
	public static volatile Thread blinker;
	
	public void stop() { 
        Thread tmpBlinker = blinker;
        blinker = null;
        if (tmpBlinker != null) {
           tmpBlinker.interrupt();
        }
    } 
	
	
	public static JTextArea resultConsole = new JTextArea(13, 42);
	
	public Window() {
		this.launchFrame();
	}
	
	
	public void launchFrame() {
		this.setTitle("EAI预警系统");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(null);
		
		JPanel top = new JPanel();
		top.setSize(780,50);
		top.setLocation(0, 0);
		jStart = new JButton("开始");
		jStop = new JButton("停止");
		jStop.setEnabled(false);
		
		top.add(jStart);
		top.add(jStop);
	
		this.add(top, BorderLayout.NORTH);
		
		JPanel center = new JPanel(new GridLayout(3,4));
		
		center.setSize(780,100);
		center.setLocation(0, 50);
		
		JLabel lReceiver = new JLabel();
		lReceiver.setText("    接收人手机号码(用;隔开)");
		
		JLabel lfz = new JLabel();
		lfz.setText("    预警循环分钟");
		JLabel lts = new JLabel();
		lts.setText("    EAI最低堆积数");
		
		JLabel lfz2 = new JLabel();
		lfz2.setText("    预警循环分钟");
		JLabel lts2 = new JLabel();
		lts2.setText("    EAI最低堆积数");
		
		fz = new JTextField(10);
		fz.setText("10");
		ts = new JTextField(10);
		ts.setText("1");
		
		fz2 = new JTextField(10);
		fz2.setText("1");
		ts2 = new JTextField(10);
		ts2.setText("15");
		
		receiver = new JTextField(10);
		receiver.setText("18115670921;");
		
		center.add(lfz);
		center.add(fz);
		center.add(lts);
		center.add(ts);
		center.add(lfz2);
		center.add(fz2);
		center.add(lts2);
		center.add(ts2);
		center.add(lReceiver);
		center.add(receiver);
		
		this.add(center, BorderLayout.CENTER);
		
		JPanel bottom = new JPanel();
		
		JScrollPane jsp=new JScrollPane();
		jsp.setViewportView(resultConsole);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		bottom.setSize(780,300);
		bottom.setLocation(0, 150);
		
		JLabel lResultConsole = new JLabel();
		resultConsole.setLayout(new FlowLayout(FlowLayout.CENTER));
		resultConsole.setAutoscrolls(true);
		resultConsole.setLineWrap(true);
		resultConsole.setFont(new Font("标楷体 ", Font.BOLD, 16));
		resultConsole.setWrapStyleWord(true);// 激活断行不断字功能
		
//		bottom.add(lResultConsole);
		bottom.add(jsp);
		
		this.add(bottom, BorderLayout.SOUTH);
		
		this.setSize(800,500);
		this.setLocation(300, 100);
		this.setVisible(true);
		
		jStart.addActionListener(this);
		jStop.addActionListener(this);
	}
	
	public void setResultConsole(String result) {
		this.resultConsole.append(result);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("开始")) {
			List<String> listTs = new ArrayList<String>();
			List<String> listFz = new ArrayList<String>();
			
			listTs.add(ts.getText().trim());
			listTs.add(ts2.getText().trim());
			listFz.add(fz.getText().trim());
			listFz.add(fz2.getText().trim());
			
			warningRun = new Running(receiver.getText().trim(), listTs, listFz);	
			blinker = new Thread(warningRun);
			blinker.start();
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date date= new java.util.Date();
			resultConsole.append(format.format(date) + "     ");
			
			resultConsole.append("监控开始\r\n");
			
			
			btnStart();
		}
		
		if(e.getActionCommand().equals("停止")) {
			this.stop();
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date date= new java.util.Date();
			resultConsole.append(format.format(date) + "     ");
			resultConsole.append("已停止监控\r\n");
			btnStop();
		}
	}
	
	public void btnStart() {
		jStart.setEnabled(false);
		jStop.setEnabled(true);
		receiver.setEnabled(false);
		ts.setEnabled(false);
		fz.setEnabled(false);
		ts2.setEnabled(false);
		fz2.setEnabled(false);
	}
	
	public void btnStop() {
		jStart.setEnabled(true);
		jStop.setEnabled(false);
		receiver.setEnabled(true);
		ts.setEnabled(true);
		fz.setEnabled(true);
		ts2.setEnabled(true);
		fz2.setEnabled(true);
	}
	
}
