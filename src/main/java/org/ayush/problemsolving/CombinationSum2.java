package org.ayush.problemsolving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
* Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
[[1,1,6],[1,2,5],[1,7],[2,6]]

Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output:
[[1,2,2],[5]]


Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
* */
public class CombinationSum2 {
    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
        System.out.println(combinationSum2(new int[]{2,5,2,1,2}, 5));
        System.out.println(combinationSum2(new int[]{3,1,3,5,1,1}, 5));
        System.out.println(combinationSum2(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, 30));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(candidates);
        getSubsets(0, subsets, new ArrayList<>(), candidates, target);
        return subsets;
    }

    public static void getSubsets(int idx, List<List<Integer>> subsets, List<Integer> sublist, int[] nums, int target){
        if(0 == target){
            subsets.add(new ArrayList<>(sublist));
            return;
        }
        for(int i =idx; i<nums.length; i++){
            if(i>idx && nums[i] == nums[i-1]) continue;
            if(nums[i] > target) break;
            sublist.add(nums[i]);
            getSubsets(i+1, subsets, sublist, nums, target-nums[i]);
            sublist.remove(sublist.size()-1);
        }
    }
}
