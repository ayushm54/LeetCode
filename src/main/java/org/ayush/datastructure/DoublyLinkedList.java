package org.ayush.datastructure;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;
    private static class Node<T>{
        T data;
        Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public int size(){ return this.size; }

    public boolean isEmpty(){
        return size() == 0;
    }

    /* Empty a linked list*/
    public void clear(){
        Node<T> temp = head;
        while (temp != null){
            Node<T> next = temp.next;
            temp.prev = temp.next = null;
            temp.data = null;
            temp = next;
        }
        head = tail = null;
        size = 0;
    }

    public void addFirst(T data){
        Node<T> newNode = new Node<>(data, null, null);
        if(isEmpty()){
            head = tail = newNode;
        }else{
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(T data){
        if(isEmpty()) addFirst(data);
        else{
            Node<T> newNode = new Node<>(data, tail, null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /* Get the value of first node*/
    public T peekFirst(){
        if(isEmpty()) throw new RuntimeException("List is empty");
        return head.data;
    }

    /* Get the value of last node*/
    public T peekLast(){
        if(isEmpty()) throw new RuntimeException("List is empty");
        return tail.data;
    }

    public void add(T data){
        addLast(data);
    }

    public T removeFirst(){
        if(isEmpty()) throw new RuntimeException("List is empty");
        T data = head.data;
        if(size == 1){
            head = tail = null;
            size = 0;
            return data;
        }else {
            Node<T> temp = head;
            head = temp.next;
            head.prev = null;
            temp.next = null;
            size -= 1;
            return data;
        }
    }

    public T removeLast(){
        if(isEmpty()) throw new RuntimeException("List is empty");
        T data = tail.data;
        if(size == 1){
            head = tail = null;
            size = 0;
            return data;
        }else {
            Node<T> temp = tail;
            tail = temp.prev;
            tail.next = null;
            temp.prev = null;
            size -= 1;
            return data;
        }
    }

    public T remove(Node<T> node){
        if(node.prev == null){
            /*This means the node is the head node*/
            return removeFirst();
        }
        if(node.next == null){
            /*This means the node is the head node*/
            return removeLast();
        }

        // Adjust the pointers of adjacent nodes
        node.prev.next = node.next;
        node.next.prev = node.prev;

        T data = node.data;
        node.data = null;
        node.prev = node.next = null;

        size--;

        return data;
    }

    public T removeAt(int idx){
        if(idx < 0 || idx >= size) throw new IllegalArgumentException("Index out of bounds.");
        int i;
        Node<T> node;
        if(idx < size/2){
            /*Search From Front*/
            for(i = 0, node = head; i!=idx; i++){
                node = node.next;
            }

        }else{
            /* Search from last*/
            for(i = size-1, node = tail; i!=idx; i--){
                node = node.prev;
            }
        }
        return remove(node);
    }

    public boolean remove(T obj){
        for(Node<T> temp = head; temp != null; temp = temp.next){
            if(temp.data.equals(obj)){
                remove(temp);
                return true;
            }
        }
        return false;
    }

    public int indexOf(T obj){
        int i = 0;
        for(Node<T> temp = head; temp != null; temp = temp.next, i++){
            if(temp.data.equals(obj)){
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T obj){
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> temp = head;
            @Override
            public boolean hasNext() {
                return temp!=null;
            }

            @Override
            public T next() {
                T data = temp.data;
                temp = temp.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("[");
        for(Node<T> temp = head; temp != null; temp = temp.next){
            sb.append(temp.data).append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> doublyLinkedList= new DoublyLinkedList<>();
        doublyLinkedList.addFirst(1);
        doublyLinkedList.addLast(2);
        System.out.println("Size: " + doublyLinkedList.size());
        doublyLinkedList.add(3);
        doublyLinkedList.add(4);
        doublyLinkedList.addFirst(5);
        doublyLinkedList.addLast(6);
        System.out.println(doublyLinkedList);
        System.out.println("Size: " + doublyLinkedList.size());
        System.out.println("Element removed from start: " +doublyLinkedList.removeFirst());
        System.out.println("Element removed from last: " +doublyLinkedList.removeLast());
        System.out.println("Element removed: " +doublyLinkedList.remove(2));
        System.out.println(doublyLinkedList);
    }
}
