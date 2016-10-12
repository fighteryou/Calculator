package com.client.calculate;

public class OperatorDeal {
	//判断是否为操作符
	public static boolean isOperator(String operator){  
        if (operator.equals("+") || operator.equals("-")    
                || operator.equals("*") || operator.equals("/")    
                || operator.equals("%")|| operator.equals("(") 
                || operator.equals(")")
                )    
            return true;    //是操作运算符返回true
        else   
            return false; //否则返回false   
    }    
	
	 // 设置操作符号的优先级别    
    public static int priority(String operator){    
        if (operator.equals("+") || operator.equals("-"))    
            return 1;  
        else if (operator.equals("*") || operator.equals("/")||operator.equals("%") )   
            return 2;  
        else   
            return 0;  
    }    
    
    // 运算 
    public static String Calculate(String operator, String a, String b){    
        try{    
            String op = operator;    
            String rs = new String();    
            double x = Double.parseDouble(a);    
            double y = Double.parseDouble(b); 
            double z = 0;   //存放运算结果的变量 
            if (op.equals("+"))   //"+"，求和运算    
                z = x + y;    
            else if (op.equals("-"))  //"-",求差运算   
                z = x - y;
            else if (op.equals("*")) //"*",求积运算    
                z = x * y;    
            else if (op.equals("/"))  //"/",求商运算   
                z = x / y;   
            else if (op.equals("%"))
            	z=x%y;
            else   
                z = 0;        //其他运算，结果设为0
            return rs + z;    //最终返回值为String类型
        } catch (NumberFormatException e){    
            return "Error";    
        }    
    }    

}
