package org.ayush.problemsolving;
/*
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:

[4,5,6,7,0,1,4] if it was rotated 4 times.
[0,1,4,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.

You must decrease the overall operation steps as much as possible.



Example 1:

Input: nums = [1,3,5]
Output: 1
Example 2:

Input: nums = [2,2,2,0,1]
Output: 0


Constraints:

n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
nums is sorted and rotated between 1 and n times.
* */
public class FindMinInRotatedSortedArray2 {
    public static void main(String[] args) {
        System.out.println(findMin(new int[]{1,3,5}));
        System.out.println(findMin(new int[]{2, 2, 2, 0, 1}));
        System.out.println(findMin(new int[]{1}));
        System.out.println(findMin(new int[]{1,1}));
    }
    public static int findMin(int[] nums) {
        if(nums.length == 1) return nums[0];
        int low = 0;
        int high = nums.length - 1;
        int min = Integer.MAX_VALUE;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(nums[low] == nums[mid] && nums[mid] == nums[high]) {
                // Edge case:If we have duplicate elements then we truncate the search space
                min = Math.min(nums[low] , min);
                low = low + 1;
                high = high - 1;
                continue;
            }
            /* Now we check which half is sorted and get the min element from this sorted half
             * based on this we will reduce the search space*/
            if(nums[low] <= nums[mid] && nums[mid] <= nums[high]) {
                // Meaning both the halfs are sorted
                min = Math.min(min, nums[low]);
                break;
            }else if(nums[low] <= nums[mid]) { // this means left half is sorted
                min = Math.min(min, nums[low]);
                low = mid + 1;
            }else { // means right half is sorted
                min = Math.min(min, nums[mid]);
                high = mid - 1;
            }
        }
        return min;
    }
}
