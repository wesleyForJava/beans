package com.wesley.bean.test.stack;

public class ParsePost {
	private MyStackX theStackX;
	private String input;

	public ParsePost(String s) {
		input = s;
	}

	public int doParse() {
		int result;
		theStackX = new MyStackX(20);
		/**
		 * 判断是否是数，是则入栈。判断是否是操作符，是则弹出2个数，进行运算。
		 */
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i); //获取到输入的数据
			if (ch >= '0' && ch <= '9') {
				theStackX.push((int) (ch - '0'));// 转换成int
			} else {
				int num2 = theStackX.pop();
				int num1 = theStackX.pop();

				switch (ch) {
				case '+':
					result = num1 + num2;
					break;
				case '-':
					result = num1 - num2;
					break;
				case '*':
					result = num1 * num2;
					break;
				case '/':
					result = num1 / num2;
					break;
				default:
					result = 0;
					break;
				}
				System.out.println(result);
				theStackX.push(result);
			}
		}
		return theStackX.pop();
	}
}
