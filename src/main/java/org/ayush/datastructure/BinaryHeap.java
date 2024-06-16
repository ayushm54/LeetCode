package org.ayush.datastructure;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
* The Binary Heap is completely a Binary Tree, Meaning that all levels are fully filled except perhaps the last which is filled from left to right.
* This Structure allows binary heaps to be efficiently implemented in an array.
* And Given an index at i.
    The Parent node is at index (i-1)/2
    The left child is at index 2* i + 1
    The right child is at index 2* i + 2
* */
public class BinaryHeap<T extends Comparable<T>> {
    // A dynamic list to track the elements inside the heap
    private List<T> heap = null;

    // Construct and initially empty priority queue
    public BinaryHeap() {
        this(1);
    }

    // Construct a priority queue with an initial capacity
    public BinaryHeap(int sz) {
        heap = new ArrayList<>(sz);
    }

    public BinaryHeap(T[] elems) {

        int heapSize = elems.length;
        heap = new ArrayList<T>(heapSize);

        // Place all element in heap
        for (int i = 0; i < heapSize; i++) insert(elems[i]);

        // Heapify process, O(n) bubble up
        bubbleUp();
    }

    public void insert(T ele){
        heap.add(ele);
        bubbleUp();
    }

    /*
    * This method is used to arrange elements to satisfy the heap property(here min heap)
    * */
    private void bubbleUp(){
        int i = heap.size() - 1;
        T ele = heap.get(i);
        while (i>0 && heap.get(i).compareTo(heap.get(getParentIndex(i))) < 0){
            //swap(i, (i-1)/2);// swap the current element with its paren to fix ordering
            //heap.set(i, heap.get(¸));
            swap(i, getParentIndex(i));
            i = (i-1)/2; //move the current index to its parent and continue this process until heapify is done
        }
        heap.set(i, ele);
    }

    public int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private void swap(int i, int j) {
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        heap.set(i, elem_j);
        heap.set(j, elem_i);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }
    /*
    * Polling operation is used to remove the root node as we are always interested in the getting the min/max element*/
    public T poll(){
        return removeAt(0);
    }

    public T removeAt(int i){
        if (isEmpty()) return null;
        T ele = heap.get(i);
        int indexOfLastElem = size() - 1;        // first swap the first and last element and then bubble down to satisfy heap property
        swap(i, indexOfLastElem);
        heap.remove(indexOfLastElem);
        bubbleDown(i);
        return  ele;
    }

    private void bubbleDown(int i) {
        int heapSize = size();
        while (true){
            int leftChildIdx = getLeftChildIndex(i);
            int rightChildIdx = getRightChildIndex(i);
            int smallest = leftChildIdx; // Assume left is the smallest node of the two children

            if (rightChildIdx < heapSize && less(rightChildIdx, leftChildIdx)) {
                smallest = rightChildIdx;
            }
            // Stop if we're outside the bounds of the tree
            // or stop early if we cannot bubble down anymore
            if (leftChildIdx >= heapSize || less(i, smallest)) {
                break;
            }

            swap(i, smallest);
            i = smallest;
        }
    }

    public boolean remove(T ele){
        if (ele == null) return false;
        // Linear removal via search, O(n)
        for (int i = 0; i < size(); i++) {
            if (ele.equals(heap.get(i))) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int getLeftChildIndex(int index) {
        return 2*index + 1;
    }

    public int getRightChildIndex(int index) {
        return 2*index + 2;
    }

    // Recursively checks if this heap is a min heap
    // Call this method with k=0 to start at the root
    public boolean isMinHeap(int i) {
        // If we are outside the bounds of the heap return true
        int heapSize = size();
        if (i >= heapSize) return true;

        int left = getLeftChildIndex(i);
        int right = getRightChildIndex(i);

        // Make sure that the current node k is less than
        // both of its children left, and right if they exist
        // return false otherwise to indicate an invalid heap
        if (left < heapSize && !less(i, left)) return false;
        if (right < heapSize && !less(i, right)) return false;

        // Recurse on both children to make sure they're also valid heaps
        return isMinHeap(left) && isMinHeap(right);
    }

    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    public static void main(String[] args) {
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2,1});
        System.out.println(binaryHeap);
        System.out.println("Polled: " + binaryHeap.poll());
        System.out.println(binaryHeap);
        if(binaryHeap.remove(4)){
            System.out.println(binaryHeap);
        }else {
            System.out.println("Failed to remove");
        }
        System.out.println(binaryHeap.isMinHeap(0));
    }
}
