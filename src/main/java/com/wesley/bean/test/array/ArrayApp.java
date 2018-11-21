package com.wesley.bean.test.array;

public class ArrayApp {
	
	public static void main(String[] args) {
		long [] arr; //reference Array
		arr=new long[100]; //make array
		int nElems=0;  //number of items
		int j;     //loop counter
		long sreachKey;  //key of item to sreach for
		arr[0]=77;
		arr[1]=99;
		arr[2]=44;
		arr[3]=55;
		arr[4]=22;
		arr[5]=88;
		arr[6]=11;
		arr[7]=00;
		arr[8]=66;
		arr[9]=33;
		
		nElems=10;  //now 10 item in array
		for ( j = 0; j < nElems; j++) {//display item
			System.out.print(arr[j]+" ");
		}
		System.out.println("");
		sreachKey=66;
		
		for (j = 0; j < nElems; j++) {
			if(arr[j]==sreachKey) {
				break;
			}
		}
		if(j==nElems) {
			System.out.println("can't find "+sreachKey);
		}else {
			System.out.println("find "+sreachKey);
		}
		
		sreachKey=55;
		for (j = 0; j < nElems; j++) {
			if(arr[j]==sreachKey) {
				break;
			}
		}
		for (int k=j; k < nElems; k++) {
			arr[k]=arr[k+1];
		}
		nElems--;
		for (j = 0; j < nElems; j++) {
			System.out.print(arr[j]+" ");
		}
		System.out.println("");
	}

}
