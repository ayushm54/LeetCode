package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

/*
You are given a Binary Tree of 'n' nodes.
The Top view of the binary tree is the set of nodes visible when we see the tree from the top.
Find the top view of the given binary tree, from left to right.

Example :
Input: Let the binary tree be:
                    1
             /             \
            2               3
          /   \               \
         4     5               6
           \                 /
            7               8
          /                  \
         9                    11
       /
     10
Output: [10, 4, 2, 1, 3, 6]

Expected time complexity :
The expected time complexity is O(n * log(n)).

Constraints:
1 <= 'n' <= 10000
1 <= data in any node <= 10 ^ 6
Time limit: 1 sec
* */
public class TopView {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        // Left subtree
        root.left = new TreeNode<>(2);
        root.left.right = new TreeNode<>(5);
        root.left.left = new TreeNode<>(4);
        root.left.left.right = new TreeNode<>(7);
        root.left.left.right.left = new TreeNode<>(9);
        root.left.left.right.left.left = new TreeNode<>(10);
        // right subtree
        root.right = new TreeNode<>(3);
        root.right.right = new TreeNode<>(6);
        root.right.right.left = new TreeNode<>(8);
        root.right.right.left.right = new TreeNode<>(11);
        System.out.println(getTopView(root));
    }

    public static List<Integer> getTopView(TreeNode<Integer> root) {
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
            result.add(entry.getValue().get(0));
        }
        return result;
    }
}

class VerticalData {
    TreeNode<Integer> node;
    int vertical;

    public VerticalData(TreeNode<Integer> node, int vertical) {
        this.node = node;
        this.vertical = vertical;
    }
}
