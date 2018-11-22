package com.wesley.bean.test.stack;

public class StackX {
	// 声明成员变量：每一个对象的成员变量互不相关
	private int maxSize;// 声明一个栈数组长度成员变量
	private long[] stackArray;// 声明一个栈数组成员引用
	private int top;// 声明一个栈顶成员变量

	// 构造器，创建对象或成员变量
	public StackX(int s) {
		maxSize = s;// 初始化成员变量
		stackArray = new long[maxSize];// 创建栈对象
		top = -1;// 初始化成员变量
	}

	// 对象操作方法：push()进栈，pop()出栈，peek()查询
	public void push(long j) {// 插入数据项
		stackArray[++top] = j;// top在插入数据项之前递增
	}

	public long pop() {// 返回top的数据项，然后减一
		return stackArray[top--];
	}

	public long peek() {// 返回所指的数据项的值，不对栈进行操作
		return stackArray[top];
	}

	public boolean isEmpty() {
		return (top == -1);// 是true，即栈空
	}

	public boolean isFull() {// 是true，即栈满
		return (top == maxSize - 1);
	}
}
