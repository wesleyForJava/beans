package com.wesley.bean.test.stack;

public class BracketChecker {
private String input;
	
	public BracketChecker(String input) {
		this.input = input;
	}
	
	public void check() {
		boolean flag = true;
		int stackSize = input.length();
		StackY stackX = new StackY(stackSize);
		for(int i=0; i<stackSize; i++) {
			char ch = input.charAt(i);
			switch(ch) {
			case '{':
			case '[':
			case '(':
				stackX.push(ch);//如果是左侧符号,进栈
				break;
			case '}':
			case ']':
			case ')':
				if(!stackX.isEmpty()) { //if stack not empty
					char chx = (char) stackX.pop();//pop and check
					if( (ch=='}' && chx != '{') ||
					    (ch==']' && chx != '[') ||
						(ch==')' && chx != '(') ){
						    flag = false;
							System.out.println("Error：" + ch + " at " + i);
						}
				}else { //prematurely  empty
					 flag = false;
					 System.out.println("Error:missing left delimiter：" + ch + " at " + i);
				}
				break;
			default:
				break;
			}
		}
		//at this point, all characters have been processed
		if(!stackX.isEmpty()) {
			System.out.println("Error:missing right delimiter");
			flag = false;
		}
		if(flag) {
			System.out.println("正确匹配");
		}else {
			System.out.println("出现错误");
		}
	}
}
