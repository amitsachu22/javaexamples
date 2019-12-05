package datastructures.linkedlist;

/*
Example for Linked List data structure
Refer to https://www.geeksforgeeks.org/linked-list-set-1-introduction/
*/


public class LinkedList {


    Node head;

    /*method to create a simple linked list with 3 nodes*/
    public static void main(String[] args) {

        /*start with empty list*/
        LinkedList llist = new LinkedList();

        /*create head node with empty pointer*/
        llist.head = new Node(1);

        /*create second node with empty pointer*/
        Node second = new Node(2);

        /*create third node with empty pointer*/
        Node third = new Node(3);


        /* Three nodes have been allocated dynamically.
          We have references to these three blocks as head,
          second and third

          llist.head        second              third
             |                |                  |
             |                |                  |
         +----+------+     +----+------+     +----+------+
         | 1  | null |     | 2  | null |     |  3 | null |
         +----+------+     +----+------+     +----+------+ */


        llist.head.next = second; //Link first head node with the second node

        /*  Now next of the first Node refers to the second.  So they
            both are linked.

         llist.head        second              third
            |                |                  |
            |                |                  |
        +----+------+     +----+------+     +----+------+
        | 1  |  o-------->| 2  | null |     |  3 | null |
        +----+------+     +----+------+     +----+------+ */

        second.next = third; // link second node with third node
        /*  Now next of the second Node refers to third.  So all three
            nodes are linked.

         llist.head        second              third
            |                |                  |
            |                |                  |
        +----+------+     +----+------+     +----+------+
        | 1  |  o-------->| 2  |  o-------->|  3 | null |
        +----+------+     +----+------+     +----+------+ */


        /*print the linked list*/
        llist.printLinkedList();

    }

    /*method for traversal of linked list */
    public void printLinkedList() {

        Node n = head;

        while (n != null) {

            System.out.println(n.data);

            n = n.next;

        }


    }

    /* Linked list Node.  This inner class is made static so that
   main() can access it */
    static class Node {

        int data;
        Node next;

        // Constructor to create a new node
        // Next is by default initialized
        // as null

        Node(int data) {
            this.data = data;
            next = null;
        }

    }


}
