package com.wesley.bean.test.link;

class LinkListApp
{
public static void main(String[] args)
   {
   LinkList theList = new LinkList();  // make new list

   theList.insertFirst(22, 2.99);      // insert four items
   theList.insertFirst(44, 4.99);
   theList.insertFirst(66, 6.99);
   theList.insertFirst(88, 8.99);

   theList.displayList();              // display list

   while( !theList.isEmpty() )         // until it's empty,
      {
      Link aLink = theList.deleteFirst();   // delete link
      System.out.print("Deleted ");         // display it
      aLink.displayLink();
      System.out.println("");
      }
   theList.displayList();              // display list
   }  // end main()
}  // end class LinkListApp
////////////////////////////////////////////////////////////////

