package org.ayush.problemsolving.dynamicProgramming;

import java.util.Arrays;

/*
A thief is robbing a store and can carry a maximal weight of W into his knapsack. There are N items and the ith item weighs wi and is of value vi.
Considering the constraints of the maximum weight that a knapsack can carry, you have to find and return the maximum value that a thief can generate by stealing items.

Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 10
1 <= N <= 10^2
1<= wi <= 50
1 <= vi <= 10^2
1 <= W <= 10^3

Time Limit: 1 second
Sample Input:
1
4
1 2 4 5
5 4 8 6
5
Sample Output:
13
* */
public class KnapSack01 {
    public static void main(String[] args) {
        System.out.println(knapsack(new int[]{1, 2, 4, 5}, new int[] {5, 4, 8, 6}, 4, 5));
        System.out.println(knapsack(new int[]{3, 2, 5}, new int[] {30, 40, 60}, 3, 6));

        System.out.println(knapsack1(new int[]{1, 2, 4, 5}, new int[] {5, 4, 8, 6}, 4, 5));
        System.out.println(knapsack1(new int[]{3, 2, 5}, new int[] {30, 40, 60}, 3, 6));

        System.out.println(knapsack2(new int[]{1, 2, 4, 5}, new int[] {5, 4, 8, 6}, 4, 5));
        System.out.println(knapsack2(new int[]{3, 2, 5}, new int[] {30, 40, 60}, 3, 6));

        System.out.println(knapsack3(new int[]{1, 2, 4, 5}, new int[] {5, 4, 8, 6}, 4, 5));
        System.out.println(knapsack3(new int[]{3, 2, 5}, new int[] {30, 40, 60}, 3, 6));
    }

    // Recursion with DP
    public static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        int[][] dp = new int[n][maxWeight + 1];
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        return dfs(n-1, maxWeight, weight, value, dp);
    }

    private static int dfs(int i, int maxWeight, int[] weight, int[] value, int[][] dp){
        if(i == 0) {
            if(weight[0] <= maxWeight) return value[0];
            return 0;
        }
        if(dp[i][maxWeight] != -1) return dp[i][maxWeight];
        int notTaken = 0 + dfs(i-1, maxWeight, weight, value, dp);
        int taken = Integer.MIN_VALUE;
        if(weight[i] <= maxWeight){
            taken = value[i] + dfs(i-1, maxWeight - weight[i], weight, value, dp);
        }
        dp[i][maxWeight] = Math.max(notTaken, taken);
        return dp[i][maxWeight];
    }

    // Tabulation
    public static int knapsack1(int[] weight, int[] value, int n, int maxWeight) {
        int[][] dp = new int[n][maxWeight + 1];
        for(int w = weight[0]; w<=maxWeight; w++){
            dp[0][w] = value[0];
        }
        for(int i = 1; i < n; i++){
            for(int w = 0; w<=maxWeight; w++){
                int notTaken = 0 + dp[i-1][w];
                int taken = Integer.MIN_VALUE;
                if(weight[i] <= w){
                    taken = value[i] + dp[i-1][w - weight[i]];
                }
                dp[i][w] = Math.max(notTaken, taken);
            }
        }
        return dp[n-1][maxWeight];
    }

    // Space Optimized with two arrays, we only need the values calculated in a prev iteration
    public static int knapsack2(int[] weight, int[] value, int n, int maxWeight) {
        int[] prev = new int[maxWeight + 1];
        int[] cur = new int[maxWeight + 1];
        for(int w = weight[0]; w<=maxWeight; w++){
            prev[w] = value[0];
        }
        for(int i = 1; i < n; i++){
            for(int w = 0; w<=maxWeight; w++){
                int notTaken = 0 + prev[w];
                int taken = Integer.MIN_VALUE;
                if(weight[i] <= w){
                    taken = value[i] + prev[w - weight[i]];
                }
                cur[w] = Math.max(notTaken, taken);
            }
            prev = cur;
        }
        return prev[maxWeight];
    }

    // Space Optimized with 1 array
    public static int knapsack3(int[] weight, int[] value, int n, int maxWeight) {
        int[] prev = new int[maxWeight + 1];
        for(int w = weight[0]; w<=maxWeight; w++){
            prev[w] = value[0];
        }
        for(int i = 1; i < n; i++){
            for(int w = maxWeight; w>=0; w--){
                int notTaken = 0 + prev[w];
                int taken = Integer.MIN_VALUE;
                if(weight[i] <= w){
                    taken = value[i] + prev[w - weight[i]];
                }
                prev[w] = Math.max(notTaken, taken);
            }
        }
        return prev[maxWeight];
    }
}
