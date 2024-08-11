package org.ayush.problemsolving.binarysearch;
/*
* There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1


Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-104 <= target <= 104

* */
public class SearchRotatedSortedArray {
    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 3));
        System.out.println(search(new int[]{1}, 0));

        System.out.println(search2(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(search2(new int[]{4,5,6,7,0,1,2}, 3));
        System.out.println(search2(new int[]{1}, 0));
        System.out.println(search2(new int[]{3,1}, 1));
    }
    public static int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        if(nums.length == 1 && nums[0] != target) return -1;
        if(nums.length == 1 && nums[0] == target) return 0;
        int low = 0;
        int high = nums.length - 1;
        return binarySearchRecursive(nums, low, high, target);
    }

    public static int search2(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        if(nums.length == 1 && nums[0] != target) return -1;
        if(nums.length == 1 && nums[0] == target) return 0;
        int low = 0;
        int high = nums.length - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(nums[mid] == target) return mid;
            /* Now we check which half is sorted and wheter that half contains the target
            * based on this we will reduce the search space*/
            if(nums[low] <= nums[mid]) { // this means left half is sorted
                if(target <= nums[mid] && target >= nums[low]) { // checking if target lies in left half
                    high = mid - 1;
                }else {
                    low = mid + 1;
                }
            }else { // means right half is sorted
                if(target >= nums[mid] && target <= nums[high]) { // checking if target lies in right half
                    low = mid + 1;
                }else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    private static int binarySearchRecursive(int[] a, int low, int high, int target){
        if(low > high) return -1;
        int mid = low+ (high - low) / 2;
        if(a[mid] == target) return mid;
        int rightSearchRes = binarySearchRecursive(a, mid+1, high, target);
        if(rightSearchRes!=-1) return rightSearchRes;
        return binarySearchRecursive(a, low, mid-1, target);
    }
}
