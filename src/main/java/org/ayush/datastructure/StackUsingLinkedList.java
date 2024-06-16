package org.ayush.datastructure;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class StackUsingLinkedList<T> implements Iterable<T> {
    private LinkedList<T> stack = new LinkedList<>();

    // Empty stack initalizer
    public StackUsingLinkedList() {
    }

    // Stack with initial element
    public StackUsingLinkedList(T element) {
        push(element);
    }

    public void push(T element){
        stack.addLast(element);
    }

    public T pop(){
        if(isEmpty()) throw new EmptyStackException();
        return stack.removeLast();
    }

    public T peek(){
        if(isEmpty()) throw new EmptyStackException();
        return stack.peekLast();
    }

    public int size(){
        return stack.size();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return stack.iterator();
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    public static void main(String[] args) {
        StackUsingLinkedList<Integer> stackUsingLinkedList = new StackUsingLinkedList<>();
        stackUsingLinkedList.push(1);
        stackUsingLinkedList.push(2);
        stackUsingLinkedList.push(3);
        stackUsingLinkedList.push(4);
        System.out.println(stackUsingLinkedList.size());
        System.out.println(stackUsingLinkedList.pop());
        System.out.println(stackUsingLinkedList.peek());
        System.out.println(stackUsingLinkedList);
    }
}
