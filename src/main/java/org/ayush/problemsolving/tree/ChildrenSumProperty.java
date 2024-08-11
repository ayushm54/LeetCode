package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
Problem statement
Given a binary tree of nodes 'N', you need to modify the value of its nodes, such that the tree holds the Children sum property.
A binary tree is said to follow the children sum property if, for every node of that tree, the value of that node is equal to
the sum of the value(s) of all of its children nodes( left child and the right child).

Note :
 1. You can only increment the value of the nodes, in other words, the modified value must be at least equal to the original value of that node.
 2. You can not change the structure of the original binary tree.
 3. A binary tree is a tree in which each node has at most two children.
 4. You can assume the value can be 0 for a NULL node and there can also be an empty tree.
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
1 <= T <= 10^2
0 <= N <= 10^2
1 <= node.Value <= 10^6

Time Limit : 1 sec
Sample Input 1 :
1
2 35 10 2 3 5 2 -1 -1 -1 -1 -1 -1 -1 -1
Sample Output 1 :
Valid ( One of the possible answers is : 45 35 10 32 3 8 2 -1 -1 -1 -1 -1, thus if the user modifies the given tree like this, the output printed will be valid).
Explanation For Sample Input 1 :
The tree can be represented as follows :
            2
         /     \
       35       10
     /    \    /   \
    2      3  5     2

The value at the root node is 2 which is less than the sum of its children’s values (35 + 10). So we simply increase the value at the root node to 45.
The node with value  = 35 has children with values 2 and 3 so their sum i.s 2 + 3 = 5 < 35.
As we can't decrement any value, so instead we have to increase the sum of children and thus we update 35's children (2 and 3) as 30 and 5 so that 30 + 5 = 35
and the same is done for the children of the node with value = 10.
The final tree looks like this :
            45
         /     \
       35       10
     /    \    /   \
    30     5  8     2

Note that there can be many other valid solutions.
Sample Input 2 :
1
10 5 5 -1 -1 -1 -1
Sample Output 2 :
Valid
* */
public class ChildrenSumProperty {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(35);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(2);
        levelOrder(root);
        changeTree(root);
        System.out.println();
        levelOrder(root);
    }

    public static void changeTree(TreeNode<Integer> root) {
        if(root == null) return;
        int child = 0;
        if(root.left != null) child += root.left.data;
        if(root.right != null) child += root.right.data;
        if(child >= root.data) {
            root.data = child;
        }else {
            if(root.left != null) root.left.data = root.data;
            if(root.right != null) root.right.data = root.data;
        }
        changeTree(root.left);
        changeTree(root.right);
        int total = 0;
        if(root.left != null) total += root.left.data;
        if(root.right != null) total += root.right.data;
        if(root.left != null || root.right != null) root.data = total;
    }

    private static void levelOrder(TreeNode<Integer> root) {
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<Integer> current = queue.poll();
            System.out.print(current.getData() + " ");
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
    }
}
