package org.ayush.problemsolving;
/*
* The right view of a Binary Tree is a set of nodes visible when the tree is visited from the Right side.
*
* Examples:

Input:
           1
       /       \
     2          3
   /   \       /  \
  4     5   6      7
                    \
                     8
Output: Right view of the tree is 1 3 7 8

Input:
          1
       /
     8
   /
  7
Output: Right view of the tree is 1 8 7
* */

import org.ayush.datastructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class RightViewOfBinaryTree {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode(1);
        root.setLeft(new TreeNode(2));
        root.setRight(new TreeNode(3));
        root.getLeft().setLeft(new TreeNode(4));
        root.getLeft().setRight(new TreeNode(5));
        root.getRight().setLeft(new TreeNode(6));
        root.getRight().setRight(new TreeNode(7));
        root.getRight().getRight().setRight(new TreeNode(8));
        printRightView(root);
        System.out.println();
        root = new TreeNode(1);
        root.setLeft(new TreeNode(8));
        root.getLeft().setLeft(new TreeNode(7));
        printRightView(root);
    }

    private static void printRightView(TreeNode root) {
        if(root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) {
            int n = q.size();
            // traverse all the nodes of the current level
            for(int i = 0; i < n; i++) {
                TreeNode node = q.peek();
                q.remove();

                if(i == n-1){
                    // Print last element of each level
                    System.out.print(node.getData() + " ");
                }

                if(node.getLeft() != null){
                    q.add(node.getLeft());
                }
                if(node.getRight() != null){
                    q.add(node.getRight());
                }
            }
        }
    }
}
