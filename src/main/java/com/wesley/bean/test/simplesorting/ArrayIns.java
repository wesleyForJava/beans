package com.wesley.bean.test.simplesorting;

public class ArrayIns {
	private long[] a ;              // ref to Array a
    private int nElems ;            // number of data items
     
    public ArrayIns(int max){       // constructor
        a = new long[max] ;         // create the array
        nElems = 0 ;                // no items yet
    }
     
    public void insert(long value){ // put element into array
        a[nElems] = value ;         // insert it
        nElems++ ;                  // increment size
    }
     
    public void display(){          // display array contents
        for (int i = 0; i < nElems; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
     
    public void insertionSort(){
        int out , in ;
        for (out = 1; out < nElems; out++) {  // outer loop
            long temp = a[out] ;              // remove marked item
            in = out ;                        // start shifts at out
            while(in > 0 && a[in-1] >= temp){
                a[in] = a[in-1] ;             // shift item to right
                in-- ;                        // go left one position
            }
            a[in] = temp ;                    // insert marked item
        }
    }

}
