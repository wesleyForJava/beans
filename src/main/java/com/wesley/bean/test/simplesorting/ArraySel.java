package com.wesley.bean.test.simplesorting;

public class ArraySel{
	 private long[] a;
	   private int nElems;
	   public ArraySel(int max){
		   a=new long[max];
		   nElems=0;
	   }
	   public void insert(long value){
		   a[nElems]=value;
		   nElems++;
	   }
	   public void display(){
		   for(int j=0;j<nElems;j++){
			   System.out.print(a[j]+" ");
		   }
		   System.out.println();
	   }
	   //选择排序
	   public void selectionSort(){
		   int out,in,min;
		   for(out=0;out<nElems;out++){
			   min=out;
			   for(in=out+1;in<nElems;in++){
				   if(a[in]<a[min])
					   min=in;
			   }
			   swap(out,min); 
		   }
	   }
	   private void swap(int one,int two){
		   long temp=a[one];
		   a[one]=a[two];
		   a[two]=temp;
	   }
}
