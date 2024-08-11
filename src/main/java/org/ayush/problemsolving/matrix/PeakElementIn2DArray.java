package org.ayush.problemsolving.matrix;

import java.util.Arrays;

/*
* A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.
Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i,j].
You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.
You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.

Example 1:
Input: mat = [[1,4],[3,2]]
Output: [0,1]
Explanation: Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.

* Example 2:
Input: mat = [[10,20,15],[21,30,14],[7,16,32]]
Output: [1,1]
Explanation: Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.

Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 500
1 <= mat[i][j] <= 105
No two adjacent cells are equal.
* */
public class PeakElementIn2DArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findPeakGrid(new int[][]{{1, 4}, {3, 2}})));
        System.out.println(Arrays.toString(findPeakGrid(new int[][]{{10, 20,15}, {21, 30, 14}, {7, 16, 32}})));
    }

    /*
    * We can do a binary search on columns, and find the max element of the column
    * bcoz the max element has the highest probability of being a peak
    * We check for the peak conditions for this max element, if it satisfies we got an answer
    * if it dosent then we have to eliminate the search space and repeate this process
    * We can also do binary search on rows
    * */
    public static int[] findPeakGrid(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int low = 0, high = m-1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int maxElementRowIdx = getMaxElementRowIdx(mat, mid, n);
            int left = mid - 1 >= 0 ? mat[maxElementRowIdx][mid-1] : -1;
            int right = mid + 1 <= m-1 ? mat[maxElementRowIdx][mid+1] : -1;
            if(mat[maxElementRowIdx][mid] > left && mat[maxElementRowIdx][mid] > right) {
                return new int[]{maxElementRowIdx, mid};
            } else if (mat[maxElementRowIdx][mid] < left) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

    public static int getMaxElementRowIdx(int[][] mat, int mid, int n){
        int max = -1;
        int rowIdx = -1;
        for(int i=0; i<n; i++){
            if(mat[i][mid] > max){
                max = mat[i][mid];
                rowIdx = i;
            }
        }
        return rowIdx;
    }
}
