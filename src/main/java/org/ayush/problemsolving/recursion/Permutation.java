package org.ayush.problemsolving.recursion;

import java.util.ArrayList;
import java.util.List;
/*
* Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]

Example 3:
Input: nums = [1]
Output: [[1]]

Constraints:
1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
* */
public class Permutation {
    public static void main(String[] args) {
        System.out.println(getPermutations(new int[] {1, 2}));
        System.out.println(getPermutations(new int[] {1, 2, 3}));

        System.out.println(getPermutations(new int[] {1, 1, 2}));
    }

    // We are using extra space here
    private static List<List<Integer>> getPermutations(int[] nums){
        List<List<Integer>> subsets = new ArrayList<>();
        getPermutations(subsets, new ArrayList<>(), nums, new boolean[nums.length]);
        return subsets;
    }

    private static void getPermutations(List<List<Integer>> subsets, List<Integer> sublist, int[] nums, boolean[] visited){
        if(sublist.size() == nums.length){
            List<Integer> l = new ArrayList<>(sublist);
            subsets.add(l);
            return;
        }

        for(int j = 0; j < nums.length; j++){
            if(!visited[j]){
                // Take the element and move forward, this will start one recursion tree considering the current element as part of the subset
                visited[j] = true;
                sublist.add(nums[j]);
                getPermutations(subsets, sublist, nums, visited);
                sublist.remove(sublist.size() - 1);
                visited[j] = false;
            }
        }
    }
}
