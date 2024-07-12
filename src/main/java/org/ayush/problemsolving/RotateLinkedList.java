package org.ayush.problemsolving;
/*
Given the head of a linked list, rotate the list to the right by k places.



Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Example 2:


Input: head = [0,1,2], k = 4
Output: [2,0,1]


Constraints:

The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 109
* */
import org.ayush.datastructure.LinkedList;
import org.ayush.datastructure.ListNode;

public class RotateLinkedList {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        System.out.println(ll);
        ll.head = rotateRight(ll.head, 2);
        System.out.println(ll);

        ll = new LinkedList();
        ll.add(0);
        ll.add(1);
        ll.add(2);
        System.out.println(ll);
        ll.head = rotateRight(ll.head, 4);
        System.out.println(ll);

        ll = new LinkedList();
        ll.add(1);
        System.out.println(ll);
        ll.head = rotateRight(ll.head, 99);
        System.out.println(ll);

        ll = new LinkedList();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        System.out.println(ll);
        ll.head = rotateRight(ll.head, 10);
        System.out.println(ll);

    }

    private static ListNode rotateRight(ListNode head, int k) {
        if(head==null ) return head;
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            len+=1;
        }
        k = k % len;
        if(k==0 || len==1) return head; // if k is equal to length we will get the same list
        int i = 1;
        ListNode temp = head;
        while (i < len - k) {
            temp = temp.next;
            i+=1;
        }
        ListNode breakPointNode = temp.next;
        temp.next = null;
        tail.next = head;
        return breakPointNode;
    }
}
