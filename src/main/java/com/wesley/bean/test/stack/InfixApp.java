package com.wesley.bean.test.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InfixApp {
	public static void main(String[] args) {
		String input, output;
		while (true) {
			input = getString();
			if ("".equals(input) || input == null) {
				break;
			}
			InToPost itp = new InToPost(input);
			output = itp.doTrans();
			System.out.println("Profix is " + output + "\n");
		}
	}

	public static String getString() {
		String output = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("请输入算术表达式：");
			output = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}
}
