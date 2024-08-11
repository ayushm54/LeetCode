package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/*
You are given a 'Binary Tree'.
Return the bottom view of the binary tree.

Note :
1. A node will be in the bottom-view if it is the bottom-most node at its horizontal distance from the root.
2. The horizontal distance of the root from itself is 0. The horizontal distance of the right child of the root node is 1 and
   the horizontal distance of the left child of the root node is -1.
3. The horizontal distance of node 'n' from root = horizontal distance of its parent from root + 1, if node 'n' is the right child of its parent.
4. The horizontal distance of node 'n' from root = horizontal distance of its parent from the root - 1, if node 'n' is the left child of its parent.
5. If more than one node is at the same horizontal distance and is the bottom-most node for that horizontal distance, including the one which is more towards the right.

Example:
Input: Consider the given Binary Tree:  (5, 6 and 1 are at same level, we take the right node)
            1
         /     \
        2       3
      /   \   /   \
     4     5 6     7
Output: 4 2 6 3 7

Expected Time Complexity:
Try to do this in O(n*log(n)).

Constraints:
1 <= n <= 10000
Where 'n' is the total number of nodes in the binary tree.
Time Limit: 1 sec
* */
public class BottomView {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.right = new TreeNode<>(3);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(7);
        System.out.println(bottomView(root));
    }
    public static List<Integer> bottomView(TreeNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<VerticalData> queue = new LinkedList<>();
        Map<Integer, List<Integer>> map = new TreeMap<>();
        queue.add(new VerticalData(root, 0));
        while(!queue.isEmpty()) {
            VerticalData verticalLevelData = queue.poll();
            TreeNode<Integer> node = verticalLevelData.node;
            int vertical = verticalLevelData.vertical;
            if(!map.containsKey(vertical)) {
                map.put(vertical, new ArrayList<>());
            }
            map.get(vertical).add(node.data);
            if(node.left != null) {
                queue.add(new VerticalData(node.left, vertical-1));
            }
            if(node.right != null) {
                queue.add(new VerticalData(node.right, vertical+1));
            }
        }
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            result.add(entry.getValue().get(entry.getValue().size()-1));
        }
        return result;
    }
}
