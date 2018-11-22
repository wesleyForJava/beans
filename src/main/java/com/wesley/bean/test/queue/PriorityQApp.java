package com.wesley.bean.test.queue;

public class PriorityQApp
{
public static void main(String[] args)
   {
   PriorityQ thePQ = new PriorityQ(5);
   thePQ.insert(30);
   thePQ.insert(50);
   thePQ.insert(10);
   thePQ.insert(40);
   thePQ.insert(20);

   while( !thePQ.isEmpty() )
      {
      long item = thePQ.remove();
      System.out.print(item + " ");  // 10, 20, 30, 40, 50
      long peekMin = thePQ.peekMin();
      }  // end while
   System.out.println("");
   //System.out.print(thePQ.peekMin());
   }  // end main()
//-------------------------------------------------------------
}  // end class PriorityQApp
////////////////////////////////////////////////////////////////
