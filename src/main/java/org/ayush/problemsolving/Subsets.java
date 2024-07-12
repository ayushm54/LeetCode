package org.ayush.problemsolving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
* Given an integer array nums of unique elements, return all possible
subsets
 (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
* */
public class Subsets {
    public static void main(String[] args) {
        System.out.println(subsets(new int[] {1, 2}));
        System.out.println(subsets(new int[] {1, 2, 3}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        getSubsets(0, subsets, new ArrayList<>(), nums);
        return subsets;
    }

    /*
    * At every call we have two choices:
    *   1. Take the current element and move forward
    *   2. Do not take the current element but move forward
    * */
    public static void getSubsets(int i, List<List<Integer>> subsets, List<Integer> sublist, int[] nums){
        if(i >= nums.length){
            List<Integer> l = new ArrayList<>(sublist);
            subsets.add(l);
            return;
        }
        // Take the element and move forward, this will start one recursion tree considering the current element as part of the subset
        sublist.add(nums[i]);
        getSubsets(i+1, subsets, sublist, nums);

        // Do not take the element but move forward, this will start another recursion tree by skipping the current element as part of the subset
        sublist.remove(Integer.valueOf(nums[i]));
        getSubsets(i+1, subsets, sublist, nums);
    }

    // Using power set(left shift)
    public static List<List<Integer>> getSubsets(int[] nums){
        List<List<Integer>> subsets = new ArrayList<>();
        int numSubsets = (int) Math.pow(2, nums.length);
        for(int i = 0; i < numSubsets; i++){
            List<Integer> subset = new ArrayList<>();
            for(int j = 0; j < nums.length; j++){
                int x = 1 << j;
                if((i & x) != 0){
                    subset.add(nums[j]);
                }
            }
            subsets.add(subset);
        }
        return subsets;
    }
}
