package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;
/*
* A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
The path sum of a path is the sum of the node's values in the path.
Given the root of a binary tree, return the maximum path sum of any non-empty path.

Example 1:
Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
            1
          /   \
         2     3

Example 2:
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
*                   -10
*                 /      \
*               9         20
*                       /    \
*                      15      7
* * */
public class MaxPathSumBinaryTree {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);
        int maxPathSum[] = {Integer.MIN_VALUE};
        maxPathSum(root, maxPathSum);
        System.out.println(maxPathSum[0]);

        root = new TreeNode<>(-10);
        root.left = new TreeNode<>(9);
        root.right = new TreeNode<>(20);
        root.right.left = new TreeNode<>(15);
        root.right.right = new TreeNode<>(7);
        maxPathSum[0] = Integer.MIN_VALUE;
        maxPathSum(root, maxPathSum);
        System.out.println(maxPathSum[0]);

        root = new TreeNode<>(2);
        root.left = new TreeNode<>(-1);
        maxPathSum[0] = Integer.MIN_VALUE;
        maxPathSum(root, maxPathSum);
        System.out.println(maxPathSum[0]);
    }

    public static int maxPathSum(TreeNode<Integer> root, int[] maxSum) {
        if(root == null) return 0;
        int leftSum  = maxPathSum(root.left, maxSum);
        int rightSum = maxPathSum(root.right, maxSum);
        // Ignore the negative path sums
        if(leftSum < 0) leftSum = 0;
        if(rightSum < 0) rightSum = 0;
        maxSum[0] = Math.max(maxSum[0], leftSum + rightSum + root.data);
        return root.data + Math.max(leftSum, rightSum);
    }
}
