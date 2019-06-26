package com.wesley.bean.mulitthreading;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyList {
	private static volatile CopyOnWriteArrayList<String> arrayList=new CopyOnWriteArrayList<String>();
	public static void main(String[] args) {
          arrayList.add("hello");
          arrayList.add("vivo");
          arrayList.add("welcome");
          arrayList.add("to");
          arrayList.add("shenzhen");
          Thread threadOne=new Thread(new Runnable() {
			
			@Override
			public void run() {
				arrayList.set(1, "baba");
				arrayList.remove(2);
				arrayList.remove(3);
			}
		});
          Iterator<String> iterator = arrayList.iterator();
          
          threadOne.start();
          
          try {
			threadOne.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
          while (iterator.hasNext()) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}

}
