package com.wesley.bean.test.stack;

public class StackY {
	    private int maxSize;
	    private char StackArray[];
	    private int top;
	    public StackY(int max){         //构造函数的定义
	        maxSize=max;
	        StackArray=new char[maxSize];
	        top=-1;
	    }
	    public void push(char j){           //字符压栈
	        StackArray[++top]=j;
	    }       
	    public char pop(){              //字符弹出
	        return StackArray[top--];
	    }
	    public char seek(){             //查找
	        return StackArray[top];
	    }
	    public boolean isEmpty()            //空栈判断
	    {
	        return top==-1;
	    }
}
