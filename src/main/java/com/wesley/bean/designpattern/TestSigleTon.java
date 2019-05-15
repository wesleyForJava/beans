package com.wesley.bean.designpattern;

public class TestSigleTon {
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new ExcutorThread());
		Thread t2 = new Thread(new ExcutorThread());
	    t1.start();
	    t2.start();
		
	}
}
