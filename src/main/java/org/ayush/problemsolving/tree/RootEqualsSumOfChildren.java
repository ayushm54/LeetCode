package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

/*
You are given the root of a binary tree that consists of exactly 3 nodes: the root, its left child, and its right child.
Return true if the value of the root is equal to the sum of the values of its two children, or false otherwise.

Example 1:
Input: root = [10,4,6]
            10
          /    \
         4      6
Output: true
Explanation: The values of the root, its left child, and its right child are 10, 4, and 6, respectively.
10 is equal to 4 + 6, so we return true.

Example 2:
Input: root = [5,3,1]
                5
              /   \
             3     1
Output: false
Explanation: The values of the root, its left child, and its right child are 5, 3, and 1, respectively.
5 is not equal to 3 + 1, so we return false.


Constraints:
The tree consists only of the root, its left child, and its right child.
-100 <= Node.val <= 100
* */
public class RootEqualsSumOfChildren {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);
        System.out.println(checkTree(root));

        root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);
        System.out.println(checkTree(root));
    }

    public static boolean checkTree(TreeNode<Integer> root) {
        if(root == null) return false;
        if(root.left != null && root.right != null && root.data == root.left.data+root.right.data) return true;
        return checkTree(root.left) && checkTree(root.right);
    }
}

