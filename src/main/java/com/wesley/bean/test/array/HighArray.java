package com.wesley.bean.test.array;


public class HighArray {

	private long [] a;
	private int nElems;
	public HighArray(int max) {
		this.a = new long[max];
		this.nElems=0;
	}
	
	public Boolean find(long sreachKey) {
		int j;
		for (j = 0; j < nElems; j++) {
			if(a[j]==sreachKey) {
				  break;
			}
		}
		if(j==nElems) {
			return false;
		}else {
			return true;
		}
	}
	/**
	 * 二分查找效率更高
	 * 前提是二叉树，按顺序排列
	 * @param sreachKey
	 * @return
	 */
	public int findByBinarySreach(long sreachKey) {
		int lowerBound=0;
		int upperBound=nElems-1;
		int curIn;
		
		while(true) {
			curIn=lowerBound+upperBound/2;
			if(a[curIn]==sreachKey) {
				return curIn;
			}else if (lowerBound>upperBound) {
				return nElems;
			}else {
				if (a[curIn]<sreachKey) {
					lowerBound=curIn+1;
				}else {
					upperBound=curIn-1;
				}
			}
		}
	}
	
	
	public void insert(long value) {
		a[nElems]=value;
		nElems++;
	}
	
	public boolean delete(long value) {
		int j;
		for (j = 0; j < nElems; j++) {
			if(a[j]==value) {
				break;
			}
		}
		
		if(j==nElems) {
			return false;
		}else {
			for (int k=j; k < nElems; k++)
				a[k]=a[k+1];
		}
		nElems--;
			return true;
		}
		
	
	public void display() {
		for (int j = 0; j < nElems; j++) {
			System.out.print(a[j]+"  ");
		}
		System.out.println(" ");
	}
	
	
	
}
