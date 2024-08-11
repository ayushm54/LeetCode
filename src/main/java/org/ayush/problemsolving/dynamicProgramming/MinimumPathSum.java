package org.ayush.problemsolving.dynamicProgramming;

/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.

Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200
* */
public class MinimumPathSum {
    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(minPathSum(new int[][]{{1,2, 3}, {4, 5, 6}}));

        System.out.println(minPathSum1(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(minPathSum1(new int[][]{{1,2, 3}, {4, 5, 6}}));

        System.out.println(minPathSum2(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(minPathSum2(new int[][]{{1,2, 3}, {4, 5, 6}}));
    }

    // Recursion + dp with memoization
    public static int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        return dfs(m-1, n-1, grid, dp);
    }

    private static int dfs(int row, int col, int[][] grid, int[][] dp){
        if(row == 0 && col == 0) return grid[row][col];
        // Since this path is not possible we return a big value so that if the other path is possible it is taken into consideration
        if(row <0 || col <0) return Integer.MAX_VALUE;
        if(dp[row][col] != 0) return dp[row][col];
        return dp[row][col] = grid[row][col] + Math.min(dfs(row, col-1, grid, dp), dfs(row-1, col, grid, dp));
    }

    // tabulation to reduce stack space of recursion
    public static int minPathSum1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) continue;
                int up = Integer.MAX_VALUE, left = Integer.MAX_VALUE;
                // Calculate the number of ways by moving up (if possible)
                if (i > 0)
                    up = dp[i - 1][j];
                // Calculate the number of ways by moving up (if possible) and left (if possible)
                if (j > 0)
                    left = dp[i][j - 1];

                // Store the total number of ways to reach the current cell in the DP array
                dp[i][j] = Math.min(up, left) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    // space optimized
    public static int minPathSum2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] prev = new int[n];
        for(int i = 0; i < m; i++){
            int[] temp = new int[n];
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) {
                    temp[j] = grid[i][j];
                    continue;
                }
                int up = Integer.MAX_VALUE, left = Integer.MAX_VALUE;
                // Calculate the number of ways by moving up (if possible)
                if (i > 0)
                    up = prev[j];
                // Calculate the number of ways by moving up (if possible) and left (if possible)
                if (j > 0)
                    left = temp[j - 1];

                // Store the total number of ways to reach the current cell in the DP array
                temp[j] = Math.min(up, left) + grid[i][j];
            }
            prev = temp;
        }
        return prev[n-1];
    }
}
