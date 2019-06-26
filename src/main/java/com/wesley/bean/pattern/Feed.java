package com.wesley.bean.pattern;

import java.util.ArrayList;
import java.util.List;

class Feed implements Subject {
	private final List<Observer> observers = new ArrayList<>();

	public void registerObserver(Observer o) {
		this.observers.add(o);
	}

	public void notifyObservers(String tweet) {
		observers.forEach(o -> o.notify(tweet));
	}
	public static void main(String[] args) {
		Feed f = new Feed();
		f.registerObserver((String tweet)->{
			if (tweet != null && tweet.contains("money")) {
				System.out.println("Breaking news in NY! " + tweet);
			}
		});
		f.registerObserver((String tweet)->{if (tweet != null && tweet.contains("queen")) {
			System.out.println("Yet another news in London... " + tweet);
		}});
		f.registerObserver((String tweet)->{
			if (tweet != null && tweet.contains("wine")) {
				System.out.println("Today cheese, wine and news! " + tweet);
			}
		});
		System.out.println("=====================");
		f.notifyObservers("The wine said her favourite book is Java 8 in Action!");
	}
	
//	责任链模式是一种创建处理对象序列（比如操作序列）的通用方案。一个处理对象可能需要
//	在完成一些工作之后，将结果传递给另一个对象，这个对象接着做一些工作，再转交给下一个处
//	理对象，以此类推。
	
}
