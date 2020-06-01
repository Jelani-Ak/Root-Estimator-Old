package slinkedlist;

import java.util.*;

public class SLinkedList {

  public static StringNode head;

  public SLinkedList() {
    head = new StringNode();
  }

  //add a new node to the head of the list
  public void addFirst(String newton) {
    // make variable head point to new node
    head = new StringNode("", head);
  }

  public void addLast(String newton) {
    StringNode tail = head;
    while (tail.getNext() != null) {
      tail = tail.getNext();
    }
    //insert new node at end of list
    tail.setNext(new StringNode(newton, null));
  }

  //add a new node after position of curnode
  public void addMid(String newton, String entryafter) {
    StringNode curnode = head;
    //go to last node and remember previous node at all times
    while (curnode != null && !curnode.getNewton().equals(entryafter)) { //If current node is not null and current node does not equal 'entryafter'
      curnode = curnode.getNext(); //Move on to the next node
    }
    //if first occurrence of element entryafter was located then insert new node
    if (curnode != null) {
      StringNode newnode = new StringNode("", curnode.getNext());
      curnode.setNext(newnode);
    }
  }

  public boolean isEmpty() {
    return head == null;
  }

  public void removeFirst() {
    StringNode oldhead;
    oldhead = head;
    //remove first node from linked list
    if (head != null) {
      head = head.getNext();
      oldhead.setNext(null);
    } else {
      throw new NoSuchElementException();
    }
  }

  public void removeLast() {
    StringNode temp;
    StringNode previous;
    temp = head; //Last node
    previous = temp; //Variable before last node
    //go to last node and remember previous node at all times
    while (temp != null && temp.getNext() != null) { //Iterate through each element
      previous = temp;  //Set to node before temp
      temp = temp.getNext(); //Set to next node
    }
    if (previous != null) {
      //remove last node
      previous.setNext(null);
    } else {
      throw new NoSuchElementException();
    }
  }

  //very similar to removeLast except we are looking for element i
  public void removeMid(String element) {
    StringNode temp;
    StringNode previous;
    temp = head.getNext();
    previous = null;
    //go to node containing element and rermember previous node at all times
    while (!temp.getNewton().equals(element) && temp.getNext() != null) {
      previous = temp;
      temp = temp.getNext();
    }
    if (previous != null && temp.getNext() != null) {
      //not first or last node so we can remove node defined by temp. 
      //set the previous node to that after temp
      previous.setNext(temp.getNext());
      temp.setNext(null);
    } else {
      throw new NoSuchElementException();
    }
  }

  public static void printList(SLinkedList theList) {
    StringNode temp;

    if (theList.isEmpty()) {
      System.out.println("List is empty");
    } else {
      temp = theList.head;
      while (temp != null) {
        System.out.print("[" + temp.getNewton() + "] ");
        temp = temp.getNext();
      }
      System.out.println();
    }
  }

  public static int countListTotal(SLinkedList theList) {
    StringNode currentNode = head;
    int count = 0;

    while (currentNode != null) {
      count++;
      currentNode = currentNode.getNext();
    }
    return count;
  }
}
