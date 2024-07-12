package org.ayush.problemsolving;

import java.util.Arrays;

/*
* Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]


Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
* */
public class FirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        System.out.println(Arrays.toString(searchRange(new int[]{}, 0)));
    }
    public static int[] searchRange(int[] a, int x) {
        int n = a.length;
        int first = findFirstOccurrence(a, x, n);
        if(first == -1) return new int[]{-1, -1};
        return new int[]{first, findLastOccurrence(a, x, n)};
    }

    private static int findFirstOccurrence(int[] arr, int target, int n) {
        int first = -1;
        int low = 0, high = n-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(arr[mid] == target){
                first = mid;
                high = mid -1;
            }else if(arr[mid] < target){
                low = mid +1 ;
            }else {
                high = mid -1;
            }
        }
        return first;
    }

    private static int findLastOccurrence(int[] arr, int target, int n) {
        int last = -1;
        int low = 0, high = n-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(arr[mid] == target){
                last = mid;
                low = mid +1;
            }else if(arr[mid] < target){
                low = mid +1 ;
            }else {
                high = mid -1;
            }
        }
        return last;
    }
}
