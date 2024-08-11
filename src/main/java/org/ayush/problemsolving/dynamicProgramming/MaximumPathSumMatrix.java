package org.ayush.problemsolving.dynamicProgramming;
/*
You have been given an N*M matrix filled with integer numbers, find the maximum sum that can be obtained from a path starting from any cell in the first row to any cell in the last row.
From a cell in a row, you can move to another cell directly below that row, or diagonally below left or right. So from a particular cell (row, col), we can move in three directions i.e.
Down: (row+1,col)
Down left diagonal: (row+1,col-1)
Down right diagonal: (row+1, col+1)
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
1 <= T <= 50
1 <= N <= 100
1 <= M <= 100
-10^4 <= matrix[i][j] <= 10^4

Where 'T' is the number of test cases.
Where 'N' is the number of rows in the given matrix, and 'M' is the number of columns in the given matrix.
And, matrix[i][j] denotes the value at (i,j) cell in the matrix.

Time Limit: 1sec
Input 1 :
2
4 4
1 2 10 4
100 3 2 1
1 1 20 2
1 2 2 1
3 3
10 2 3
3 7 2
8 1 5
Output 1 :
105
25
Explanation Of Input 1 :
In the first test case for the given matrix,

  1    2    10    4
  100  3    2     1
  1    1    20    2
  1    2    2     1

The maximum path sum will be 2->100->1->2, So the sum is 105(2+100+1+2).

In the second test case for the given matrix, the maximum path sum will be 10->7->8, So the sum is 25(10+7+8).
Input 2 :
2
3 3
1 2 3
9 8 7
4 5 6
4 6
10 10 2 -13 20 4
1 -9 -81 30 2 5
0 10 4 -79 2 -10
1 -5 2 20 -11 4
Output 2 :
17
74
Explanation Of Input 2 :
In the first test case for the given matrix, the maximum path sum will be 3->8->6, So the sum is 17(3+8+6).

In the second test case for the given matrix, the maximum path sum will be 20->30->4->20, So the sum is 74(20+30+4+20).
* */
public class MaximumPathSumMatrix {
    public static void main(String[] args) {
        System.out.println(getMaxPathSum(new int[][]{{1, 2, 10, 4}, {100, 3, 2, 1}, {1, 1, 20, 2}, {1, 2 , 2, 1}}));
        System.out.println(getMaxPathSum(new int[][]{{10, 2, 3}, {3, 7, 2}, {8, 1, 5}}));

        System.out.println(getMaxPathSum1(new int[][]{{1, 2, 10, 4}, {100, 3, 2, 1}, {1, 1, 20, 2}, {1, 2 , 2, 1}}));
        System.out.println(getMaxPathSum1(new int[][]{{10, 2, 3}, {3, 7, 2}, {8, 1, 5}}));

        System.out.println(getMaxPathSum2(new int[][]{{1, 2, 10, 4}, {100, 3, 2, 1}, {1, 1, 20, 2}, {1, 2 , 2, 1}}));
        System.out.println(getMaxPathSum2(new int[][]{{10, 2, 3}, {3, 7, 2}, {8, 1, 5}}));
    }

    // Recursion with dp, we start from last row and look for a path from all the elements from last row to the first row
    public static int getMaxPathSum(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;
        int[][] dp = new int[m][n];
        for(int j = n-1; j >=0; j--){
            // We will start one by one from each element in the last row and try to reach every element in the 1st row
            maxSum = Math.max(maxSum, dfs(matrix, m-1, j, n, dp));
        }
        return maxSum;
    }

    private static int dfs(int[][] matrix, int row, int col, int columns, int[][] dp) {
        if(col < 0 || col >= columns) return (int) Math.pow(-10, 9);
        if(row == 0) return matrix[row][col];
        if(dp[row][col] != 0) return dp[row][col];
        int up = matrix[row][col] + dfs(matrix, row-1, col, columns, dp);
        int leftDiagonal = matrix[row][col] + dfs(matrix, row-1, col-1, columns, dp);
        int rightDiagonal = matrix[row][col] + dfs(matrix, row-1, col+1, columns, dp);
        return dp[row][col] = Math.max(up, Math.max(leftDiagonal, rightDiagonal));
    }

    // Tabulation, recusrsion stack space eliminated
    public static int getMaxPathSum1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        // First initialize the base condition values, i.e the first row of the dp array to the first row of the input matrix.
        for(int j = 0; j<n; j++) dp[0][j] = matrix[0][j];

        // We want to move from the first row to the last row.Whenever we compute values for a cell, we want to have all the values required to calculate it.
        for(int i=1; i<m; i++) {
            for(int j = 0; j < n; j++){
                int up = matrix[i][j] + dp[i-1][j];
                int leftDiagonal = matrix[i][j];
                if (j - 1 >= 0) {
                    leftDiagonal += dp[i - 1][j - 1];
                } else {
                    leftDiagonal += (int) Math.pow(-10, 9);
                }

                int rightDiagonal = matrix[i][j];
                if (j + 1 < n) {
                    rightDiagonal += dp[i - 1][j + 1];
                } else {
                    rightDiagonal += (int) Math.pow(-10, 9);
                }
                dp[i][j] = Math.max(up, Math.max(leftDiagonal, rightDiagonal));
            }
        }
        int maxSum = Integer.MIN_VALUE;
        // ans will be the max value in the last row of dp
        for (int j = 0; j < n; j++) {
            maxSum = Math.max(maxSum, dp[m - 1][j]);
        }
        return maxSum;
    }

    // Space optimisation
    public static int getMaxPathSum2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] prev = new int[n];
        // First initialize the base condition values, i.e the first row of the dp array to the first row of the input matrix.
        for(int j = 0; j<n; j++) prev[j] = matrix[0][j];

        // We want to move from the first row to the last row.Whenever we compute values for a cell, we want to have all the values required to calculate it.
        for(int i=1; i<m; i++) {
            int[] temp = new int[n];
            for(int j = 0; j < n; j++){
                int up = matrix[i][j] + prev[j];
                int leftDiagonal = matrix[i][j];
                if (j - 1 >= 0) {
                    leftDiagonal += prev[j - 1];
                } else {
                    leftDiagonal += (int) Math.pow(-10, 9);
                }

                int rightDiagonal = matrix[i][j];
                if (j + 1 < n) {
                    rightDiagonal += prev[j + 1];
                } else {
                    rightDiagonal += (int) Math.pow(-10, 9);
                }
                temp[j] = Math.max(up, Math.max(leftDiagonal, rightDiagonal));
            }
            prev = temp;
        }
        int maxSum = Integer.MIN_VALUE;
        // ans will be the max value in the last row of dp
        for (int j = 0; j < n; j++) {
            maxSum = Math.max(maxSum, prev[j]);
        }
        return maxSum;
    }
}
