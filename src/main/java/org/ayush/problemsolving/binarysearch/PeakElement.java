package org.ayush.problemsolving.binarysearch;
/*
* A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time.



Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.


Constraints:

1 <= nums.length <= 1000
-231 <= nums[i] <= 231 - 1
nums[i] != nums[i + 1] for all valid i.
* */
public class PeakElement {
    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1,2,3,1}));
        System.out.println(findPeakElement(new int[]{1,2,1,3,5,6,4}));
        System.out.println(findPeakElement(new int[]{2, 1}));
        System.out.println(findPeakElement(new int[]{1, 2}));
        System.out.println(findPeakElement(new int[]{1,2,3,4}));
        System.out.println(findPeakElement(new int[]{1,5,1,2,1}));
    }

    private static int findPeakElement(int[] arr) {
        if(arr.length == 1) return 0;
        int low = 0, high = arr.length - 1;
        if(arr[low] > arr[low+1]) return low; // to handle cases where the array is strictly decreasing
        if(arr[high] > arr[high-1]) return high; // to handle cases were tha array is strictly increasing
        // we will trim the search space becaußse we have explicit checks for 1st and last element
        low = low + 1;
        high = high - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if(arr[mid] > arr[mid - 1]) {
                // since mid was not the peak and the left of mid is smaller, this means peak will be on the right
                low = mid + 1;
            }else {
                // peak will be on the left
                high = mid - 1;
            }
        }
        return -1;
    }
}
