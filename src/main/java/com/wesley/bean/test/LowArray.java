package com.wesley.bean.test;

public class LowArray {
	
	private long [] a;

	public LowArray(int size) {
		a=new long[size];
	}
	
	public void setElem(int index,long value) {
		a[index]=value;
	}
	
	public long getElem(int index) {
		return a[index];
	}

}
