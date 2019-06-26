package com.wesley.bean.pattern;

public interface Subject {
	void registerObserver(Observer o);
	void notifyObservers(String tweet);
}
