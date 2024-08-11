package org.ayush.problemsolving.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.

Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.

Example 2:
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.

Example 3:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.

Constraints:
1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104
* */
public class BestTimeToBuyAndSellStock2 {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println(maxProfit(new int[]{7,6,4,3,1}));
        System.out.println(maxProfit(new int[]{1, 2, 3, 4, 5}));

        System.out.println(maxProfit1(new int[]{7,1,5,3,6,4}));
        System.out.println(maxProfit1(new int[]{7,6,4,3,1}));
        System.out.println(maxProfit1(new int[]{1, 2, 3, 4, 5}));
    }

    // recursion with memoization
    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        Map<String, Integer> dp = new HashMap<>();
        return dfs(0, prices, 1, dp);
    }

    /*
    At every index we have two options: we can buy it, but if we have previously bought anything then we can not buy but sell.
    * */
    private static int dfs(int i, int[] prices, int canBuy, Map<String, Integer> dp) {
        // Base case
        if(i == prices.length) return 0;

        if(dp.containsKey(i+"_"+canBuy)) return dp.get(i+"_"+canBuy);

        // canBuy = true means I am allowed to buy on the current day
        int profit = 0;
        if(canBuy == 1){
            // buy
            int buyProfit = -prices[i] + dfs(i + 1, prices, 0, dp);
            // not buy
            int nonBuyProfit = dfs(i + 1, prices, 1, dp);
            profit =  Math.max(buyProfit, nonBuyProfit);
        }else{
            // sell
            int sellProfit = prices[i] + dfs(i + 1, prices, 1, dp);
            // not sell
            int nonSellProfit = dfs(i + 1, prices, 0, dp);
            profit =  Math.max(sellProfit, nonSellProfit);
        }
        dp.put(i+"_"+canBuy, profit);
        return profit;
    }

    // tabulation
    public static int maxProfit1(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        int n = prices.length;
        int dp[][] = new int[n+1][n+1];
        // Base case at i==n
        dp[n][0] = dp[n][1] = 0;
        //we will got top down
        for(int i = n-1; i >=0; i--) {
            for(int buy = 0; buy <= 1; buy++){
                if(buy == 1){
                    // buy
                    int buyProfit = -prices[i] + dp[i+1][0];
                    // not buy
                    int nonBuyProfit = dp[i+1][1];
                    dp[i][buy] =  Math.max(buyProfit, nonBuyProfit);
                }else{
                    // sell
                    int sellProfit = prices[i] + dp[i+1][1];
                    // not sell
                    int nonSellProfit = dp[i+1][0];
                    dp[i][buy] =  Math.max(sellProfit, nonSellProfit);
                }
            }
        }
        return dp[0][1];
    }

}
