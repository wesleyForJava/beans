package com.wesley.bean.dxc;

public class Threada extends Thread{

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public synchronized void start() {
		super.start();
	}

	@Override
	public void run() {
		super.run();
	}

	@Override
	public void interrupt() {
		super.interrupt();
	}

	@Override
	public boolean isInterrupted() {
		return super.isInterrupted();
	}
	public String toString() {
		return super.toString();
	}

	@Override
	public ClassLoader getContextClassLoader() {
		return super.getContextClassLoader();
	}

	@Override
	public void setContextClassLoader(ClassLoader cl) {
		super.setContextClassLoader(cl);
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return super.getStackTrace();
	}

	@Override
	public long getId() {
		return super.getId();
	}

	@Override
	public State getState() {
		return super.getState();
	}

	@Override
	public UncaughtExceptionHandler getUncaughtExceptionHandler() {
		return super.getUncaughtExceptionHandler();
	}

	@Override
	public void setUncaughtExceptionHandler(UncaughtExceptionHandler eh) {
		super.setUncaughtExceptionHandler(eh);
	}

}
