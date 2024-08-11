package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
Given a binary tree, find the path from root to a given node

Example, find path to node 7
            1
         /     \
        2       3
     /    \
    4      5
         /  \
        6    7
* */
public class PathToNode {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.left.right.left = new TreeNode<>(6);
        root.left.right.right = new TreeNode<>(7);
        List<Integer> path = new ArrayList<>();
        findPath(root, 7, path);
        System.out.println(path);
    }

    /*We can do inorder traversal and check for target*/
    private static boolean findPath(TreeNode<Integer> root, int target, List<Integer> path) {
        if(root == null) return false;
        path.add(root.data);
        if(root.data == target) return true;
        if(findPath(root.left, target, path) || findPath(root.right, target, path)) return true;
        path.remove(path.size() - 1); // if not found remove tha last node added
        return false;
    }
}
