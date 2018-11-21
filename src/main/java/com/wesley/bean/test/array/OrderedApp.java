package com.wesley.bean.test.array;

public class OrderedApp {
	public static void main(String[] args) {
             int maxSize=100;
             OrdArray arr=new OrdArray(maxSize);
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
             
             
             int sreachKey=55;
             if(arr.find(sreachKey)!=arr.size()) {
            	 System.out.println("find "+sreachKey);
             }else {
            	 System.out.println("can't find "+sreachKey);
             }
             arr.display();
             
             arr.delete(00);
             arr.delete(55);
             arr.delete(99);
             
             arr.display();
	}
}
