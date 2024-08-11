package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
* Given the root of a binary tree, return all root-to-leaf paths in any order.
A leaf is a node with no children.

Example 1:
            1
         /     \
        2       3
          \
           5
Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]

Example 2:
Input: root = [1]
Output: ["1"]

Constraints:
The number of nodes in the tree is in the range [1, 100].
-100 <= Node.val <= 100
* */
public class BinaryTreePaths {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.setLeft(new TreeNode<>(2));
        root.setRight(new TreeNode<>(3));
        root.getLeft().setRight(new TreeNode<>(5));
        System.out.println(binaryTreePaths(root));

        root = new TreeNode<>(1);
        System.out.println(binaryTreePaths(root));
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        findPaths(root, paths, "");
        return paths;
    }

    private static String findPaths(TreeNode node, List<String> paths, String path) {
        if(node == null) return "";
        if(node.getLeft() != null || node.getRight() != null) {
            path += node.getData() + "->";
        }
        if(node.getLeft() == null && node.getRight() == null){
            path += node.getData();
            paths.add(path);
            return path.substring(0, path.length() - 1);
        }
        if(node.getLeft() != null) {
            findPaths(node.getLeft(), paths, path);
        }
        if(node.getRight() != null) {
            findPaths(node.getRight(), paths, path);
        }
        return path;
    }
}
