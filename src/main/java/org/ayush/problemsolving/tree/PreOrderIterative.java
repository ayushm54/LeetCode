package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

import java.util.Stack;

/*
* Example 1:
            1
         /     \
        2       3
          \
           5
Output: 1 2 5 3  (root, left, right)
* */
public class PreOrderIterative {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.setLeft(new TreeNode<>(2));
        root.setRight(new TreeNode<>(3));
        root.getLeft().setRight(new TreeNode<>(5));
        preOrder(root);
    }

    private static void preOrder(TreeNode<Integer> root) {
        if(root == null) return;
        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode<Integer> current = stack.pop();
            System.out.println(current.getData());
            if(current.getRight() != null) stack.push(current.getRight());
            if(current.getLeft() != null) stack.push(current.getLeft());
        }
    }
}
