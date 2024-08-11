package org.ayush.problemsolving.linkedlist;

import org.ayush.datastructure.LinkedList;
import org.ayush.datastructure.ListNode;

/*
* You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

Example 1:
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:
Input: list1 = [], list2 = []
Output: []

Example 3:
Input: list1 = [], list2 = [0]
Output: [0]
* */
public class MergeSortedLinkedList {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add(1);
        ll.add(2);
        ll.add(4);

        LinkedList ll2 = new LinkedList();
        ll2.add(1);
        ll2.add(3);
        ll2.add(4);

        //ll.head = mergeTwoLists(ll.head, ll2.head);
        //System.out.println(ll);

        ll = new LinkedList();
        ll2 = new LinkedList();
        ll.head = mergeTwoLists(ll.head, ll2.head);
        System.out.println(ll);

        ll = new LinkedList();
        ll2 = new LinkedList();
        ll2.add(0);
        ll.head = mergeTwoLists(ll.head, ll2.head);
        System.out.println(ll);
    }

    private static ListNode mergeTwoLists(ListNode list1, ListNode list2){
        ListNode head = null;
        ListNode temp = head;

        while(list1!=null && list2!=null){
            if(list1.val <= list2.val){
                ListNode ll = new ListNode(list1.val);
                if(head==null){
                    head = ll;
                    temp = head;
                }else{
                    temp.next = ll;
                    temp = temp.next;
                }
                list1 = list1.next;
            }else{
                ListNode ll = new ListNode(list2.val);
                if(head==null){
                    head = ll;
                    temp = head;
                }else{
                    temp.next = ll;
                    temp = temp.next;
                }
                list2 = list2.next;
            }
        }
        while(list1!=null){
            ListNode ll = new ListNode(list1.val);
            if(head==null){
                head = ll;
                temp = head;
            }else {
                temp.next = ll;
                temp = temp.next;
            }
            list1 = list1.next;
        }

        while(list2!=null){
            ListNode ll = new ListNode(list2.val);
            if(head==null){
                head = ll;
                temp = head;
            }else {
                temp.next = ll;
                temp = temp.next;
            }
            list2 = list2.next;
        }
        return head;
    }
}
