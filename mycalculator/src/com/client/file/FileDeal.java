package com.client.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileDeal {
	//用于存储数据
	public static void save(String str,String path){
		try {
				FileWriter file = new FileWriter(path);
				BufferedWriter buffer = new BufferedWriter(file);
				buffer.write(str);
				buffer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//用于取数据
	public  static String read(String filePath){
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
