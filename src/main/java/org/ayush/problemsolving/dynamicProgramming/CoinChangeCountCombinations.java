package org.ayush.problemsolving.dynamicProgramming;

import java.util.Arrays;
import java.util.List;

/*
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
You may assume that you have an infinite number of each kind of coin.
The answer is guaranteed to fit into a signed 32-bit integer.

Example 1:
Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:
Input: amount = 10, coins = [10]
Output: 1

Constraints:
1 <= coins.length <= 300
1 <= coins[i] <= 5000
All the values of coins are unique.
0 <= amount <= 5000
* */
public class CoinChangeCountCombinations {
    public static void main(String[] args) {
        System.out.println(change(5, new int[]{1, 2, 5}));
        System.out.println(change(3, new int[]{2}));
        System.out.println(change(10, new int[]{10}));
        System.out.println();
        System.out.println(change1(5, new int[]{1, 2, 5}));
        System.out.println(change1(3, new int[]{2}));
        System.out.println(change1(10, new int[]{10}));
        System.out.println();
        System.out.println(change2(5, new int[]{1, 2, 5}));
        System.out.println(change2(3, new int[]{2}));
        System.out.println(change2(10, new int[]{10}));
    }

    // Recursion with DP
    public static int change(int amount, int[] coins) {
        int [][] dp = new int[coins.length][amount + 1];
        for(int[] row : dp) Arrays.fill(row, -1);
        return dfs(coins, amount, coins.length - 1, dp);
    }

    private static int dfs(int[] coins, int amount, int i, int [][] dp) {
        if(amount == 0) return 1;
        if(i == 0){
            if(amount % coins[0] == 0) return 1;
            return 0;
        }

        if(dp[i][amount] != -1) return dp[i][amount];

        // do not take the coin and move
        int notTake = dfs(coins, amount, i-1, dp);

        // take the same coin and move ahead
        int take = 0;
        if(amount>=coins[i]) {
            take = dfs(coins, amount - coins[i], i, dp);
        }
        return dp[i][amount] = take + notTake;
    }

    // Tabulation
    public static int change1(int amount, int[] coins) {
        int [][] dp = new int[coins.length][amount + 1];
        for(int total = 0; total <= amount; total++){
            if(total % coins[0] == 0) {
                dp[0][total] = 1;
            }
        }
        for(int i =1; i < coins.length; i++){
            for(int total = 0; total <= amount; total++){
                int notTake = dp[i-1][total];
                int take = 0;
                if(total >= coins[i]) {
                    take = dp[i][total - coins[i]];
                }
                dp[i][total] = notTake + take;
            }
        }
        return dp[coins.length - 1][amount];
    }

    // Space Optimized
    public static int change2(int amount, int[] coins) {
        int [] prev = new int[amount + 1];
        for(int total = 0; total <= amount; total++){
            if(total % coins[0] == 0) {
                prev[total] = 1;
            }
        }
        for(int i =1; i < coins.length; i++){
            for(int total = 0; total <= amount; total++){
                int notTake = prev[total];
                int take = 0;
                if(total >= coins[i]) {
                    take = prev[total - coins[i]];
                }
                prev[total] = notTake + take;
            }
        }
        return prev[amount];
    }

}
