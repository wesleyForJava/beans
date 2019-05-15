package com.wesley.bean.pattern.adapter;


public class TestAdatper {
	public static void main(String[] args) {
		DC5 dc5=new PowerAdapter();
		dc5.outputDc5();
	}

}
