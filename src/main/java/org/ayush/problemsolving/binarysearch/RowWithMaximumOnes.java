package org.ayush.problemsolving.binarysearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
* Given a m x n binary matrix mat, find the 0-indexed position of the row that contains the maximum count of ones, and the number of ones in that row.
In case there are multiple rows that have the maximum count of ones, the row with the smallest row number should be selected.
Return an array containing the index of the row, and the number of ones in it.

* Example 1:
Input: mat = [[0,1],[1,0]]
Output: [0,1]
Explanation: Both rows have the same number of 1's. So we return the index of the smaller row, 0, and the maximum count of ones (1). So, the answer is [0,1].

* Example 2:
Input: mat = [[0,0,0],[0,1,1]]
Output: [1,2]
Explanation: The row indexed 1 has the maximum count of ones (2). So we return its index, 1, and the count. So, the answer is [1,2].

Example 3:
Input: mat = [[0,0],[1,1],[0,0]]
Output: [1,2]
Explanation: The row indexed 1 has the maximum count of ones (2). So the answer is [1,2].

Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 100
mat[i][j] is either 0 or 1.
* */
public class RowWithMaximumOnes {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(rowAndMaximumOnes(new int[][]{{0, 1}, {1, 0}})));
        System.out.println(Arrays.toString(rowAndMaximumOnes(new int[][]{{0, 0, 0}, {0, 1, 1}})));
        System.out.println(Arrays.toString(rowAndMaximumOnes(new int[][]{{0, 0}, {1, 1}, {0, 0}})));

        System.out.println(Arrays.toString(rowAndMaximumOnesOptimized(new int[][]{{0, 1}, {1, 0}})));
        System.out.println(Arrays.toString(rowAndMaximumOnesOptimized(new int[][]{{0, 0, 0}, {0, 1, 1}})));
        System.out.println(Arrays.toString(rowAndMaximumOnesOptimized(new int[][]{{0, 0}, {1, 1}, {0, 0}})));
    }

    // BruteForce
    public static int[] rowAndMaximumOnes(int[][] mat) {
        int maxCount  = 0;
        int maxCountRow = 0;
        for (int i = 0; i < mat.length; i++) {
            int count = 0;
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) {
                    count++;
                }
            }
            if(count > maxCount) {
                maxCount = count;
                maxCountRow = i;
            }
        }
        return new int[]{maxCountRow, maxCount};
    }

    public static int[] rowAndMaximumOnesOptimized(int[][] mat) {
        int maxCount  = 0;
        int maxCountRow = -1;
        for (int i = 0; i < mat.length; i++) {
            int rowLength = mat[i].length;
            int count = rowLength - lowerBound(mat[i], rowLength, 1);
            if(count > maxCount) {
                maxCount = count;
                maxCountRow = i;
            }
        }
        return new int[]{maxCountRow, maxCount};
    }

    public static int lowerBound(int[] arr, int n, int x) {
        int low = 0, high = n - 1;
        int ans = n;
        Arrays.sort(arr);
        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] >= x) {
                ans = mid;
                // look for smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }
}
