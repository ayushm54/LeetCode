package org.ayush.datastructure;

import java.util.Iterator;
import java.util.LinkedList;

public class QueueUsingLinkedList<T> implements Iterable<T> {
    private LinkedList<T> queue = new LinkedList<>();

    public QueueUsingLinkedList(){}

    public QueueUsingLinkedList(T element){
        enqueue(element);
    }

    public void enqueue(T element) {
        queue.addLast(element);
    }

    public T dequeue() {
        if(isEmpty()) throw new RuntimeException("Queue is Empty.");
        return queue.removeFirst();
    }

    public T peek() {
        if(isEmpty()) throw new RuntimeException("Queue is Empty.");
        return queue.peekFirst();
    }

    public int size(){
        return queue.size();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return queue.iterator();
    }

    @Override
    public String toString() {
        return queue.toString();
    }

    public static void main(String[] args) {
        QueueUsingLinkedList<Integer> queueUsingLinkedList = new QueueUsingLinkedList<>(1);
        queueUsingLinkedList.enqueue(2);
        queueUsingLinkedList.enqueue(3);
        System.out.println("Size: "+queueUsingLinkedList.size());
        System.out.println(queueUsingLinkedList);
        System.out.println("Polled: "+queueUsingLinkedList.dequeue());
        System.out.println(queueUsingLinkedList);
    }
}
