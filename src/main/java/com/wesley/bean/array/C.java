package com.wesley.bean.array;


public class C implements B, A {
	   public static void main(String... args) {
	   System.out.println(new C().getNumber());
	   }

	@Override
	public Integer getNumber() {
		return (Integer) A.super.getNumber();
	}


	

	   
}
