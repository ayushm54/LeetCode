package org.ayush.problemsolving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
* Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.



Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false


Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
0 <= k <= 105

* */
public class ContainsDuplicate {

    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[]{1,2,3,1}, 3));
        System.out.println(containsNearbyDuplicate(new int[]{0,1,2,3,4,0,0,7,8,9,10,11,12,0}, 1));

        System.out.println(containsNearbyDuplicateHashMap(new int[]{1,2,3,1}, 3));
        System.out.println(containsNearbyDuplicateHashMap(new int[]{0,1,2,3,4,0,0,7,8,9,10,11,12,0}, 1));
    }
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> s = new HashSet<>();
        for(int i = 0; i<nums.length; i++){
            // We will run a sliding window of length k and check if duplicate element exists in set within that window
            if(i > k){
                s.remove(nums[i-k-1]); // removing the 1st element to maintain the window size
            }
            if(!s.add(nums[i])){
                // duplicate element exists in the window
                return true;
            }
        }
        return false;
    }

    public static boolean containsNearbyDuplicateHashMap(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i<nums.length; i++){
            if(m.containsKey(nums[i]) && (i - m.get(nums[i])) <= k) return true;
            m.put(nums[i], i);
        }
        return false;
    }
}
