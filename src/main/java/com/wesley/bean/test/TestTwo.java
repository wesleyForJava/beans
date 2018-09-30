package com.wesley.bean.test;

public class TestTwo {
	  public static int a;
   public static void main(String[] args) {
		{
		    String[] office ={"律所1","律所2","律所3","律所4"};
		String[][] classes = {{"律师1","律师2","律师3"},
		{"律师1","律师2","律师3"},
		{"律师1","律师2","律师3"},
		{"律师1","律师2","律师3"},
		{"律师1","律师2","律师3"}};
		forEach(office,classes);
		}
	
}
 
	private static void forEach(String[]  classes,String[][] en){
		for (int i=0;i<classes.length;i++) {
		        System.out.println(classes[i]+" " +en[i][a]);
		}
		a++;
		    if (en[0].length > a){
		        forEach(classes,en);
		}
   }
}