package org.ayush.problemsolving;

import java.util.Arrays;

/*
* Given two sorted arrays, merge them to find the resulting sorted array.
*
* */
public class MergeTwoSortedArrays {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeTwoSortedArrays(new int[]{2, 4, 7, 9}, new int[]{1, 3, 5, 11, 13})));
    }

    private static int[] mergeTwoSortedArrays(int[] arr1, int[] arr2) {
        int[] mergedArr = new int[arr1.length+arr2.length];
        int l = 0, r= 0, k=0;

        while(l < arr1.length && r < arr2.length) {
            if (arr1[l] <= arr2[r]) {
                mergedArr[k] = arr1[l];
                l++;
            } else {
                mergedArr[k] = arr2[r];
                r++;
            }
            k++;
        }
        while (l < arr1.length){
            mergedArr[k] = arr1[l];
            l++;
            k++;
        }
        while (r < arr2.length){
            mergedArr[k] = arr2[r];
            r++;
            k++;
        }
        return mergedArr;
    }
}
