package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

/*
* Example:
*            1
*        /      \
*       2        3
*              /    \
*             4      6
*            /
*           5
* Max Depth or Height = 4 (Max number of levels)
* */
public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.setLeft(new TreeNode<>(2));
        root.setRight(new TreeNode<>(3));
        root.getRight().setLeft(new TreeNode<>(4));
        root.getRight().setRight(new TreeNode<>(6));
        root.getRight().getLeft().setLeft(new TreeNode<>(5));
        System.out.println(maxDepth(root));
    }

    public static int maxDepth(TreeNode node) {
        if(node == null) return 0;
        return 1 + Math.max(maxDepth(node.getLeft()), maxDepth(node.getRight()));
    }
}
