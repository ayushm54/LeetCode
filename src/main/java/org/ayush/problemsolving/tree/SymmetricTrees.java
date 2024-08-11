package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

/*
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

Example 1:
            1
         /     \
        2      2
      /  \   /   \
     3    4 4     3
Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:
        1
      /   \
     2     2
      \     \
       3     3
Input: root = [1,2,2,null,3,null,3]
Output: false

Constraints:
The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100
* */
public class SymmetricTrees {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.left.left = new TreeNode<>(3);
        root.left.right = new TreeNode<>(4);
        root.right = new TreeNode<>(2);
        root.right.left = new TreeNode<>(4);
        root.right.right = new TreeNode<>(3);
        System.out.println(isSymmetric(root));

        root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.left.right = new TreeNode<>(3);
        root.right = new TreeNode<>(2);
        root.right.right = new TreeNode<>(3);
        System.out.println(isSymmetric(root));
    }
    public static boolean isSymmetric(TreeNode<Integer> root) {
        if(root == null) return false;
        return  isSymmetricRecursive(root.left, root.right);
    }

    private static boolean isSymmetricRecursive(TreeNode<Integer> root1, TreeNode<Integer> root2) {
        if(root1 == null && root2 == null) return true;
        if((root1 == null && root2 != null) || (root1 != null && root2 == null)) return false;
        if(root1.data != root2.data) return false;
        return isSymmetricRecursive(root1.left, root2.right) && isSymmetricRecursive(root1.right, root2.left);
    }
}
