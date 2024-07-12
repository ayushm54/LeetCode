package org.ayush.problemsolving;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 4, 1, 3};
        mergeSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int mid = (low + high) / 2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid+1, high);
        merge(arr, low, mid, high);
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        int[] x = new int[high - low + 1];
        int k =0;
        while (left < mid+1 && right < high+1) {
            if (arr[left] <= arr[right]) {
                x[k] = arr[left];
                left+=1;
            }else {
                x[k] = arr[right];
                right+=1;
            }
            k+=1;
        }
        while(left<mid+1){
            x[k] = arr[left];
            left+=1;
        }
        while(right<high+1){
            x[k] = arr[right];
            right+=1;
        }
        for(int i=low;i<=high;i++){
            arr[i] = x[i-low];
        }
    }

}
