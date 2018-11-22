package com.wesley.bean.test.firstlastlist;

//firstLastList.java
//demonstrates list with first and last references
//to run this program: C>java FirstLastApp
////////////////////////////////////////////////////////////////
class Link
{
public long dData;                 // data item
public Link next;                  // next link in list
//-------------------------------------------------------------
public Link(long d)                // constructor
   { dData = d; }
//-------------------------------------------------------------
public void displayLink()          // display this link
   { System.out.print(dData + " "); }
//-------------------------------------------------------------
}  // end class Link
////////////////////////////////////////////////////////////////
