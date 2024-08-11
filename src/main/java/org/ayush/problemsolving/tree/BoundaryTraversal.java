package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
* Problem statement
You are given a binary tree having 'n' nodes.
The boundary nodes of a binary tree include the nodes from the left and right boundaries and the leaf nodes, each node considered once.
Figure out the boundary nodes of this binary tree in an Anti-Clockwise direction starting from the root node.

Example :
Input: Consider the binary tree A as shown in the figure:
*                   10
*               /        \
*              5          20
*            /   \      /    \
*           3     8    18     25
*               /
*              7
Output: [10, 5, 3, 7, 18, 25, 20]
The nodes on the left boundary are [10, 5, 3]
The nodes on the right boundary are [10, 20, 25]
The leaf nodes are [3, 7, 18, 25].
Please note that nodes 3 and 25 appear in two places but are considered once.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
10 5 20 3 8 18 25 -1 -1 7 -1 -1 -1 -1 -1 -1 -1
Sample Output 1:
10 5 3 7 18 25 20
Explanation of Sample Input 1:
The nodes on the left boundary are [10, 5, 3]
The nodes on the right boundary are [10, 20, 25]
The leaf nodes are [3, 7, 18, 25].
Please note that nodes 3 and 25 appear in two places but are considered once.

Sample Input 2:
100 50 150 25 75 140 200 -1 30 70 80 -1 -1 -1 -1 -1 35 -1 -1 -1 -1 -1 -1
Sample Output 2:
100 50 25 30 35 70 80 140 200 150
Constraints:
1 <= n <= 10000
Where 'n' is the total number of nodes in the binary tree.
Time Limit: 1 sec
* */

public class BoundaryTraversal {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(10);
        root.left = new TreeNode<>(5);
        root.left.left = new TreeNode<>(3);
        root.left.right = new TreeNode<>(8);
        root.left.right.left = new TreeNode<>(7);
        root.right = new TreeNode<>(20);
        root.right.left = new TreeNode<>(18);
        root.right.right = new TreeNode<>(25);
        System.out.println(traverseBoundary(root));
    }

    /*
    * We will follow below:
    * 1. Take left boundary excluding leaves
    * 2. Take the leaves
    * 3. Take right boundary excluding leaves in reverse direction
    * */
    public static List<Integer> traverseBoundary(TreeNode<Integer> root){
        List<Integer> result = new LinkedList<>();
        if(root == null) return result;
        if(!isLeaf(root)) result.add(root.data);
        addLeftBoundary(root, result);
        addLeaves(root, result);
        addRightBoundary(root, result);
        return result;
    }

    private static void addRightBoundary(TreeNode<Integer> root, List<Integer> result) {
        TreeNode<Integer> current = root.right;
        List<Integer> temp = new ArrayList<>();
        while(current != null){
            if(!isLeaf(current)) temp.add(current.data);
            if(current.right != null) current = current.right;
            else current = current.left;
        }
        for(int i = temp.size() - 1; i >= 0; i--){
            result.add(temp.get(i));
        }
    }

    private static void addLeaves(TreeNode<Integer> root, List<Integer> result) {
        if(isLeaf(root)) {
            result.add(root.data);
            return;
        }
        if(root.left != null) {
            addLeaves(root.left, result);
        }
        if(root.right != null) {
            addLeaves(root.right, result);
        }

    }

    private static void addLeftBoundary(TreeNode<Integer> root, List<Integer> result) {
        TreeNode<Integer> current = root.left;
        while(current != null){
            if(!isLeaf(current)) result.add(current.data);
            if(current.left != null) current = current.left;
            else current = current.right;
        }
    }

    private static boolean isLeaf(TreeNode<Integer> current) {
        return current.left == null && current.right == null;
    }
}
