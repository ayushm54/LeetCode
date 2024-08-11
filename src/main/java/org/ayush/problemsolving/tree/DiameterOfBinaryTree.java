package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

/*
* Diameter is the longest path between any two nodes
* Path does not necessarily pass through root
*
* Example 1:
*                       1
*                  /         \
*                 2           3
*              /    \
*             4      5
*   Answer: 3, we start fom 4 -> 2 -> 1 -> 3
*
* Example 2:
*               1
*           /       \
*          2         3
*                 /     \
*                4       7
*              /          \
*             5            8
*           /               \
*          6                 9
*   Answer: 6, we start fom 6 -> 5 -> 4 -> 3 -> 7 -> 8 -> 9
* */
public class DiameterOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        int[] diameter = {0};
        height(root, diameter);
        System.out.println(diameter[0]);

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.left.left = new TreeNode(5);
        root.right.left.left.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);
        root.right.right.right.right = new TreeNode(9);
        diameter[0] = 0;
        height(root, diameter);
        System.out.println(diameter[0]);
    }

    private static int height(TreeNode<Integer> node, int[] diameter) {
        if(node == null) return 0;
        int leftHeight  = height(node.left, diameter);
        int rightHeight = height(node.right, diameter);
        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
