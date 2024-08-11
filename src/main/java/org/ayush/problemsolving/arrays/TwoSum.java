package org.ayush.problemsolving.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.

Example 1:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:
Input: nums = [3,2,4], target = 6
Output: [1,2]

Example 3:
Input: nums = [3,3], target = 6
Output: [0,1]

Constraints:
2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.


Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
* */
public class TwoSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum1(new int[]{2, 7, 11, 15}, 9)));
    }

    // using map
    public static int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        Map<Integer,Integer> m = new HashMap<>();
        for(int i = 0; i<nums.length; i++){
            int delta = target-nums[i];
            if(m.containsKey(delta)){
                ans[0] = m.get(delta);
                ans[1] = i;
                break;
            }else{
                m.put(nums[i], i);
            }
        }
        return ans;
    }

    // using two pointer with sorting
    public static int[] twoSum1(int[] nums, int target) {
        int[] ans = new int[2];
        int l = 0, r = nums.length - 1;
        Arrays.sort(nums);
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum == target) {
                ans[0] = l;
                ans[1] = r;
                break;
            } else if (sum < target) {
                l++;
            }else {
                r--;
            }
        }
        return ans;
    }
}
