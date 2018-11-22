package com.wesley.bean.test.simplesorting;

public class SelectSortApp {
	public static void main(String[] args) {
        int maxSize=100;
        ArraySel arr;
        arr=new ArraySel(maxSize);
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
        arr.selectionSort();
        arr.display();
   }

}
