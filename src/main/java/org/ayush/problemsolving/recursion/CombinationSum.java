package org.ayush.problemsolving.recursion;

import java.util.ArrayList;
import java.util.List;

/*
* Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
frequency
 of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.



Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []


Constraints:

1 <= candidates.length <= 30
2 <= candidates[i] <= 40
All elements of candidates are distinct.
1 <= target <= 40
* */
public class CombinationSum {
    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(combinationSum(new int[]{2, 3, 5}, 8));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> subsets = new ArrayList<>();
        getSubsets(0, subsets, new ArrayList<>(), candidates, target);
        return subsets;
    }

    public static void getSubsets(int i, List<List<Integer>> subsets, List<Integer> sublist, int[] nums, int target){
        if(i >= nums.length || target < 0) return;
        if(0 == target){
            List<Integer> l = new ArrayList<>(sublist);
            subsets.add(l);
            return;
        }
        // Take the element and move forward, this will start one recursion tree considering the current element as part of the subset
        sublist.add(nums[i]);
        getSubsets(i, subsets, sublist, nums, target - nums[i]);

        // Do not take the element but move forward, this will start another recursion tree by skipping the current element as part of the subset
        sublist.remove(Integer.valueOf(nums[i]));
        getSubsets(i+1, subsets, sublist, nums, target);
    }
}
