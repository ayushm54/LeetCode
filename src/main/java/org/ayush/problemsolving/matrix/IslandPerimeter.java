package org.ayush.problemsolving.matrix;

import java.util.LinkedList;
import java.util.Queue;

/*
* You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water,
and there is exactly one island (i.e., one or more connected land cells).
The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island.
One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example 1:
Input: grid = [
                [0,1,0,0],
                [1,1,1,0],
                [0,1,0,0],
                [1,1,0,0]
             ]
Output: 16

Example 2:
Input: grid = [[1]]
Output: 4

Example 3:
Input: grid = [[1,0]]
Output: 4

Constraints:
row == grid.length
col == grid[i].length
1 <= row, col <= 100
grid[i][j] is 0 or 1.
There is exactly one island in grid.
* */
public class IslandPerimeter {
    public static void main(String[] args) {
        System.out.println(islandPerimeter(new int[][]{
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        }));
    }

    /*
    * Since there is only one island, we do not need to do bfs or dfs
    * At every iteration, if we encounter a 1, we add 4 to perimeter, because one square has a perimeter of 4, as explained below
    *             __
    *            | |   -> total 4 edges so perimeter is 4
    *            --
    * Then we check if this square is having only one square(land) near it,we subtract 2 from perimeter, becuase both teh squares will share one edge each, as explained below
    *                   --  --
    *                  | | | |   -> here they share 2 edges, hence total perimeter = 4 + 4 - 2 (for the overlaping edges
    *                  --  --
    * Finally, if there are three squares sharing edges, we subtract 4 becuase each pair of square will share 2 edges
    *       --  --  --
    *      | | | | | |  -> perimeter = 4 + 4 + 4 - 4
    *      --  --  --
    * */
    public static int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    perimeter += 4;
                    if(i > 0 && grid[i-1][j] == 1) perimeter -= 2;
                    if(j > 0 && grid[i][j-1] == 1) perimeter -= 2;
                }
            }
        }
        return perimeter;
    }
}

