package com.wesley.bean.test.stack;

public class StackApp {
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		StackX theStack = new StackX(10);// 创建一个栈
		theStack.push(20);
		theStack.push(40);
		theStack.push(60);
		theStack.push(80);
		while (!theStack.isEmpty()) {// 直到为空
			long value = theStack.pop();// 从栈里删除数据项
			System.out.print(value);// 显示剩余的数据项
			System.out.print(" ");
		} // end while
		System.out.println("");
	}// end main
}// end class StackApp