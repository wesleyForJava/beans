package com.wesley.bean.test.stack;

public class InToPost {
	private MyStack ms;//自定义栈
	private String input;//输入中缀表达式
	private String output="";//输出的后缀表达式

	public InToPost(String input) {
		this.input = input;
		int size = input.length();
		ms = new MyStack(size);
	}

	public String doTrans() {//转换为后缀表达式方法
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);   //获取到输入的数据第一个字符 
			ms.display("for " + ch + " ");
			switch (ch) {
			case '+':
			case '-':
				getOper(ch, 1);
				break;
			case '*':
			case '/':
				getOper(ch, 2);
				break;
			case '(':
				ms.push(ch);   
				break;
			case ')':
				getParent(ch);
				break;
			default:
				output = output + ch; //如果是数字直接放入字符串 符号放入栈顶
				break; 
			}//end switch 
		}//end for
		while(!ms.isEmpty()){
			ms.display("While ");
			output=output+ms.pop();
		}
		ms.display("end while!!");
		return output;
	}
	
	/**
	 * @param ch
	 * 获得上一级字符串
	 */
	public void getParent(char ch) {
		while(!ms.isEmpty()){
			char chx=ms.pop();
			if(chx=='('){
				break;
			}else{
				output=output+chx;
			}
		}
	}	
		/**
		 * @param ch 操作符
		 * @param prec1 操作符的优先级
		 * 根据操作符的优先级判断是否入栈，及入栈的顺序
		 */
		public void getOper(char ch, int prec1) {
			while (!ms.isEmpty()) {//判断栈是否为空
				char operTop = ms.pop();  //弹出栈顶元素
				if (operTop == '(') {
					ms.push(operTop);
					break;
				} else {
					int prec2;
					if (operTop == '+' || operTop == '-') {//优先级高的先append
						prec2 = 1;
					} else {
						prec2 = 2;
					}
					if (prec2 <prec1) {
						ms.push(operTop);
						break;
					} else {
						output = output + operTop;
					}
				}
			}// end while
			ms.push(ch);
		}
}
