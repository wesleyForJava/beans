package com.wesley.bean.test.array;

public class OrdArray {
	private long [] a;
	private int nElems;
	
	
   public OrdArray(int max) {
	   this.a = new long[max];
		this.nElems=0;
   }
   
   public int size() {
	return nElems;
   }
   
   
   public int find(long sreachKey) {
	   int lowerBound=0;
		int upperBound=nElems-1;
		int curIn;
		while(true) {
			curIn=(lowerBound+upperBound)/2;
			if(a[curIn]==sreachKey) {
				return curIn;
			}else if (lowerBound>upperBound) {
				return nElems;
			}else {
				if (a[curIn]<sreachKey) {//如果当前的值小于查找的value
					lowerBound=curIn+1; //那么就在右边中查找，当前位置+1
				}else {
					upperBound=curIn-1;//否则就在左边中查找，当前位置减1
				}
			}
		}
   }
   
   public void insert(long value) {  
	   int j;
	   for (j = 0; j < nElems; j++) { //find where is gone  找到要插入的地方
		if(a[j]>value) { //找到比这个值大的最近value的索引  此处为1
			break;
		}
	}
	   for (int k = nElems; k > j; k--) {//move bigger ones up  从j位置开始向后移动
		a[k]=a[k-1];
		
	}
	   a[j]=value;//然后把此处为1的索引的位置赋值给value
	   nElems++;
   }
   
   public boolean delete(long value) {
	   int j=find(value); //找到这个值的索引
	   if(j==nElems) {  //如果这个值的索引大于数组的长度 表示不存在
		   return false;
	   }else {
		   for (; j<nElems; j++) {//从这个值的索引开始到结束 统一移位一格 然后length减一 达到删除的效果
			a[j]=a[j+1];
		}
		   nElems--;
		   return true;
	   }
   }
   
   public void display() {
	   for (int i = 0; i < nElems; i++) {
		System.out.print(a[i]+" ");
	}
	   System.out.println(" ");
   }
   
   
   
}
