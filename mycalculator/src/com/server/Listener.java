package com.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listener implements Runnable {
    private Socket s;
	
	public Listener(Socket s){
		this.s=s;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
			//接收从client端发来的消息
			String reMsg = br.readLine();
			String []s = reMsg.split(",");
			String message1 = s[0];
			String message2 = s[1];
		    String path1 =  "src\\memory.txt";
			String path2 =  "src\\user_log.txt";
			save(message1,path1,"memory");
			save(message2,path2,"log");
			System.out.println("server端已经成功的接收到client端的请求消息 "+reMsg);  
			//将返回消息发往TELLER端
			String result;
			result = readTxtFile(path1);
			pw.println(result);
			System.out.println("server端已经成功的向client端发送响应消息 "+result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//用于存储数据
	private void save(String str,String path,String s){
		try {
			if(s.equals("log")){
				FileWriter file = new FileWriter(path,true);
				BufferedWriter buffer = new BufferedWriter(file);
				Date now = new Date(); 
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
	            String time = dateFormat.format( now ); 
				buffer.write("时间："+time+" " +"任务："+ str);
				buffer.newLine();
				buffer.close();
			}
			else{
				FileWriter file = new FileWriter(path);
				BufferedWriter buffer = new BufferedWriter(file);
				buffer.write(str);
				buffer.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//用于取数据
	public  String readTxtFile(String filePath){
		String datatxt = "";
        try {
                String encoding="GBK";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = "";
                    while((lineTxt = bufferedReader.readLine()) != null){
                    	datatxt = datatxt+lineTxt;
                        System.out.println(lineTxt);
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
		return datatxt;
     
    }

}
