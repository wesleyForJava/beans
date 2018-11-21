package com.wesley.bean.test.array;

public class HighArrayApp {
	public static void main(String[] args) {
       int maxSize=15;
       HighArray arr=new HighArray(maxSize);
       
       arr.insert(77);
       arr.insert(99);
       arr.insert(44);
       arr.insert(55);
       arr.insert(22);
       arr.insert(88);
       arr.insert(11);
       arr.insert(00);
       arr.insert(66);
       arr.insert(33);
       
       arr.display();
       
       int sreachKey=35;
       if(arr.find(sreachKey)) {
    	   System.out.println("find "+sreachKey);
       }else {
    	   System.out.println("Can't find "+sreachKey);
       }
       
       arr.delete(00);
       arr.delete(55);
       arr.delete(99);
       
       arr.display();
       
	}
}
