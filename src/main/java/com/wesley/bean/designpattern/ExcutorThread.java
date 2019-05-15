package com.wesley.bean.designpattern;

public class ExcutorThread implements Runnable {
	@Override
	public void run() {
		LazySimpleSingleton lazySimpleSingleton=LazySimpleSingleton.getInstance();
		System.out.println(Thread.currentThread().getName()+","+lazySimpleSingleton);
	}
}