package org.ayush.problemsolving.dynamicProgramming;

import java.util.Arrays;

/*
Given a rod of length ‘N’ units. The rod can be cut into different sizes and each size has a cost associated with it.
Determine the maximum cost obtained by cutting the rod and selling its pieces.

Note:
1. The sizes will range from 1 to ‘N’ and will be integers.

2. The sum of the pieces cut should be equal to ‘N’.

3. Consider 1-based indexing.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 50
1 <= N <= 100
1 <= A[i] <= 100

Where ‘T’ is the total number of test cases, ‘N’ denotes the length of the rod, and A[i] is the cost of sub-length.

Time limit: 1 sec.
Sample Input 1:
2
5
2 5 7 8 10
8
3 5 8 9 10 17 17 20
Sample Output 1:
12
24
Explanation of sample input 1:
Test case 1:

All possible partitions are:
1,1,1,1,1           max_cost=(2+2+2+2+2)=10
1,1,1,2             max_cost=(2+2+2+5)=11
1,1,3               max_cost=(2+2+7)=11
1,4                 max_cost=(2+8)=10
5                   max_cost=(10)=10
2,3                 max_cost=(5+7)=12
1,2,2               max _cost=(1+5+5)=12

Clearly, if we cut the rod into lengths 1,2,2, or 2,3, we get the maximum cost which is 12.


Test case 2:

Possible partitions are:
1,1,1,1,1,1,1,1         max_cost=(3+3+3+3+3+3+3+3)=24
1,1,1,1,1,1,2           max_cost=(3+3+3+3+3+3+5)=23
1,1,1,1,2,2             max_cost=(3+3+3+3+5+5)=22
and so on….

If we cut the rod into 8 pieces of length 1, for each piece 3 adds up to the cost. Hence for 8 pieces, we get 8*3 = 24.
Sample Input 2:
1
6
3 5 6 7 10 12
Sample Output 2:
18
* */
public class RodCutting {
    public static void main(String[] args) {
        System.out.println(cutRod(new int[]{2, 5, 7, 8, 10}, 5));
        System.out.println(cutRod(new int[]{3, 5, 8, 9, 10, 17, 17, 20}, 8));

        System.out.println(cutRod1(new int[]{2, 5, 7, 8, 10}, 5));
        System.out.println(cutRod1(new int[]{3, 5, 8, 9, 10, 17, 17, 20}, 8));
    }

    /*
        Here the indexes of the prices relates tot the rod length e.g
            prices:    [2, 5, 7, 8, 10]
            indexes:    0  1  2  3  4
            rodLength:  1  2  3  4  5
        Now we just need to pick the lengths and sum them up to make given N and then maximize, similar to knapsack
    * */
    public static int cutRod(int price[], int n) {
        int [][] dp = new int[price.length][n+1];
        return dfs(price.length - 1, price, n, dp);
    }

    private static int dfs(int i, int[] prices, int rodLength, int [][] dp){
        if(i==0){
            return rodLength*prices[0];
        }
        if(dp[i][rodLength]!=0) return dp[i][rodLength];
        int notTake = 0 +dfs(i-1, prices, rodLength, dp);
        int take = (int) Math.pow(-10, 9);
        if(i+1 <= rodLength){
            take = prices[i] + dfs(i, prices, rodLength-(i+1), dp);
        }
        return dp[i][rodLength] = Math.max(notTake, take);
    }

    // Tabulation
    public static int cutRod1(int price[], int n) {
        int [][] dp = new int[price.length][n+1];
        for(int i=0; i<=n; i++) dp[0][i] = i*price[0];
        for(int i=1; i<n; i++){
            for(int rodLength = 0; rodLength <= n; rodLength++){
                int notTake = dp[i-1][rodLength];
                int take = (int) Math.pow(-10, 9);
                if(i+1 <= rodLength){
                    take = price[i] + dp[i][rodLength-(i+1)];
                }
                dp[i][rodLength] = Math.max(notTake, take);
            }
        }
        return dp[price.length-1][n];
    }
}
