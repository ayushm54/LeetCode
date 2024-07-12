package org.ayush.problemsolving;

/*
* Problem statement
You have been given a Binary Tree of 'N' nodes where the nodes have integer values and three integers 'N1', 'N2', and 'N3'. Find the LCA(Lowest Common Ancestor) of the three nodes represented by the given three('N1', 'N2', 'N3') integer values in the Binary Tree.

For example:

For the given binary tree: the LCA of (7,8,10) is 1
Note:
All of the node values of the binary tree will be unique.

N1, N2, and N3  will always exist in the binary tree.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 10
1 <= N <= 10^5
0 <= node data <= 10^9
0 <= N1 <= 10^9
0 <= N2 <= 10^9
0 <= N3 <= 10^9

Time Limit: 1sec
Sample Input 1:
1
8 9 11
1 2 3 4 5 6 7 8 9 -1 -1 -1 -1 -1 -1 10 -1 11 -1 -1 -1 -1 -1
Sample Output 1:
4
Sample Input 2:
2
7 8 2
1 2 3 4 5 6 7 8 9 -1 -1 -1 -1 -1 -1 10 -1 11 -1 -1 -1 -1 -1
5 6 7
1 2 3 4 5 6 7 8 9 -1 -1 -1 -1 -1 -1 10 -1 11 -1 -1 -1 -1 -1
Sample Output 2:
1
1
Explanation to Sample Input 2:
For both inputs, the binary tree will be represented as

For the first test case, the LCA of 7,8,2 in the given tree is 2

For the second test case. the LCA of 5,6,7 in the given tree is 1,


Hints:
1. Think of finding LCA from the paths to all three nodes.
* */

import java.util.ArrayList;
import java.util.List;

public class LCAOf3Node {
    public static void main(String[] args) {
        /*
        *               1
        *           /       \
        *          2         3
        *       /          /   \
        *      4          5     6
        *    /   \            /   \
        *   7     8          9     10
        * */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
        root.left = new BinaryTreeNode<Integer>(2);
        root.right = new BinaryTreeNode<Integer>(3);
        root.left.left = new BinaryTreeNode<Integer>(4);
        root.left.left.left = new BinaryTreeNode<Integer>(7);
        root.left.left.right = new BinaryTreeNode<Integer>(8);
        root.right.left = new BinaryTreeNode<Integer>(5);
        root.right.right = new BinaryTreeNode<Integer>(6);
        root.right.right.left = new BinaryTreeNode<Integer>(9);
        root.right.right.right = new BinaryTreeNode<Integer>(10);

        System.out.println(lcaOfThreeNodes(root, 5, 6, 7));

    }

    public static BinaryTreeNode<Integer> lcaOfThreeNodes(BinaryTreeNode<Integer> root, int n1, int n2,
                                                          int n3) {
        // Write your code here.
        List<BinaryTreeNode<Integer>> pathN1 = new ArrayList<>();
        List<BinaryTreeNode<Integer>> pathN2 = new ArrayList<>();
        List<BinaryTreeNode<Integer>> pathN3 = new ArrayList<>();

        // Find paths from root to n1 and root to n2 and root to n3. If either
        // n1, n2 or n3 is not present, return -1
        if (!findPath(root, pathN1, n1)
                || !findPath(root, pathN2, n2)
                || !findPath(root, pathN3, n3))
            return null;

        int i = 0;
        while(i<pathN1.size() && i<pathN2.size() && i<pathN3.size()){
            if(pathN1.get(i).data != pathN2.get(i).data || pathN1.get(i).data != pathN3.get(i).data)
                break;
            i++;
        }
        return pathN1.get(i-1);
    }

    private static boolean findPath(BinaryTreeNode<Integer> root, List<BinaryTreeNode<Integer>> path, int val){
        if(root == null) return false;

        path.add(root);

        if(root.data == val) return true;

        if((root.left!=null && findPath(root.left, path, val)) || (root.right!=null && findPath(root.right, path, val))){
            return true;
        }

        // if not found the val
        path.remove(path.size() - 1);
        return false;
    }
}

class BinaryTreeNode<T> {
    T data;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;

    public BinaryTreeNode(T data) {
        this.data = data;
    }
}