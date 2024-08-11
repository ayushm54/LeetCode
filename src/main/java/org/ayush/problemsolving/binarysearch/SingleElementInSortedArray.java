package org.ayush.problemsolving.binarysearch;
/*
* You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

Example 1:
Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
*
Example 2:
Input: nums = [3,3,7,7,10,11,11]
Output: 10
Observations:
* duplicate elements are together
* arr = [3, 3, 7, 7, 10, 11, 11]
  idx = [0, 1, 2, 3, 4,  5,  6]
  on the left half of the single element, the duplicate elements follow below index pattern:
  * elements: (3, 3)  (7, 7)
  * indexes:  (0, 1)  (2, 3)  (even, odd) (even, odd)
  on the right half of the single element, the duplicate elements follow below index pattern:
  * elements: (11, 11)
  * indexes:  (5, 6) (odd, even)
* And the single element will have different elements near it on both halves


Constraints:
1 <= nums.length <= 105
0 <= nums[i] <= 105
* */
public class SingleElementInSortedArray {
    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}));
        System.out.println(singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
        System.out.println(singleNonDuplicate(new int[]{7,7,10,11,11,12,12}));
    }

    public static int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        if(nums[0] != nums[1]) return nums[0];
        if(nums[n-1] != nums[n-2]) return nums[n-1];
        // to Avoid additional conditions for 1st and last index we are handling them above and triming the search space by 1 on both sides
        int low = 1, high = n-2;
        int ans = nums[low];
        while(low <= high) {
            int mid = low+ (high - low) / 2;
            if(nums[mid] != nums[mid+1] && nums[mid] != nums[mid-1]) {
                ans = nums[mid];
                break;
            }
            // eliminate the half where element is not present
            boolean isMidEven = mid % 2 == 0;
            if((!isMidEven && nums[mid] == nums[mid-1]) || (isMidEven && nums[mid] == nums[mid+1])) {
                // this means this is the left half where element is not present
                low = mid + 1;
            }else{
                // right half needs to be eliminated
                high = mid - 1;
            }
        }
        return ans;
    }
}
