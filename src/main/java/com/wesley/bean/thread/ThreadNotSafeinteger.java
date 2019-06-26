package com.wesley.bean.thread;

public class ThreadNotSafeinteger {
	 private volatile int value;

	    public int get() {
	        return value;
	    }

	    public void set(int value) {
	        this.value = value;
	    }
}
