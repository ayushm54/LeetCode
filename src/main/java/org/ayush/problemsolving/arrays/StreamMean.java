package org.ayush.problemsolving.arrays;

import java.util.LinkedList;
import java.util.Queue;

/*
Given an big stream of numbers, you have to return the mean of the last K elements in the stream. For example:-
for arr = [50, 60, 70, 50, 100, 120, 80, 140](arr represents the order in which elements came in the stream) and K = 5,

Firstly 50 comes into the stream, mean = 50
Then, 60 comes into the stream, mean = 55
Then, 70 comes into the stream, mean = 60
Then, 50 comes into the stream, mean = 57.5
Then, 100 comes into the stream, mean = 66
Then, 120 comes into the stream, mean = 80 (because 1st 50 is no longer in the last K elements)
Then, 80 comes into the stream, mean = 84 (because 60 is no longer in the last K elements)
* */
public class StreamMean {
    public static void main(String[] args) {
        System.out.println(mean(new int[]{50, 60, 70, 50, 100, 120, 80, 140}, 5));
    }

    private static int mean(int[] arr, int k) {
        Queue<Integer> queue = new LinkedList<>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if(queue.size() < k){
                queue.add(arr[i]);
                sum += arr[i];
            }else {
                sum -= queue.poll();
                queue.add(arr[i]);
                sum += arr[i];
            }
        }
        return sum/k;
    }
}
