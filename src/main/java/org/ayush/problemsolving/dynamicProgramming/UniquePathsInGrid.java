package org.ayush.problemsolving.dynamicProgramming;
/*
You are present at point ‘A’ which is the top-left cell of an M X N matrix, your destination is point ‘B’, which is the bottom-right cell of the same matrix.
Your task is to find the total number of unique paths from point ‘A’ to point ‘B’.
In other words, you will be given the dimensions of the matrix as integers ‘M’ and ‘N’, your task is to find the total number of
unique paths from the cell MATRIX[0][0] to MATRIX['M' - 1]['N' - 1].
To traverse in the matrix, you can either move Right or Down at each step. For example in a given point MATRIX[i] [j], you can move to either MATRIX[i + 1][j] or MATRIX[i][j + 1].

Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 ≤ T ≤ 100
1 ≤ M ≤ 15
1 ≤ N ≤ 15

Where ‘M’ is the number of rows and ‘N’ is the number of columns in the matrix.

Time limit: 1 sec
Sample Input 1:
2
2 2
1 1
Sample Output 1:
2
1
Explanation of Sample Output 1:
In test case 1, we are given a 2 x 2 matrix, to move from matrix[0][0] to matrix[1][1] we have the following possible paths.

Path 1 = (0, 0) -> (0, 1) -> (1, 1)
Path 2 = (0, 0) -> (1, 0) -> (1, 1)

Hence a total of 2 paths are available, so the output is 2.

In test case 2, we are given a 1 x 1 matrix, hence we just have a single cell which is both the starting and ending point. Hence the output is 1.
Sample Input 2:
2
3 2
1 6
Sample Output 2:
3
1
Explanation of Sample Output 2:
In test case 1, we are given a 3 x 2 matrix, to move from matrix[0][0] to matrix[2][1] we have the following possible paths.

Path 1 = (0, 0) -> (0, 1) -> (1, 1) -> (2, 1)
Path 2 = (0, 0) -> (1, 0) -> (2, 0) -> (2, 1)
Path 3 =  (0, 0) -> (1, 0) -> (1, 1) -> (2, 1)

Hence a total of 3 paths are available, so the output is 3.

In test case 2, we are given a 1 x 6 matrix, hence we just have a single row to traverse and thus total path will be 1.
* */
public class UniquePathsInGrid {

    // we need to move from (0,0) tp (m-1, n-1)
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 3));
        System.out.println(uniquePaths(2, 2));
        System.out.println(uniquePaths(1, 1));

        System.out.println(uniquePaths1(3, 3));
        System.out.println(uniquePaths1(2, 2));
        System.out.println(uniquePaths1(1, 1));

        System.out.println(uniquePaths2(3, 3));
        System.out.println(uniquePaths2(2, 2));
        System.out.println(uniquePaths2(1, 1));
    }

    // Recursion with dp
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        return dfs(m-1, n-1, dp);
    }

    private static int dfs(int row, int col, int[][] dp){
        if(row == 0 && col == 0) return 1;
        if(row <0 || col <0) return 0;
        if(dp[row][col] != 0) return dp[row][col];
        return dp[row][col] = dfs(row, col-1, dp) + dfs(row-1, col, dp);
    }

    // DP with tabulation
    public static int uniquePaths1(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Base condition: If we are at the top-left cell (0, 0), there's one way to reach it.
                if (i == 0 && j == 0) continue;

                int up = 0;
                int left = 0;
                // Calculate the number of ways by moving up (if possible)
                if (i > 0)
                    up = dp[i - 1][j];
                // Calculate the number of ways by moving up (if possible) and left (if possible)
                if (j > 0)
                    left = dp[i][j - 1];

                // Store the total number of ways to reach the current cell in the DP array
                dp[i][j] = up + left;
            }
        }

        // Return the number of ways to reach the bottom-right cell (m-1, n-1)
        return dp[m - 1][n - 1];
    }

    // Space optimized, we only need a previous row to calculate the next row
    public static int uniquePaths2(int m, int n) {
        int[] prev = new int[n];
        for (int i = 0; i < m; i++) {
            int[] temp = new int[n];
            for (int j = 0; j < n; j++) {
                // Base condition: If we are at the top-left cell (0, 0), there's one way to reach it.
                if (i == 0 && j == 0) {
                    temp[j] = 1;
                    continue;
                }

                int up = 0;
                int left = 0;
                // Calculate the number of ways by moving up (if possible)
                if (i > 0)
                    up = prev[j];
                // Calculate the number of ways by moving up (if possible) and left (if possible)
                if (j > 0)
                    left = temp[j - 1];

                // Store the total number of ways to reach the current cell in the DP array
                temp[j] = up + left;
            }
            prev = temp;
        }

        // Return the number of ways to reach the bottom-right cell (m-1, n-1)
        return prev[n - 1];
    }
}
