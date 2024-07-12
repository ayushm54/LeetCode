package org.ayush.problemsolving;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 4, 1, 3};
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIdx = partition(arr, low, high);
            quickSort(arr, low, pivotIdx-1);
            quickSort(arr, pivotIdx+1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low, j = high;
        while (i < j) {
            while (arr[i] <= pivot && i<=high) i++; // Find the first element greater than pivot from left
            while (arr[j] > pivot && j>=low) j--; // Find the first element less than pivot from right
            if (i < j) {
                swap(arr, i, j); // Swap both this elements
            }
        }
        // Finally swap the pivot to put it at its right position
        // This will update the array by placing all the elements less than pivot on left and the greater ones on the right
        swap(arr, low, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
