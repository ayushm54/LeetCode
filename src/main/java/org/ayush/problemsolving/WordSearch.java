package org.ayush.problemsolving;

import java.util.HashSet;
import java.util.Set;

/*
* Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
A B C E
S F C S
A D E E
Output: true
*
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
*
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
* */
public class WordSearch {
    public static void main(String[] args) {
        System.out.println(exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCCED"));
        System.out.println(exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "SEE"));
        System.out.println(exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCB"));
    }
    public static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(exist(board, i, j, 0, word, rows, cols, visited)){ // we need to do search from aall the positions on the board
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean exist(char[][] board, int row, int col, int idx, String word, int rows, int cols,boolean[][] visited) {
        if(idx == word.length()) return true;
        if(row<0 || col <0
                || row>=rows || col>=cols
                || board[row][col] != word.charAt(idx)
                || visited[row][col]) return false; // We cannot use already visited position

        // If char is found in grid
        visited[row][col] = true;
        boolean ans = exist(board, row+1, col, idx+1, word, rows, cols, visited)
        || exist(board, row-1, col, idx+1, word, rows, cols, visited)
        || exist(board, row, col+1, idx+1, word, rows, cols, visited)
        || exist(board, row, col-1, idx+1, word, rows, cols, visited);

        // remove the current position from the visited to backtrack
        visited[row][col] = false;
        return ans;
    }
}
