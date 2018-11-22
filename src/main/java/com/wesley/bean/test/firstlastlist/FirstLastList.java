package com.wesley.bean.test.firstlastlist;

class FirstLastList
{
private Link first;               // ref to first link
private Link last;                // ref to last link
//-------------------------------------------------------------
public FirstLastList()            // constructor
   {
   first = null;                  // no links on list yet
   last = null;
   }
//-------------------------------------------------------------
public boolean isEmpty()          // true if no links
   { return first==null; }
//-------------------------------------------------------------
public void insertFirst(long dd)  // insert at front of list
   {
   Link newLink = new Link(dd);   // make new link

   if( isEmpty() )                // if empty list,
      last = newLink;             // newLink <-- last
   newLink.next = first;          // newLink --> old first
   first = newLink;               // first --> newLink
   }
//-------------------------------------------------------------
public void insertLast(long dd)   // insert at end of list
   {
   Link newLink = new Link(dd);   // make new link
   if( isEmpty() )                // if empty list,
      first = newLink;            // first --> newLink
   else
      last.next = newLink;        // old last --> newLink
   last = newLink;                // newLink <-- last
   }
//-------------------------------------------------------------
public long deleteFirst()         // delete first link
   {                              // (assumes non-empty list)
   long temp = first.dData;
   if(first.next == null)         // if only one item
      last = null;                // null <-- last
   first = first.next;            // first --> old next
   return temp;
   }
//-------------------------------------------------------------
public void displayList()
   {
   System.out.print("List (first-->last): ");
   Link current = first;          // start at beginning
   while(current != null)         // until end of list,
      {
      current.displayLink();      // print data
      current = current.next;     // move to next link
      }
   System.out.println("");
   }
//-------------------------------------------------------------
}  // end class FirstLastList
////////////////////////////////////////////////////////////////
