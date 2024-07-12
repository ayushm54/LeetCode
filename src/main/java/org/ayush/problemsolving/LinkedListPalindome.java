package org.ayush.problemsolving;

import org.ayush.datastructure.LinkedList;
import org.ayush.datastructure.ListNode;

import java.util.ArrayList;
import java.util.List;

/*
* Given the head of a singly linked list, return true if it is a
palindrome or false otherwise.

*Input: head = [1,2,2,1]
Output: true
*
* Input: head = [1,2]
Output: false
*  */
public class LinkedListPalindome {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add(1);
        ll.add(2);
        ll.add(2);
        ll.add(1);
        System.out.println(ll);
        System.out.println(isPalindrome(ll.head));
        System.out.println(isPalindrome2(ll.head));
    }

    public static boolean isPalindrome(ListNode head) {
        List<Integer> l = new ArrayList<>();
        while(head!=null){
            l.add(head.val);
            head = head.next;
        }
        System.out.println(l);
        int i =0, j=l.size()-1;
        while(i<j){
            if(l.get(i)==l.get(j)){
                i+=1;
                j-=1;
            }else {
                return false;
            }
        }
        return true;
    }

    /*
    * We have two pounters slow and fast, when fast reaches the endd, slow will be in the middle
    * Then we can run two pointers one from start and the other from end, but since we only have next pointer we cant movve the fast in reverse
    * So we can reverse the linked list from slow to fast and can move the head and mslow forward
    * */
    public static boolean isPalindrome2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // Finding middle, the place where slow pointer reaches
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // Reverse second half
        ListNode right = reverseList(slow);

        // Prev will now point the end of the list, but since the list is reversed, prev.next will move to left with every iteration
        ListNode left = head;
        while(right != null){
            if(left.val!=right.val) return false;
            left = left.next;
            right=right.next;
        }
        return true;
    }

    private static ListNode reverseList(ListNode curr) {
        ListNode prev = null;
        while(curr!=null){
             ListNode tmp = curr.next;
             curr.next = prev;
             prev = curr;
             curr = tmp;
        }
        return prev;
    }
}