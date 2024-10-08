package org.ayush.problemsolving.binarysearch;

/*
* Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.

Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.

Example 3:
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times.


Constraints:

n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
All the integers of nums are unique.
nums is sorted and rotated between 1 and n times.
* */
public class FindMinInRotatedSortedArray {
    public static void main(String[] args) {
        System.out.println(findMin(new int[]{4,5,6,7,0,1,2}));
    }

    public static int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int min = Integer.MAX_VALUE;
        while(low <= high) {
            int mid = low + (high - low) / 2;
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
