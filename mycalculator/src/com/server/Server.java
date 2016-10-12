package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	
	private ServerSocket server;
	private int port = 8888;
	public Server()throws IOException{
		//启动服务器，并指定端口号
		server = new ServerSocket(port);
		System.out.println("服务器已经成功的启动！");
	}
	public void receive(){
		try {
			//监听客户端的连接请求并接受，如果没有将一直等待下去
			while(true){
				
				Socket s = server.accept();
				System.out.println("已经成功的接收到一个客户端的连接请求！");
				//将接收到的客户端连接交给一个线程处理
				new Thread(new Listener(s)).start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new Server().receive();

	}

}
