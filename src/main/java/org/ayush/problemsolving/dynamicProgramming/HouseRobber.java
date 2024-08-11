package org.ayush.problemsolving.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 400
* */
public class HouseRobber {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));

        System.out.println(rob2(new int[]{1, 2, 3, 1}));
        System.out.println(rob2(new int[]{2, 7, 9, 3, 1}));

        System.out.println(rob3(new int[]{1, 2, 3, 1}));
        System.out.println(rob3(new int[]{2, 7, 9, 3, 1}));
    }

    /* DP without extra space*/
    public static int rob3(int[] nums) {
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

    /* DP with tabulation*/
    public static int rob2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // take current element, we cant add the previous one
            if(i>1) dp[i] = nums[i] + dp[i - 2];
            else dp[i] = nums[i];

            // not take
            dp[i] = Math.max(dp[i], dp[i-1]);
        }
        return dp[nums.length-1];
    }

    /*Recursion with memoization*/
    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return dfs(nums, nums.length-1, dp);
    }

    public static int dfs(int[] nums, int index, int[] dp) {
        // this means we have not picked index 1(bcoz we cant pick adjacents)
        if (index == 0) return nums[index];
        if(index < 0) return 0;
        if(dp[index] != -1) return dp[index];
        // take
        int money1 = nums[index] + dfs(nums, index - 2, dp);
        //not take, 0+ dfs on next index
        int money2 = dfs(nums, index - 1, dp);
        dp[index] = Math.max(money1, money2);
        return dp[index];
    }
}
