package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
* Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
* (i.e., from left to right, then right to left for the next level and alternate between).

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
*               3
*            /     \
*           9       20
*                 /    \
*                15     7

Example 2:
Input: root = [1]
Output: [[1]]

Example 3:
Input: root = []
Output: []

Constraints:
The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
* */
public class ZigZagTraversal {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(9);
        root.right = new TreeNode<>(20);
        root.right.left = new TreeNode<>(15);
        root.right.right = new TreeNode<>(7);
        System.out.println(zigzagLevelOrder(root));
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode<Integer> root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Integer[] entry = new Integer[levelSize];
            for (int i = 0; i < levelSize; i++) {
                TreeNode<Integer> current = queue.poll();
                int idx = leftToRight ? i : levelSize - i - 1;
                entry[idx] = current.data;
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            leftToRight = !leftToRight;
            ans.add(Arrays.asList(entry));
        }
        return ans;
    }
}
