package org.ayush.problemsolving.tree;

import org.ayush.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
* * Example: 3 9 20 15 7
 *               3
 *           /       \
 *          9        20
 *                 /    \
 *                15     7
 *
 * * Example: 1 3 2 5 4 7 6
 *               1
 *           /       \
 *          3        2
 *       /    \
 *     5      4
 *   /   \
 *  7     6
 *
* */
public class LevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.setLeft(new TreeNode<>(9));
        root.setRight(new TreeNode<>(20));
        root.getRight().setLeft(new TreeNode<>(15));
        root.getRight().setRight(new TreeNode<>(7));
        levelOrder(root);
        System.out.println();
        System.out.println(levelOrder2(root));
        root = new TreeNode<>(1);
        root.setLeft(new TreeNode<>(3));
        root.setRight(new TreeNode<>(2));
        root.getLeft().setLeft(new TreeNode<>(5));
        root.getLeft().setRight(new TreeNode<>(4));
        root.getLeft().getLeft().setLeft(new TreeNode<>(7));
        root.getLeft().getLeft().setRight(new TreeNode<>(6));
        levelOrder(root);
        System.out.println();
        System.out.println(levelOrder2(root));

        root = new TreeNode<>(1);
        root.setLeft(new TreeNode<>(2));
        root.setRight(new TreeNode<>(3));
        root.getLeft().setLeft(new TreeNode<>(4));
        root.getLeft().setRight(new TreeNode<>(5));
        levelOrder(root);
        System.out.println();
        System.out.println(levelOrder2(root));
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


    public static List<List<Integer>> levelOrder2(TreeNode<Integer> root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode<Integer> current = queue.poll();
                l.add(current.getData());
                if (current.getLeft() != null) {
                    queue.add(current.getLeft());
                }
                if (current.getRight() != null) {
                    queue.add(current.getRight());
                }
            }
            ans.add(l);
        }
        return ans;
    }
}
