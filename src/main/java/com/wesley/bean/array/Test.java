package com.wesley.bean.array;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.stream.Stream;

public class Test {
	public static void main(String[] args) {
//		  Array array=new Array();
//		  array.addLast(1);
//		  array.addLast(2);
//		  array.addLast(3);
//		  array.addFirst(4);
//		  array.addFirst(5);
//		 
//		  System.out.println(array.getSize());
//		  System.out.println(array.toString());
//		  
//		  System.out.println(array.get(1));
//		  array.set(1, 6);
//		  System.out.println(array.toString());
//		 // array.remove(0);
//		 // array.remove(1);
//		  array.removeFirst();
//		  array.removeLast();
//		  array.removeElement(1);
//		  System.out.println(array.toString());
    	   Stream.iterate(new int[] {0,1},t->new int[] {t[1],t[0]+t[1]}).map(t->t[0]).limit(10).forEach(System.out::println);
		  
    	      Callable<Object> oneCallable = new Tickets<Object>();
              FutureTask<Object> oneTask = new FutureTask<Object>(oneCallable);

             // Thread t = new Thread(oneTask);
              oneTask.run();
              System.out.println(Thread.currentThread().getName());
             // t.start();
	}

   }

   class Tickets<Object> implements Callable<Object>{

       //重写call方法
       @Override
       public Object call() throws Exception {
           // TODO Auto-generated method stub
           System.out.println(Thread.currentThread().getName()+"-->我是通过实现Callable接口通过FutureTask包装器来实现的线程");
           return null;
       }   
    	 
}
