package com.client.mystacks;

import java.util.LinkedList;
/**
 * 自定义栈  (采用java中的链表LinkList实现)
 */
public class MyStacks {
	private LinkedList<Object> mylist = new LinkedList<Object>();//使用链表实现栈
	public int top = -1; //初始化栈顶指针
	
	//入栈
	public void push(Object value){
		top++;
		mylist.addFirst(value);
	}
	
	//出栈并返回出栈元素
	public Object pop(){
		top--;
		return mylist.removeFirst();
		
	}
	
	//获取栈顶元素
	public Object top(){
		return mylist.getFirst();
		
	}

}
