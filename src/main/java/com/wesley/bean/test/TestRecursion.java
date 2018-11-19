package com.wesley.bean.test;
/**
 * Smiple recursion demo
 * @author Wesley
 *
 */
public class TestRecursion {

	

	public static int sum(int [] arr,int l) {
		if(arr.length==l) return 0;
		return arr[l]+sum(arr, l+1);
	}
	
	public static void main(String[] args) {
		int[] arr= {1,3,5,7,9};
		System.out.println(sum(arr, 0));
	}
	
}
