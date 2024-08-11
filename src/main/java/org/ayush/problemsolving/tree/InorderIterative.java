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
Output: 2 5 1 3   (left, root, right)
* */
public class InorderIterative {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.setLeft(new TreeNode<>(2));
        root.setRight(new TreeNode<>(3));
        root.getLeft().setRight(new TreeNode<>(5));
        inOrder(root);
    }

    private static void inOrder(TreeNode<Integer> root) {
        if(root == null)return;
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> node = root;
        while (true){
            if(node != null){
                stack.push(node);
                node = node.getLeft();
            }else{
                if(stack.isEmpty()){
                    break;
                }
                node = stack.pop();
                System.out.println(node.getData());
                node = node.getRight();
            }
        }
    }
}
