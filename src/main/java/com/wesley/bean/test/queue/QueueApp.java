package com.wesley.bean.test.queue;

public class QueueApp {
	public static void main(String[] args) {
	      Queue theQueue=new Queue(10);
	          theQueue.insert(3);
	          theQueue.insert(13);
	          theQueue.insert(23);
	          theQueue.insert(33);
	          theQueue.insert(43);
	          theQueue.insert(53);
	          System.out.println(theQueue.peek());
	          System.out.println(theQueue.remove());
	          theQueue.insert(63);
	          theQueue.insert(73);
	          theQueue.insert(83);
	          theQueue.insert(93);
	  //        theQueue.insert(103);
	          System.out.println(theQueue.peek());
	          System.out.println(theQueue.remove());
	          while(!theQueue.isEmpty()){
	              System.out.print(theQueue.remove()+" ");
	          }
	          System.out.println();
	}
	          
}
