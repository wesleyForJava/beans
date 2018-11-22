package com.wesley.bean.test.stack;

	public class MyStackX {
		private int maxSize;//栈的最大容量
		private int[] stackArray;	//栈的数据
		private int top;	//栈头标记

		public MyStackX(int s) {
			maxSize = s;
			stackArray = new int[s];
			top = -1;
		}

		public void push(int c) {//入栈
			stackArray[++top] = c;
		}

		public int pop() {//出栈
			return stackArray[top--];
		}

		public int peek() {
			return stackArray[top];
		}

		public boolean isEmpty() {
			return top == -1;
		}

		public boolean isFull() {
			return top == (maxSize - 1);
		}

		public int size() {
			return top + 1;
		}

		public int get(int index) {
			return stackArray[index];
		}

		public void display(String str) {
			System.out.print(str);
			System.out.print(" Stack (bottom-->top): ");
			for (int i = 0; i < size(); i++) {
				System.out.print(get(i)+" ");
			}
			System.out.println();
		}
}
