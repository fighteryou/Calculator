package com.client.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.client.calculate.ExpressionDeal;
import com.client.file.FileDeal;

public class CalculatorFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	  JPanel jp1 = new JPanel(new BorderLayout(1,3));
	  JPanel jp2 = new JPanel(new GridLayout(1,3,3,3));
	  JPanel jp3 = new JPanel(new GridLayout(4,5,3,3));
	 
	  JTextField jtf = new JTextField("0");
	 
	  String []s1 = {"Backspace","C","ZY"};
	  private static double memory;
	  private static String smemory = "";
	  private static String task;
	  JButton [] jbutton1 = new JButton[s1.length];
	  String []s2 = {"%","null","1","2","3","(",")","4","5","6","+","-","7","8","9","*","/","0",".","="};
	  JButton []jbutton2 = new JButton[s2.length];
	  
	  public static int flago = 0;
	  private int flag00 = 0;

	public CalculatorFrame(){
		super("ZYCalculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭窗口时结束进程，防止内存泄露
		for(int i = 0;i<s1.length;i++)
		  {
			jbutton1[i] = new JButton(s1[i]);
			jbutton1[i].setFont(new Font("宋体",Font.PLAIN,12));
			jbutton1[i].setForeground(Color.red);
			jbutton1[i].addActionListener(this);
		    jp2.add(jbutton1[i]);
		  }
		   for(int i = 0;i<s2.length;i++)
		   {
			   jbutton2[i] = new JButton(s2[i]);
			   jbutton2[i].setFont(new Font("宋体",Font.PLAIN,12));
		    if(i==2||i==3 || i==4||i==7|| i==8 || i==9||i==12 || i==14 || i==13|| i==17|| i==18 || i==19 )
		    {
		    	jbutton2[i].setForeground(Color.black);
		    }
		    else
		    {
		    	jbutton2[i].setForeground(Color.BLUE);
		    }//注册监听器
		    jbutton2[i].addActionListener(this);
		    jp3.add(jbutton2[i]);
		   }
		   
		   jp1.add(jp2,BorderLayout.CENTER);
		   jp1.add(jp3,BorderLayout.SOUTH);
		   this.add(jtf,BorderLayout.NORTH);
		   this.add(jp1);
		   this.pack();
		   this.setVisible(true);
	}

	 String str = ".0123456789+-*/%()";
	 String resultText = "";
	 int flag = 0;
	 String []before = new String [100];
	 int i = 1;
	 public void actionPerformed(ActionEvent e) {
	  String s = e.getActionCommand();//获取获取事件源的标签
	  before[0] = "0";
	  before[i] = s;
	  i++;
	  System.out.println(s);
	  
	  if(flag00 == 0){
		  jtf.setText("");
		  flag00 = 1;
	  }
	  if(str.indexOf(s) != -1)
	  {
		  if(before[i-1] == "C"){
			  jtf.setText(s);
		  }else{
			  if(flag == 0){
				    jtf.setText(jtf.getText()+s);
				    resultText = jtf.getText();
			  }
			  else{
				 jtf.setText(s);
				 flag = 0;
			  }
		  }
		 
	    
	  }
	  else if(s.equals("=")){
		  //处理表达式并且返回结果
		  flag = 1;
		  String experssion = jtf.getText();
		  ExpressionDeal experssiondeal = new ExpressionDeal(experssion);
		  
		  String strresult = experssiondeal.getResult();
		  jtf.setText(strresult);
		  task = experssion + "=" + strresult;
		  flago = 1;
	  }
	  else if(s.equals("ZY")){
		  String path = "src\\data.txt";
		  smemory = FileDeal.read(path);
		  jtf.setText(smemory);
		  
	  }
	  else if(s.equals(s1[0])){
		  String text = jtf.getText();
			int i = text.length();
			if (i > 0) {
				// 退格，将文本最后一个字符去掉
				text = text.substring(0, i - 1);
				if (text.length() == 0) {
					// 如果文本没有了内容，则初始化计算器的各种值
					jtf.setText("");
				} else {
					// 显示新的文本
					jtf.setText(text);
				}
			}
		  
	  }
	  else if(s.equals(s1[1])){
		  jtf.setText("0"); 
		  flag00 = 0;
	  }
	  else if(s.equals(s1[2])){
		  String txt = jtf.getText();
		  try {
			memory = stringtodouble(txt);
			String path = "src\\data.txt";
            FileDeal.save(txt, path);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			jtf.setText("数据非法");
			
		}
	  }
	
	 }
	

	 //方便异常处理
	private double stringtodouble(String s) throws Exception{
		double m = 0;
		m = Double.parseDouble(s);
		return m;
	}
	
	public static double getMemory() {
		return memory;
	}
	
	
	public static String getSMemory() {
		return smemory;
	}


	public static String getTask() {
		return task;
	}
}
