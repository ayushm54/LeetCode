package org.ayush.problemsolving;
/*There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

You must decrease the overall operation steps as much as possible.



Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false


Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
nums is guaranteed to be rotated at some pivot.
-104 <= target <= 104
* */
public class SearchRotatedSortedArray2 {
    public static void main(String[] args) {
        System.out.println(search(new int[]{2,5,6,0,0,1,2}, 0));
        System.out.println(search(new int[]{2,5,6,0,0,1,2}, 3));
        System.out.println(search(new int[]{3, 1, 2, 3, 3, 3, 3}, 5));
        System.out.println(search(new int[]{3, 1, 2, 3, 3, 3, 3}, 2));
        System.out.println(search(new int[]{3, 1, 2, 3, 3, 3, 3}, 1));
        System.out.println(search(new int[]{3, 1, 2, 3, 3, 3, 3}, 3));

        System.out.println(search2(new int[]{2,5,6,0,0,1,2}, 0));
        System.out.println(search2(new int[]{2,5,6,0,0,1,2}, 3));
        System.out.println(search2(new int[]{3, 1, 2, 3, 3, 3, 3}, 5));
        System.out.println(search2(new int[]{3, 1, 2, 3, 3, 3, 3}, 2));
        System.out.println(search2(new int[]{3, 1, 2, 3, 3, 3, 3}, 1));
        System.out.println(search2(new int[]{3, 1, 2, 3, 3, 3, 3}, 3));
    }

    public static boolean search2(int[] nums, int target) {
        if(nums == null || nums.length == 0) return false;
        if(nums.length == 1 && nums[0] != target) return false;
        if(nums.length == 1 && nums[0] == target) return true;
        int low = 0;
        int high = nums.length - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(nums[mid] == target) return true;
            if(nums[low] == nums[mid] && nums[mid] == nums[high]) {
                // Edge case:If we have duplicate elements then we truncate the search space
                low = low + 1;
                high = high - 1;
                continue;
            }
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
        return false;
    }

    public static boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0 || (nums.length == 1 && nums[0] != target)) return false;
        if(nums.length == 1 && nums[0] == target) return true;
        int low = 0;
        int high = nums.length - 1;
        return binarySearchRecursive(nums, low, high, target);
    }

    private static boolean binarySearchRecursive(int[] a, int low, int high, int target){
        if(low > high) return false;
        int mid = low+ (high - low) / 2;
        if(a[mid] == target) return true;
        boolean rightSearchRes = binarySearchRecursive(a, mid+1, high, target);
        if(rightSearchRes) return rightSearchRes;
        return binarySearchRecursive(a, low, mid-1, target);
    }
}
