package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

/*
* A binary tree is called as balanced, if for every node the difference between height of left subtree and right subtree is not more than 1

* Example: Below is balanced
*               3
*           /       \
*          9        20
*                 /    \
*                15     7
*
* * Example: Below is not balanced
 *               1
 *           /       \
 *          3        2
 *       /    \
 *     5      4
 *   /   \
 *  7     6
* */
public class CheckBalancedBinaryTree {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.setLeft(new TreeNode<>(9));
        root.setRight(new TreeNode<>(20));
        root.getRight().setLeft(new TreeNode<>(15));
        root.getRight().setRight(new TreeNode<>(7));
        System.out.println(height(root) == -1 ? "not balanced" : "balanced");

        root = new TreeNode<>(1);
        root.setLeft(new TreeNode<>(3));
        root.setRight(new TreeNode<>(2));
        root.getLeft().setLeft(new TreeNode<>(5));
        root.getLeft().setRight(new TreeNode<>(4));
        root.getLeft().getLeft().setLeft(new TreeNode<>(7));
        root.getLeft().getLeft().setRight(new TreeNode<>(6));
        System.out.println(height(root) == -1 ? "not balanced" : "balanced");
    }

    public static int height(TreeNode node) {
        if(node == null) return 0;
        int lHeight = height(node.getLeft());
        int rHeight = height(node.getRight());
        if(lHeight == -1 || rHeight == -1) return -1;
        if(Math.abs(lHeight - rHeight) > 1) return -1;
        return 1 + Math.max(lHeight, rHeight);
    }
}
