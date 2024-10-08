package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/*
* Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively.
The root of the tree is at (0, 0).
The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column
and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
Return the vertical order traversal of the binary tree.

Example 1:
Input: root = [3,9,20,null,null,15,7]
*                     (0,0)
*                       3
*                  /         \
*               (1,-1)      (1,1)
*                 9           20
*                          /       \
                         (2,0)     (2,2)
*                         15        7
Output: [[9],[3,15],[20],[7]]
Explanation:
Column -1: Only node 9 is in this column.
Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
Column 1: Only node 20 is in this column.
Column 2: Only node 7 is in this column.

Example 2:
Input: root = [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
Column -2: Only node 4 is in this column.
Column -1: Only node 2 is in this column.
Column 0: Nodes 1, 5, and 6 are in this column.
          1 is at the top, so it comes first.
          5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
Column 1: Only node 3 is in this column.
Column 2: Only node 7 is in this column.

Example 3:
Input: root = [1,2,3,4,6,5,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
This case is the exact same as example 2, but with nodes 5 and 6 swapped.
Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.

Constraints:
The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 1000
* */
public class VerticalOrderTraversal {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(9);
        root.right = new TreeNode<>(20);
        root.right.left = new TreeNode<>(15);
        root.right.right = new TreeNode<>(7);
        System.out.println(verticalTraversal(root));
    }
    public static List<List<Integer>> verticalTraversal(TreeNode<Integer> root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Queue<VerticalLevelData> queue = new LinkedList<>();
        Map<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        queue.add(new VerticalLevelData(root, 0, 0));
        while(!queue.isEmpty()) {
            VerticalLevelData verticalLevelData = queue.poll();
            TreeNode<Integer> node = verticalLevelData.node;
            int level = verticalLevelData.level;
            int vertical = verticalLevelData.vertical;
            if(!map.containsKey(vertical)) {
                map.put(vertical, new TreeMap<>());
            }
            if(!map.get(vertical).containsKey(level)) {
                map.get(vertical).put(level, new PriorityQueue<>());
            }
            map.get(vertical).get(level).add(node.data);
            if(node.left != null) {
                queue.add(new VerticalLevelData(node.left, level + 1, vertical-1));
            }
            if(node.right != null) {
                queue.add(new VerticalLevelData(node.right, level + 1, vertical+1));
            }
        }
        for(TreeMap<Integer, PriorityQueue<Integer>> values : map.values()) {
            result.add(new ArrayList<>());
            for(PriorityQueue<Integer> pq : values.values()) {
                while (!pq.isEmpty()) {
                    result.get(result.size() - 1).add(pq.poll());
                }
            }
        }
        return result;
    }
}

class VerticalLevelData {
    TreeNode<Integer> node;
    int level;
    int vertical;

    public VerticalLevelData(TreeNode<Integer> node, int level, int vertical) {
        this.node = node;
        this.level = level;
        this.vertical = vertical;
    }
}
