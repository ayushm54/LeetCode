package org.ayush.problemsolving.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

Example 2:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 3:
Input: nums = [1,2,3]
Output: 3

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 1000
* */
public class HouseRobber2 {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{2, 3, 2}));
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{1, 2, 3}));

    }
    public static int rob(int[] nums) {
        /* We cant use the fist and last elements together
        * hence we can break the array in two arrays;
        * arr1 = [0, n-2] and arr2 = [1, n-1]
        * We apply the same logic as house robber 1 on both this arrays and find max of both*/
        int[] arr1 = new int[nums.length - 1];
        int[] arr2 = new int[nums.length - 1];

        if(nums.length == 1)
            return nums[0];
        int j = 0, k = 0;
        for(int i=0; i<nums.length; i++){
            if(i != nums.length-1) {
                arr1[j] = nums[i];
                j++;
            }
            if(i != 0){
                arr2[k] = nums[i];
                k++;
            }

        }

        int ans1 = robHelper(arr1);
        int ans2 = robHelper(arr2);

        return Math.max(ans1,ans2);
    }

    public static int robHelper(int[] nums) {
        int prev1 = nums[0], prev2= -1;
        for (int i = 1; i < nums.length; i++) {
            // take current element, we cant add the previous one
            int temp = 0;
            if (i > 1) temp = nums[i] + prev2;
            else temp = nums[i];

            // not take
            temp = Math.max(temp, prev1);
            prev2 = prev1;
            prev1 = temp;
        }
        return prev1;
    }
}
