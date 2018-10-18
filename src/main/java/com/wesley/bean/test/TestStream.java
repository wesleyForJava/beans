package com.wesley.bean.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestStream {
	static class User{
		private String name;
		private int age;
		public User(String name,int age) {
			this.name=name;
			this.age=age;
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
	public static void main(String[] args) {
		 List<User> list = Arrays.asList(
				      new User("张三", 11),
				      new User("王五", 20),
				      new User("王五", 91),
				      new User("张三", 8),
				      new User("李四", 44),
				      new User("李四", 44),
				      new User("李四", 44)
				  );
		  
		  //list.forEach(e ->System.out.println(e));
		  //list.stream().forEach(user->System.out.println(user));
		 // list.stream().sorted(Comparator.comparing(User::getAge)).forEach(user->System.out.println(user));O

		 // ExecutorService pool = Executors.newFixedThreadPool(2);
        //ExecutorService pool =Executors.newSingleThreadExecutor();
         //ExecutorService pool =Executors.newCachedThreadPool();
		 ScheduledExecutorService pool =	  Executors.newScheduledThreadPool(2);
		  Thread t1=new MyThread();
		  Thread t2=new MyThread();
		  Thread t3=new MyThread();
		  Thread t4=new MyThread();
		  Thread t5=new MyThread();
		  
		  pool.execute(t1);
		  pool.execute(t2);
		  pool.execute(t3);
		  pool.execute(t4);
		  pool.execute(t5);
		  
		  pool.schedule(t4, 10, TimeUnit.MILLISECONDS); 
          pool.schedule(t5, 10, TimeUnit.MILLISECONDS); 
		  pool.shutdown();
	}
	
	static class MyThread extends Thread{

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"正在执行方法。。。。。。");
		}
	
	}
	
	static class Person{
		private String firstName;
		private String lastName;
		
		public Person(String firstName, String lastName) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	}
}
