package com.wesley.bean.test;

public class TestThreadLocal {

	private static ThreadLocal<User> tLocal = new ThreadLocal<User>() {
		@Override
		protected User initialValue() {
			return new User("tom", 12);
		}
		
	};


	
	
	static class User {
		private String name;
		private int age;

		public User(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "User [name=" + name + ", age=" + age + "]";
		}
		
	}

	static class TestThread extends Thread {

		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				User user = tLocal.get();
				user.setName(Thread.currentThread().getName());
				user.setAge(i);
				tLocal.set(user);
				System.out.println(tLocal.get());
			}
		}

	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new TestThread().start();
		}
		try {
			Thread.sleep(1000);
           System.out.println(Thread.currentThread().getName()+tLocal.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tLocal.remove();
	}
}
