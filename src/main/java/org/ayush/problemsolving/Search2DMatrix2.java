package org.ayush.problemsolving;
/*
* Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.

Example 1:
Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
Output: true

Example 2:
Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
Output: false

Constraints:
m == matrix.length
n == matrix[i].length
1 <= n, m <= 300
-109 <= matrix[i][j] <= 109
All the integers in each row are sorted in ascending order.
All the integers in each column are sorted in ascending order.
-109 <= target <= 109
* */
public class Search2DMatrix2 {
    public static void main(String[] args) {
        // System.out.println(searchMatrix(new int[][]{{1,4,7,11,15},  {2,5,8,12,19}, {3,6,9,16,22}, {10,13,14,17,24}, {18,21,23,26,30}}, 5));
        // System.out.println(searchMatrix(new int[][]{{1,4,7,11,15},  {2,5,8,12,19}, {3,6,9,16,22}, {10,13,14,17,24}, {18,21,23,26,30}}, 20));

        // System.out.println(searchMatrix2(new int[][]{{1,4,7,11,15},  {2,5,8,12,19}, {3,6,9,16,22}, {10,13,14,17,24}, {18,21,23,26,30}}, 5));
        // System.out.println(searchMatrix2(new int[][]{{1,4,7,11,15},  {2,5,8,12,19}, {3,6,9,16,22}, {10,13,14,17,24}, {18,21,23,26,30}}, 20));
        // System.out.println(searchMatrix2(new int[][]{{-5}}, -5));
        // System.out.println(searchMatrix2(new int[][]{{1, 1}}, 1));
        System.out.println(searchMatrix2(new int[][]{{-1, 3}}, 3));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length-1;

        while (row< matrix.length && col>=0) {
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] > target) {
                col-=1;
            }else {
                row+=1;
            }
        }
        return false;
    }

    public static boolean searchMatrix2(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            if(binarySearch(matrix[i], 0, matrix[i].length-1, target)) {
                return true;
            }
        }
        return false;
    }

    private static boolean binarySearch(int[] row, int l, int r, int target){
        if(row.length == 1 && row[0] == target){
            return true;
        }
        if(l==r && row[l]==target){ return true;}
        if(l<r){
            int m = l+(r-l)/2;
            if(row[m]==target){
                return true;
            }
            if(binarySearch(row,l,m,target)){
                return true;
            }
            if(binarySearch(row,m+1,r,target)){
                return true;
            }
        }
        return false;
    }
}
