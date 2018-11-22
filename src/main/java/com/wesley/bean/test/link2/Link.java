package com.wesley.bean.test.link2;

//linkList2.java
//demonstrates linked list
//to run this program: C>java LinkList2App
////////////////////////////////////////////////////////////////
class Link
{
public int iData;              // data item (key)
public double dData;           // data item
public Link next;              // next link in list
//-------------------------------------------------------------
public Link(int id, double dd) // constructor
   {
   iData = id;
   dData = dd;
   }
//-------------------------------------------------------------
public void displayLink()      // display ourself
   {
   System.out.print("{" + iData + ", " + dData + "} ");
   }
}  // end class Link
////////////////////////////////////////////////////////////////
