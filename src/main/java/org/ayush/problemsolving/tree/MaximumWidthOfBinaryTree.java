package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/*
Given the root of a binary tree, return the maximum width of the given tree.
The maximum width of a tree is the maximum width among all levels.
The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.
It is guaranteed that the answer will in the range of a 32-bit signed integer.

Example 1:
Input: root = [1,3,2,5,3,null,9]
            1
         /     \
        3       2
      /   \       \
     5     3        9
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).

Example 2:
Input: root = [1,3,2,5,null,null,9,6,null,7]
            1
         /     \
        3       2
      /          \
     5            9
    /            /
   6            7
Output: 7
Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).

Example 3:
Input: root = [1,3,2,5]
            1
         /     \
        3       2
      /
     5
Output: 2
Explanation: The maximum width exists in the second level with length 2 (3,2).

Constraints:
The number of nodes in the tree is in the range [1, 3000].
-100 <= Node.val <= 100
* */
public class MaximumWidthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(3);
        root.left.left  = new TreeNode<>(5);
        root.left.right  = new TreeNode<>(3);
        root.right = new TreeNode<>(2);
        root.right.right = new TreeNode<>(9);
        System.out.println(widthOfBinaryTree(root));

        root = new TreeNode<>(1);
        root.left = new TreeNode<>(3);
        root.left.left  = new TreeNode<>(5);
        root.left.left.left  = new TreeNode<>(6);
        root.right = new TreeNode<>(2);
        root.right.right = new TreeNode<>(9);
        root.right.right.left = new TreeNode<>(7);
        System.out.println(widthOfBinaryTree(root));
    }

    /*
    * We can index the nodes at every level and take the difference of this indexes
    * root is at index i, leftChild = 2*i+1 and rightChild = 2*i+2
    * to prevent overflow of indexes, we want to start the indexing at every level from 0
    * so when ever we are inserting the node to the queuw we first subtract 1 from currentIndex and then calculate the index
    * */
    public static int widthOfBinaryTree(TreeNode<Integer> root) {
        if(root == null) return 0;
        Queue<QueueData> queue = new LinkedList<>();
        queue.add(new QueueData(0, root));
        int width = 0;
        while(!queue.isEmpty()) {
            int levelMinIdx = 0, levelMaxIdx = 0;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                QueueData data = queue.poll();
                int currentIdx = data.index - 1; // to prevent overflow of indexes, we want to start the indexing at every level from 0
                if(i == 0) levelMinIdx = currentIdx;
                if(i == n - 1) levelMaxIdx = currentIdx;
                if(data.node.left != null) {
                    queue.add(new QueueData(2*currentIdx + 1, data.node.left));
                }
                if(data.node.right != null) {
                    queue.add(new QueueData(2*currentIdx + 2, data.node.right));
                }
            }
            width = Math.max(width, levelMaxIdx - levelMinIdx + 1);
        }
        return width;
    }
}

class QueueData{
    int index;
    TreeNode<Integer> node;
    public QueueData(int index, TreeNode<Integer> node) {
        this.index = index;
        this.node = node;
    }
}
