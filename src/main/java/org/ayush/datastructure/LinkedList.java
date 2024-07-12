package org.ayush.datastructure;

public class LinkedList {
    public ListNode head;

    public void add(int val) {
        ListNode newNode = new ListNode(val);
        if (this.head == null) {
            this.head = newNode;
            return;
        }
        if (this.head.next == null) {
            this.head.next = newNode;
            return;
        }
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    @Override
    public String toString() {
        String s = "";
        ListNode temp = head;
        while (temp != null) {
            s+=temp.val + "->";
            temp = temp.next;
        }
        s+="null";
        return s;
    }
}