package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
According to the definition of LCA on Wikipedia:
“The lowest common ancestor is defined between two nodes p and q as the lowest node in Tree that has both p and q as descendants
(where we allow a node to be a descendant of itself).”

Example 1:
                3
             /      \
            5        1
          /   \    /   \
         6     2  0     8
             /   \
            7     4
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
                3
             /      \
            5        1
          /   \    /   \
         6     2  0     8
             /   \
            7     4
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

Example 3:
            1
           /
          2
Input: root = [1,2], p = 1, q = 2
Output: 1

Constraints:
The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q will exist in the tree.
* */
public class LCA {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(5);
        root.left.left = new TreeNode<>(6);
        root.left.right = new TreeNode<>(2);
        root.left.right.left = new TreeNode<>(7);
        root.left.right.right = new TreeNode<>(4);
        root.right = new TreeNode<>(1);
        root.right.left = new TreeNode<>(0);
        root.right.right = new TreeNode<>(8);
        System.out.println(lowestCommonAncestor(root, new TreeNode<>(5), new TreeNode<>(1)).data);
        System.out.println(lowestCommonAncestor(root, new TreeNode<>(5), new TreeNode<>(4)).data);

        System.out.println(lowestCommonAncestorOptimized(root, new TreeNode<>(5), new TreeNode<>(1)).data);
        System.out.println(lowestCommonAncestorOptimized(root, new TreeNode<>(5), new TreeNode<>(4)).data);
    }

    public static TreeNode<Integer> lowestCommonAncestorOptimized(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        if (root == null || root.data == p.data || root.data == q.data) {
            return root;
        }
        TreeNode<Integer> left = lowestCommonAncestorOptimized(root.left, p, q);
        TreeNode<Integer> right = lowestCommonAncestorOptimized(root.right, p, q);
        if(left==null) return right;
        else if(right==null) return left;
        else return root;
    }

    public static TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        List<TreeNode<Integer>> pathP = new ArrayList<>();
        List<TreeNode<Integer>> pathQ = new ArrayList<>();
        findPath(root, p.data, pathP);
        findPath(root, q.data, pathQ);
        TreeNode<Integer> result = null;
        if (pathP.size() <= pathQ.size()) {
            for (int i = 0; i < pathP.size(); i++) {
                if (pathP.get(i).data == pathQ.get(i).data) {
                    result = pathP.get(i);
                }else {
                    break;
                }
            }
        }else {
            for (int i = 0; i < pathQ.size(); i++) {
                if (pathQ.get(i).data == pathP.get(i).data) {
                    result = pathQ.get(i);
                }else {
                    break;
                }
            }
        }
        return result;
    }

    private static boolean findPath(TreeNode<Integer> root, int target, List<TreeNode<Integer>> path) {
        if(root == null) return false;
        path.add(root);
        if(root.data == target) return true;
        if(findPath(root.left, target, path) || findPath(root.right, target, path)) return true;
        path.remove(path.size() - 1); // if not found remove tha last node added
        return false;
    }
}
