package org.ayush.arrays.datastructure;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T>{
    private T[] arr;
    private int length;
    private int capacity;

    public DynamicArray(){
        this(16);
    }

    public DynamicArray(int capacity){
        if(capacity < 0) throw new IllegalArgumentException("Illegal capacity: " + capacity);
        this.capacity = capacity;
        this.arr = (T[]) new Object[capacity];
    }

    public int size(){
        return this.length;
    }

    public int capacity(){
        return this.capacity;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public T get(int idx){
        return this.arr[idx];
    }

    public void set(T item, int idx){
        this.arr[idx] = item;
    }

    public void clear(){
        for(int i=0; i<this.length; i++){
            this.arr[i] = null;
        }
        this.length = 0;
    }

    public void add(T ele){
        if(this.length == capacity){
            /*Need to resize the array*/
            if(this.capacity == 0) this.capacity = 1;
            else this.capacity *= 2;
            T[] newArr = (T[]) new Object[this.capacity]; // New Array
            if (this.length >= 0) System.arraycopy(this.arr, 0, newArr, 0, this.length);
            this.arr = newArr;
        }
        this.arr[this.length++] = ele;
    }

    public T removeAt(int idx){
        if(idx > this.length || idx < 0) throw new IndexOutOfBoundsException();
        T ele = this.arr[idx];
        T[] newArr = (T[]) new Object[this.length-1]; // New Array
        for(int i =0, j=0; i < this.length; i++, j++){
            if(i == idx) j--;
            else newArr[j] = this.arr[i];
        }
        this.arr = newArr;
        this.capacity = --this.length;
        return ele;
    }

    public boolean remove(T obj){
        for(int i =0; i < this.length; i++){
            if(this.arr[i] == obj){
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(T obj){
        for(int i =0; i < this.length; i++){
            if(this.arr[i] == obj){
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
            int idx = 0;
            @Override
            public boolean hasNext() {
                return idx < length;
            }

            @Override
            public T next() {
                return arr[idx++];
            }
        };
    }

    @Override
    public String toString() {
        if(this.length == 0) return "[]";
        StringBuilder sb = new StringBuilder(this.length).append("[");
        for(int i =0; i < this.length-1; i++){
            sb.append(this.arr[i]).append(",");
        }
        sb.append(this.arr[this.length - 1]).append("]");
        return sb.toString();
    }
}
