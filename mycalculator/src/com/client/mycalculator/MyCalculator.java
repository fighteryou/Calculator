package com.client.mycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.client.frame.CalculatorFrame;



public class MyCalculator {
	private String url = "127.0.0.1";
	private int port = 8888;
	
	public MyCalculator() throws  IOException {
		//启动客户端
		System.out.println("客户端已经成功的启动！");
	}

    public void send() throws  IOException{
    	
    	new CalculatorFrame();
    	
    	while(true){
    		if(CalculatorFrame.flago == 1){
    			CalculatorFrame.flago = 0;
        		//建立socket通信
        		Socket client = new Socket(url,port);
        		System.out.println("已连接到服务端");
                //封装输入输出流 
                PrintWriter pw = new PrintWriter(client.getOutputStream(),true);
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));//转化为字符流传入socket
                //向socket通道写入消息
                String message1;
                String message2;
                String message ;
                
                double data1 = CalculatorFrame.getMemory();
                message1 = "" + data1;
                message2 = CalculatorFrame.getTask();
                message = message1 + "," + message2;
                pw.println(message);
                System.out.println("client端已经成功的向server端发送消息  "+message);
                //从socket通道取出返回的结果
                String response = br.readLine();
                
                System.out.println("client端已经成功的从server端接收到响应消息 " + response);
        	}
    	}
    	
    }
	public static void main(String[] args) throws IOException{
		new MyCalculator().send();
	}

}
