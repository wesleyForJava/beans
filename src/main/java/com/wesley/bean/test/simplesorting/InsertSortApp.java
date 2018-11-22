package com.wesley.bean.test.simplesorting;

public class InsertSortApp {
	public static void main(String[] args) {
        int maxSize = 100 ;             // array size      
        ArrayIns arr  ;                 // reference to array
        arr = new ArrayIns(maxSize) ;   // create the array
         
        arr.insert(6);
        arr.insert(5);
        arr.insert(3);
        arr.insert(1);
        arr.insert(8);
        arr.insert(7);
        arr.insert(2);
        arr.insert(4);
         
        arr.display();                  // display items
                             
        arr.insertionSort();                // bubble sort them
                 
        arr.display();                  // display items again     
    }
 
}
