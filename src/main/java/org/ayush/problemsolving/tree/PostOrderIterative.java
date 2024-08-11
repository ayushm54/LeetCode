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
Output: 5 2 3 1  (left, right, root)
* */
public class PostOrderIterative {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.setLeft(new TreeNode<>(2));
        root.setRight(new TreeNode<>(3));
        root.getLeft().setRight(new TreeNode<>(5));
        postOrder(root);
    }

    private static void postOrder(TreeNode<Integer> root) {
        if(root == null) return;
        Stack<TreeNode<Integer>> stack1 = new Stack<>();
        Stack<TreeNode<Integer>> stack2 = new Stack<>();
        stack1.push(root);
        while(!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if(node.getLeft() != null) stack1.push(node.getLeft());
            if(node.getRight() != null) stack1.push(node.getRight());
        }
        while(!stack2.isEmpty()) {
            System.out.print(stack2.pop().getData() + " ");
        }
    }
}
