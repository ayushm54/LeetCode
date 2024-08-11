package org.ayush.problemsolving.recursion;

import java.util.Arrays;

public class ReverseArrayRecursion {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        reverseArrayRecursion2Pointer(nums, 0, nums.length-1);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1, 2, 3, 4, 5, 6};
        reverseArrayRecursion1Pointer(nums, 0 );
        System.out.println(Arrays.toString(nums));
    }

    public static void reverseArrayRecursion2Pointer(int[] arr, int l, int r) {
        if(l>=r) return;
        swap(arr, l, r);
        reverseArrayRecursion2Pointer(arr, l+1, r-1);
    }

    public static void reverseArrayRecursion1Pointer(int[] arr, int l) {
        if(l>=arr.length/2) return;
        swap(arr, l, arr.length-l-1);
        reverseArrayRecursion1Pointer(arr, l+1);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
