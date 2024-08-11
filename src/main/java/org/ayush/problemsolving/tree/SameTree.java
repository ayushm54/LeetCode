package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

/*
* Given the roots of two binary trees p and q, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Example 1:
Input: p = [1,2,3], q = [1,2,3]
Output: true

Example 2:
Input: p = [1,2], q = [1,null,2]
Output: false

Example 3:
Input: p = [1,2,1], q = [1,1,2]
Output: false
* */
public class SameTree {
    public static void main(String[] args) {
        TreeNode<Integer> p = new TreeNode<>(1);
        p.left = new TreeNode<>(2);
        p.right = new TreeNode<>(3);

        TreeNode<Integer> q = new TreeNode<>(1);
        q.left = new TreeNode<>(2);
        q.right = new TreeNode<>(3);
        System.out.println(isSameTree(p, q));

        p = new TreeNode<>(1);
        p.left = new TreeNode<>(2);

        q = new TreeNode<>(1);
        q.right = new TreeNode<>(2);
        System.out.println(isSameTree(p, q));
    }

    private static boolean isSameTree(TreeNode<Integer> p, TreeNode<Integer> q) {
        if (p == null && q == null) return true;
        if (p != null && q != null && p.data == q.data)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;
    }
}
