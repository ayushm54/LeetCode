package org.ayush.datastructure;

public class DoublyLinkedListWithChild {
    public static class Node{
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int _val) {
            this.val = _val;
        }
    }

    public Node head;

    public void add(int val){
        Node newNode = new Node(val);
        if (this.head == null) {
            this.head = newNode;
            return;
        }
        if (this.head.next == null) {
            this.head.next = newNode;
            newNode.prev = this.head;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.prev = temp;
    }

    public void addChildList(int val, Node childhead){
        Node temp = head;
        while (temp.next != null) {
            if(temp.val == val) break;
            temp = temp.next;
        }
        temp.child = childhead;
    }
    @Override
    public String toString() {
        String s = "";
        Node temp = head;
        while (temp != null) {
            s+=temp.val + "->";
            temp = temp.next;
        }
        s+="null";
        return s;
    }
}
