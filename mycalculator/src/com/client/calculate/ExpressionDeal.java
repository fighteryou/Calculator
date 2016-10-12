package com.client.calculate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.client.mystacks.MyStacks;

public class ExpressionDeal {
	@SuppressWarnings("unchecked")
	public ArrayList infix = new ArrayList();   // 存储中缀表达式    
    @SuppressWarnings("unchecked")
	public ArrayList postfix = new ArrayList();        // 存储逆波兰式    
   
    public String result;// 结果
    public String strexpression;
    public String nbls = "";//输出逆波兰式
    
   
	@SuppressWarnings("unchecked")
	public ExpressionDeal(String expression) {
		super();
		strexpression = expression;
		StringTokenizer analysis = new StringTokenizer(expression, "+-*/%()", true);//字符串解析
		while (analysis.hasMoreElements()){  
            String s = analysis.nextToken();
            infix.add(s);
        }
	}
	
	
	@SuppressWarnings("unchecked")
	public String intopost(){
		
		MyStacks opstack = new MyStacks();
		String operator;
		String temp;
		int position = 0;
		boolean ismatch = false;
		
		
		while(true){
			//两种特殊情况-1+1    、   (-1+1)
			if(infix.get(0).equals("+")||infix.get(0).equals("-")){
				temp = infix.get(0) + (String)infix.get(position+1);
				postfix.add(temp);//是操作数直接添加到结果集
				position += 2;
				continue;
			}
			 else if(position>0&&infix.get(position-1).equals("(")&&(infix.get(position).equals("+") || infix.get(position).equals("-"))) //类似(-3+2)
		        {
		         temp = infix.get(position) + (String)infix.get(position+1);
		         postfix.add(temp);
		         position +=2;
		        }
			 else {
				 if (OperatorDeal.isOperator((String)infix.get(position))){//是操作符
					 //若栈顶元素为空或者操作符为开括号则直接入栈
					 if (opstack.top == -1 || ((String)infix.get(position)).equals("(")){  
						 opstack.push(infix.get(position));   
		                } 
					 else if(((String)infix.get(position)).equals(")")){
						 while(true){
							//遇到闭括号，弹出栈中操作符直到遇见开括号
	                            if (opstack.top != -1&&!((String) opstack.top()).equals("(")){ 
	                            	ismatch = true;
	                                operator = (String) opstack.pop();
	                                postfix.add(operator);    //将出栈符号添加到结果集
	                            }else{
	                                //if(opstack.top != -1)	           
	                                break;
	                            }   
	                        } 
						 if(ismatch){
							 ismatch = false;
						 }else {
							 return "Error";
						 }
						 
						 
					 }
					 else {
						 while(true){
							 int a0 = OperatorDeal.priority((String)infix.get(position));//扫描到的操作符
							 int a1 = 0;
							 if(opstack.top != -1){
								 a1 = OperatorDeal.priority((String)opstack.top());//栈顶操作符
							 }else {break;}
							 
	                            if ( a0<a1 || a0 == a1){    
	                                //扫描到的操作符优先级不高于栈顶操作符时，出栈直到遇见开括号或者优先级较低的操作符
	                            	operator = (String) opstack.pop(); 
	                                if (!operator.equals("("))    
	                                    postfix.add(operator);    
	                            }else{
	                                break;
	                            }    
	                            
	                        } 
						 opstack.push(infix.get(position));
					 }
				}
				 else   
					 postfix.add(infix.get(position));    
		             position++;    
		             if (position >= infix.size())    
		                break;    
			 }
		}
		while (opstack.top != -1){   //栈非空 
            operator = (String)opstack.pop();  //退栈输出
            if(!operator.equals("("))  //
            	postfix.add(operator);    
        }   
		 Iterator it = postfix.iterator();  
	        while (it.hasNext()) {   
	        	nbls = nbls + "  " + it.next().toString();
	        }
	        System.out.println(nbls);
		return nbls;
	}
	
	 
	 
    @SuppressWarnings("unchecked")
	public String getResult() {  
    	
    	    String returnnbls = this.intopost();
    	    if(returnnbls.equals("Error")){
    	    	return "Error";
    	    }else{
    	    	MyStacks datastack = new MyStacks();  
                Iterator it = postfix.iterator();
                String str;
                String data1;
                String data2;
                
                while(it.hasNext()){
                	str = it.next().toString();
                    if (OperatorDeal.isOperator(str)){//如果是操作符,则弹出两个操作数，运算后入栈
                    	if(datastack.top < 1){
                    		return "Error";
                    	}
                    	else{
                    		data1 = (String) datastack.pop();
                        	data2 = (String) datastack.pop();
                        	datastack.push(OperatorDeal.Calculate(str, data2, data1));
                    	}
                    	
                    }
                    else{//如果是操作数
                    	if(isRight(str)){
                    		datastack.push(str);
                    	}
                    	else{
                    		return "Error";
                    	}
                    }
                }
                result = (String)datastack.pop();   
                if(datastack.top != -1){
                	result = "Error";
                }
                return result;   	
    	    }
    		
      }  
    

	private boolean isRight(String s){
		if(s.indexOf('.') != s.lastIndexOf('.')){
			return false;
		}
		else if(s.charAt(0) == '.'|| s.charAt(s.length()-1) == '.'){
			return false;
		}
		else return true;
	}
}
