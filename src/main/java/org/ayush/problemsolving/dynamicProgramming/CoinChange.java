package org.ayush.problemsolving.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.

Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

Example 3:
Input: coins = [1], amount = 0
Output: 0

Constraints:
1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104

* */
public class CoinChange {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange(new int[]{2}, 3));
        System.out.println(coinChange(new int[]{1}, 0));
        System.out.println(coinChange(new int[]{2, 5, 10, 1}, 27));
        System.out.println(coinChange(new int[]{1, 2}, 2));
        System.out.println();
        System.out.println(coinChange1(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange1(new int[]{2}, 3));
        System.out.println(coinChange1(new int[]{1}, 0));
        System.out.println(coinChange1(new int[]{2, 5, 10, 1}, 27));
        System.out.println(coinChange1(new int[]{1, 2}, 2));
    }

    // Recursion without DP
    public static int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;
        int [][] dp = new int[coins.length][amount + 1];
        for(int[] row : dp) Arrays.fill(row, -1);
        int ans = dfs(coins, amount, coins.length-1, dp);
        if(ans >= Math.pow(10, 9)) return -1;
        return ans;
    }

    private static int dfs(int[] coins, int amount, int i, int [][] dp) {
        if(i == 0){
            // if the amount is multiple of coin at index 0 we can directly add the quotient of their division value
            if(amount % coins[0] == 0) {
                return amount / coins[0];
            }
            return (int)Math.pow(10, 9);
        }

        if(dp[i][amount] != -1) return dp[i][amount];

        // do not take the coin and move
        int notTake = 0 + dfs(coins, amount, i-1, dp);

        // take the same coin and move ahead
        int take = (int)Math.pow(10, 9);
        if(amount >= coins[i]) {
            take = 1 + dfs(coins, amount - coins[i], i, dp);
        }

        return dp[i][amount] = Math.min(notTake, take);
    }

    // tabulation
    public static int coinChange1(int[] coins, int amount) {
        if(amount == 0) return 0;
        int [][] dp = new int[coins.length][amount + 1];
        for(int total = 0; total <= amount; total++){
            if(total % coins[0] == 0) {
                dp[0][total] = total/coins[0];
            }else {
                dp[0][total] = (int) Math.pow(10, 9);
            }
        }
        for(int i =1; i < coins.length; i++){
            for(int total = 0; total <= amount; total++){
                int notTake = 0 + dp[i-1][total];
                int take = (int)Math.pow(10, 9);
                if(total >= coins[i]) {
                    take = 1 + dp[i][total - coins[i]];
                }
                dp[i][total] = Math.min(notTake, take);
            }
        }
        if(dp[coins.length-1][amount] >= Math.pow(10, 9)) return -1;
        return dp[coins.length - 1][amount];
    }
}