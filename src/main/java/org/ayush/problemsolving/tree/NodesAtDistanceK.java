package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/*
Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values
of all nodes that have a distance k from the target node.
You can return the answer in any order.

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
                3
           /          \
          5            1
       /  |            |  \
      6   2            0   8
        /   \
       7     4

Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
Example 2:

Input: root = [1], target = 1, k = 3
Output: []


Constraints:

The number of nodes in the tree is in the range [1, 500].
0 <= Node.val <= 500
All the values Node.val are unique.
target is the value of one of the nodes in the tree.
0 <= k <= 1000
* */
public class NodesAtDistanceK {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        System.out.println(distanceK(root, root.left, 2));
    }

    public static List<Integer> distanceK(TreeNode<Integer> root, TreeNode<Integer> target, int k) {
        Map<TreeNode<Integer>, TreeNode<Integer>> nodeParentMap = new HashMap<>();
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<Integer> node = queue.poll();
            if(node.left !=null){
                queue.add(node.left);
                nodeParentMap.put(node.left, node);
            }
            if(node.right !=null){
                queue.add(node.right);
                nodeParentMap.put(node.right, node);
            }
        }
        Set<TreeNode<Integer>> visited = new HashSet<>();
        queue = new LinkedList<>();
        queue.add(target);
        visited.add(target);
        int distance = 0;
        // we will move radially in left, right an dparent direction and increment distance
        // when distance matches K, the elements in the queue is out answer
        while (!queue.isEmpty()) {
            if(distance == k) break;
            int size = queue.size();
            distance++; // we increment by 1 when the children and parent nodes are added to queue
            for (int i = 0; i < size; i++) {
                TreeNode<Integer> node = queue.poll();
                if(node.left != null && !visited.contains(node.left)) {
                    queue.add(node.left);
                    visited.add(node.left);
                }
                if(node.right != null && !visited.contains(node.right)) {
                    queue.add(node.right);
                    visited.add(node.right);
                }
                if(nodeParentMap.containsKey(node) && !visited.contains(nodeParentMap.get(node))) {
                    queue.add(nodeParentMap.get(node));
                    visited.add(nodeParentMap.get(node));
                }
            }
        }
        return queue.stream().map(node -> node.data).collect(Collectors.toList());
    }
}
