package org.ayush.problemsolving.matrix;


import java.util.LinkedList;
import java.util.Queue;

/*
* Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
* */
public class NumberOfIslands {
    public static void main(String[] args) {
        System.out.println(numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        }));
        System.out.println(numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        }));

        System.out.println(numIslands(new char[][]{
                {'0','0','1','0','0','0','0','1','0','0','0','0','0'},
                {'0','0','0','0','0','0','0','1','1','1','0','0','0'},
                {'0','1','1','0','1','0','0','0','0','0','0','0','0'},
                {'0','1','0','0','1','1','0','0','1','0','1','0','0'},
                {'0','1','0','0','1','1','0','0','1','1','1','0','0'},
                {'0','0','0','0','0','0','0','0','0','0','1','0','0'},
                {'0','0','0','0','0','0','0','1','1','1','0','0','0'},
                {'0','0','0','0','0','0','0','1','1','0','0','0','0'}}));
    }

    /*
    * We will start at a '1' and do bfs on it to mark all the neighbouring '1' as visited
    * The number of times we enter the bfs from the main loop, we increase the count
    * since once we have started from a '1' all its neighbours will be visited by bfs
    * */
    public static int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(!visited[i][j] && grid[i][j] == '1') {
                    //bfs(grid, i, j, visited);
                    //bfs1(grid, i, j, visited);
                    dfs(grid, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int row, int col, boolean[][] visited) {
        int rows = grid.length, cols = grid[0].length;
        if(row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == '0' || visited[row][col]) return;
        visited[row][col] = true;
        dfs(grid, row - 1, col, visited);
        dfs(grid, row + 1, col, visited);
        dfs(grid, row, col - 1, visited);
        dfs(grid, row, col + 1, visited);
    }

    // BFS to consider only 4 direction, up, down , left and right
    private static void bfs1(char[][] grid, int row, int col, boolean[][] visited) {
        visited[row][col] = true;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(row, col));
        int n = grid.length, m = grid[0].length;
        while (!queue.isEmpty()) {
            int r = queue.peek().row;
            int c = queue.peek().col;
            queue.remove();
            // traverse the neighbours and mark them visited
            if(r-1 >=0 && grid[r-1][c] == '1' && !visited[r-1][c]){
                visited[r-1][c] = true;
                queue.add(new Pair(r-1, c));
            }
            if(r+1 < n && grid[r+1][c] == '1' && !visited[r+1][c]){
                visited[r+1][c] = true;
                queue.add(new Pair(r+1, c));
            }
            if(c-1 >= 0 && grid[r][c-1] == '1' && !visited[r][c-1]){
                visited[r][c-1] = true;
                queue.add(new Pair(r, c-1));
            }
            if(c+1 < m && grid[r][c+1] == '1' && !visited[r][c+1]){
                visited[r][c+1] = true;
                queue.add(new Pair(r, c+1));
            }
        }
    }

    // BFS to consider all 8 directions
    private static void bfs(char[][] grid, int row, int col, boolean[][] visited) {
        visited[row][col] = true;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(row, col));
        int n = grid.length, m = grid[0].length;
        while (!queue.isEmpty()) {
            int r = queue.peek().row;
            int c = queue.peek().col;
            queue.remove();
            // traverse the neighbours and mark them visited
            // now, the neighbors of the current row, col, will be in the range row+1, row-1, col+1 and col-1 in all the 8 directions
            // this means the delta iis from -1 to +1
            for(int deltaRow = -1; deltaRow <= 1; deltaRow++) {
                for(int deltaCol = -1; deltaCol <= 1; deltaCol++) {
                    int neighBourRow = r + deltaRow, neighBourCol = c + deltaCol;
                    if(neighBourRow >=0 && neighBourRow < n && neighBourCol >=0 && neighBourCol < m
                    && grid[neighBourRow][neighBourCol] == '1' && !visited[neighBourRow][neighBourCol]){
                        visited[neighBourRow][neighBourCol] = true;
                        queue.add(new Pair(neighBourRow, neighBourCol));
                    }
                }
            }
        }
    }
}

class Pair {
    int row;
    int col;
    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
