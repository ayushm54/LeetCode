package org.ayush.problemsolving.twopointer;

import java.util.Arrays;

/*
* Given an array of random numbers, Push all the zero’s of a given array to the end of the array. For example, if the given arrays is {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0}, it should be changed to {1, 9, 8, 4, 2, 7, 6, 0, 0, 0, 0}. The order of all other elements should be same. Expected time complexity is O(n) and extra space is O(1).

Example:

Input :  arr[] = {1, 2, 0, 4, 3, 0, 5, 0};
Output : arr[] = {1, 2, 4, 3, 5, 0, 0, 0};

Input : arr[]  = {1, 2, 0, 0, 0, 3, 6};
Output : arr[] = {1, 2, 3, 6, 0, 0, 0};
* */
public class PushAllZerosToEndOfArray {
    public static void main(String[] args) {
        int arr[] = new int[]{1, 2, 0, 4, 3, 0, 5, 0};
        pushAllZerosToEndOfArray(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{1, 2, 0, 0, 0, 3, 6};
        pushAllZerosToEndOfArray(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void pushAllZerosToEndOfArray(int[] arr) {
        int j = 0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i]!=0) {
                // swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
    }
}
