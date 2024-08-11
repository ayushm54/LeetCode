package org.ayush.problemsolving.arrays;

public class SecondLargestElementInArray {
    public static void main(String[] args) {
        int arr[] = { 12, 35, 1, 10, 34, 1 };
        System.out.println(findSecondLargest(arr));
    }

    private static int findSecondLargest(int[] arr) {
        int first = arr[0], second = -1;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] > first) {
                second = first;
                first = arr[i];
            }else if(arr[i] < first && arr[i] > second) {
                second = arr[i];
            }
        }
        return second;
    }
}
