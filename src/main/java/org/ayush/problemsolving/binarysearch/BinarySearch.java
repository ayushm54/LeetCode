package org.ayush.problemsolving.binarysearch;

public class BinarySearch {
    public static void main(String[] args) {
        System.out.println(binarySearchIterative(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, 9, 4));
        System.out.println(binarySearchIterative(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, 9, 10));
        System.out.println(binarySearchIterative(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, 9, 0));

        System.out.println(binarySearchRecursive(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, 9, 4));
        System.out.println(binarySearchRecursive(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, 9, 10));
        System.out.println(binarySearchRecursive(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, 9, 0));
    }

    private static boolean binarySearchIterative(int[] a, int low, int high, int target){
        while (low <= high){
            int mid = (low + high)/2;
            if(a[mid] == target) return true;
            if(a[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }

    private static boolean binarySearchRecursive(int[] a, int low, int high, int target){
        if(low > high) return false;
        int mid = (low + high)/2;
        if(a[mid] == target) return true;
        if(a[mid] < target) return binarySearchRecursive(a, mid+1, high, target);
        else return binarySearchRecursive(a, low, mid-1, target);
    }
}
