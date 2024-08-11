package org.ayush.problemsolving.matrix;
/*
* You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

Example 1:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Example 2:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104
* */
public class Search2DMatrix {
    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3));
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 13));
        System.out.println(searchMatrix(new int[][]{{1}}, 2));

        System.out.println(searchMatrixBinarySearch(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3));
        System.out.println(searchMatrixBinarySearch(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 13));
        System.out.println(searchMatrixBinarySearch(new int[][]{{1}}, 2));

        System.out.println(searchMatrixBinarySearchOptimized(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3));
        System.out.println(searchMatrixBinarySearchOptimized(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 13));
        System.out.println(searchMatrixBinarySearchOptimized(new int[][]{{1}}, 2));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length-1;

        while (row<matrix.length && col>=0) {
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

    public static boolean searchMatrixBinarySearch(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] <= target && matrix[i][matrix[i].length-1] >= target) {
                int low = 0, right = matrix[i].length-1;
                while (low <= right) {
                    int mid = (low+right)/2;
                    if (matrix[i][mid] == target) {
                        return true;
                    }else if (matrix[i][mid] > target) {
                        right = mid-1;
                    }else{
                        low = mid+1;
                    }
                }
            }
        }
        return false;
    }

    /*
    * The idea is to logically flatten the matrix into a 1D array and then do a binary search
    * */
    public static boolean searchMatrixBinarySearchOptimized(int[][] matrix, int target) {
        // e.g. matrix is 3x3, the total elements will be 9 and high will be 9-1 = 8
        int low = 0, right = matrix.length * matrix[0].length - 1;
        while (low <= right) {
            int mid = low + (right - low)/2;
            int rowIdx = mid / matrix[0].length; // e.g mid = 5 in flattened array,and length of row = 4, then rowIdx = 5/4 = 1
            int colIdx = mid % matrix[0].length; // e.g mid = 5 in flattened array,and length of row = 4, then colIdx = 5%4 = 1
            if (matrix[rowIdx][colIdx] == target) {
                return true;
            }else if (matrix[rowIdx][colIdx] > target) {
                right = mid-1;
            }else{
                low = mid+1;
            }
        }
        return false;
    }
}
