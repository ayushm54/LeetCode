package org.ayush.problemsolving.dynamicProgramming;

import java.util.ArrayList;

/*
You are given an array/list ‘ARR’ of ‘N’ positive integers and an integer ‘K’. Your task is to check if there exists a subset in ‘ARR’ with a sum equal to ‘K’.
Note: Return true if there exists a subset with sum equal to ‘K’. Otherwise, return false.

For Example :
If ‘ARR’ is {1,2,3,4} and ‘K’ = 4, then there exists 2 subsets with sum = 4. These are {1,3} and {4}. Hence, return true.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 5
1 <= N <= 10^3
0 <= ARR[i] <= 10^9
0 <= K <= 10^3

Time Limit: 1 sec
Sample Input 1:
2
4 5
4 3 2 1
5 4
2 5 1 6 7
Sample Output 1:
true
false
Explanation For Sample Input 1:
In example 1, ‘ARR’ is {4,3,2,1} and ‘K’ = 5. There exist 2 subsets with sum = 5. These are {4,1} and {3,2}. Hence, return true.
In example 2, ‘ARR’ is {2,5,1,6,7} and ‘K’ = 4. There are no subsets with sum = 4. Hence, return false.
Sample Input 2:
2
4 4
6 1 2 1
5 6
1 7 2 9 10
Sample Output 2:
true
false
Explanation For Sample Input 2:
In example 1, ‘ARR’ is {6,1,2,1} and ‘K’ = 4. There exist 1 subset with sum = 4. That is {1,2,1}. Hence, return true.
In example 2, ‘ARR’ is {1,7,2,9,10} and ‘K’ = 6. There are no subsets with sum = 6. Hence, return false.


Hints:
1. Can you find every possible subset of ‘ARR’ and check if its sum is equal to ‘K’?
2. Can you use dynamic programming and use the previously calculated result to calculate the new result?
3. Try to use a recursive approach followed by memoization by including both index and sum we can form.
* */
public class SubsetSumEqualToK {

    public static void main(String[] args) {
        System.out.println(subsetSumToK(4, 4, new int[]{1, 2, 3, 4}));
        System.out.println(subsetSumToK(4, 4, new int[]{6,1,2,1}));
        System.out.println(subsetSumToK(5, 6, new int[]{1,7,2,9,10}));
        System.out.println(subsetSumToK(4, 4, new int[]{1,1,1,1}));

        System.out.println(subsetSumToK1(4, 4, new int[]{1, 2, 3, 4}));
        System.out.println(subsetSumToK1(4, 4, new int[]{6,1,2,1}));
        System.out.println(subsetSumToK1(5, 6, new int[]{1,7,2,9,10}));
        System.out.println(subsetSumToK1(4, 4, new int[]{1,1,1,1}));

        System.out.println(subsetSumToK2(4, 4, new int[]{1, 2, 3, 4}));
        System.out.println(subsetSumToK2(4, 4, new int[]{6,1,2,1}));
        System.out.println(subsetSumToK2(5, 6, new int[]{1,7,2,9,10}));
        System.out.println(subsetSumToK2(4, 4, new int[]{1,1,1,1}));
    }

    // Recursion with DP
    public static boolean subsetSumToK(int n, int k, int arr[]){
        int[][] dp = new int[n][k+1];
        return dfs(arr.length-1, k, arr, dp);
    }

    private static boolean dfs(int i, int target, int[] arr, int[][] dp) {
        // Base case 1
        if(target == 0) return true;
        // Base case 2
        if(i == 0) return arr[0] == target;
        if(dp[i][target] != 0) return true;
        boolean notTake = dfs(i-1, target, arr, dp);
        boolean take = false;
        if(target >= arr[i] && !notTake){
           take = dfs(i-1, target-arr[i], arr, dp);
        }
        dp[i][target] = notTake || take ? 1 : 0;
        return notTake || take;
    }

    // Tabulation
    public static boolean subsetSumToK1(int n, int k, int arr[]){
        boolean[][] dp = new boolean[n][k+1];
        // Base case 1
        for(int i = 0; i<n; i++){
            dp[i][0] = true; // target can become 0 for any index
        }
        // Base case 2
        if(arr[0] <= k) {
            dp[0][arr[0]] = true; // at index 0 the only possible case is when target is equal to arr[0]
        }
        for(int i = 1; i<n; i++){
            for(int target = 1; target<=k; target++){
                boolean notTake = dp[i-1][target];
                boolean take = false;
                if(target >= arr[i] && !notTake){
                    take = dp[i-1][target-arr[i]];
                }
                dp[i][target] = notTake || take;
            }
        }
        return dp[n-1][k];
    }

    // Space optimization
    public static boolean subsetSumToK2(int n, int k, int arr[]){
        boolean[] prev = new boolean[k+1];
        boolean[] cur = new boolean[k+1];
        // Base case 1, when target is 0
        prev[0] = true;
        cur[0] = true;
        // Base case 2
        if(arr[0] <= k) {
            prev[arr[0]] = true; // at index 0 the only possible case is when target is equal to arr[0]
        }
        for(int i = 1; i<n; i++){
            boolean[] curr = new boolean[k+1];
            for(int target = 1; target<=k; target++){
                boolean notTake = prev[target];
                boolean take = false;
                if(target >= arr[i] && !notTake){
                    take = prev[target-arr[i]];
                }
                curr[target] = notTake || take;
            }
            prev = curr;
        }
        return prev[k];
    }
}
