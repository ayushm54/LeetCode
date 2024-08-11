package org.ayush.problemsolving.linkedlist;

import org.ayush.datastructure.LinkedList;
import org.ayush.datastructure.ListNode;

/*
* You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.
*
Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
* */
public class Add2NumsLinkedList {
    public static void main(String[] args) {
        LinkedList ll1 = new LinkedList();
        ll1.add(2);
        ll1.add(4);
        ll1.add(3);
        System.out.println(ll1);

        LinkedList ll2 = new LinkedList();
        ll2.add(5);
        ll2.add(6);
        ll2.add(4);
        System.out.println(ll2);

        LinkedList ll3 = new LinkedList();
        //ll3.head = addTwoNumbers(ll1.head, ll2.head);
        //System.out.println(ll3);

        ll1 = new LinkedList();
        ll1.add(9);
        ll1.add(9);
        ll1.add(9);
        ll1.add(9);
        ll1.add(9);
        ll1.add(9);
        ll1.add(9);

        ll2 = new LinkedList();
        ll2.add(9);
        ll2.add(9);
        ll2.add(9);
        ll2.add(9);

        ll3.head = addTwoNumbers(ll1.head, ll2.head);
        System.out.println(ll3);
    }

    private static ListNode addTwoNumbers(ListNode list1, ListNode list2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (list1 != null && list2 != null) {
            int sum = list1.val + list2.val;
            sum+=carry;
            ListNode newNode = new ListNode(0);
            if(sum >= 10){
                carry = sum/10;
                newNode.val = sum%10;
            }else{
                carry = 0;
                 newNode.val = sum;
            }
            if(head == null) {
                head = newNode;
                tail = head;
            }else {
                tail.next = newNode;
                tail = tail.next;
            }
            list1 = list1.next;
            list2 = list2.next;
        }

        while (list1 != null) {
            int sum = list1.val + carry;
            if(sum >= 10){
                carry = sum/10;
                tail.next = new ListNode(sum%10);
            }else{
                carry = 0;
                tail.next = new ListNode(sum);
            }
            list1 = list1.next;
            tail = tail.next;
        }
        while (list2 != null) {
            int sum = list2.val + carry;
            if(sum >= 10){
                carry = sum/10;
                tail.next = new ListNode(sum%10);
            }else{
                carry = 0;
                tail.next = new ListNode(sum);
            }
            list2 = list2.next;
            tail = tail.next;
        }
        if(carry > 0){
            tail.next = new ListNode(carry);
            tail = tail.next;
        }
        return head;
    }
}
