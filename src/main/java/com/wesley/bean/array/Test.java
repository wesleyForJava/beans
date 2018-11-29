package com.wesley.bean.array;


public class Test {
       public static void main(String[] args) {
		  Array array=new Array();
		  array.addLast(1);
		  array.addLast(2);
		  array.addLast(3);
		  array.addFirst(4);
		  array.addFirst(5);
		 
		  System.out.println(array.getSize());
		  System.out.println(array.toString());
		  
		  System.out.println(array.get(1));
		  array.set(1, 6);
		  System.out.println(array.toString());
		 // array.remove(0);
		 // array.remove(1);
		  array.removeFirst();
		  array.removeLast();
		  array.removeElement(1);
		  System.out.println(array.toString());
		  
		  
		  
	}
}
