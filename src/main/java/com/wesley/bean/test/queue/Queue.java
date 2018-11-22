package com.wesley.bean.test.queue;

public class Queue {
	 private int maxSize;
     private long[] queueArray;
     private int front;
     private int rear;
     private int nItem;
 
     public Queue(int s) {
         maxSize = s;
         queueArray = new long[maxSize];
         front = 0;
         rear = -1;
         nItem = 0;
     }
 
     //队列尾部插入元素
     public void insert(int value) {
         if(nItem==maxSize){
             throw new RuntimeException("队列满了，不能插入数据了");
         }
         if (rear == maxSize - 1) {
             rear = -1;
         }
         queueArray[++rear] = value;
         nItem++;
     }
 
     //队头删除元素
     public long remove() {
         if(nItem==0){
             throw new RuntimeException("队列中没有元素了，不能进行删除操作了");
         }
         long temp = queueArray[front++];
         if (front == maxSize) {
             front = 0;
         }
         nItem--;
         return temp;
     }
 
     //查看队头元素
     public long peek() {
         return queueArray[front];
     }
 
     //判断队列是否为空
     public boolean isEmpty() {
         return nItem == 0;
     }
 
     //判断队满
     public boolean isFull() {
         return nItem == maxSize;
     }
 
     //获取队列大小
     public int getSize() {
         return nItem;
     }
}
