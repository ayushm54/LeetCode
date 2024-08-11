package org.ayush.problemsolving.arrays;

import java.util.HashMap;
import java.util.Map;

/*
Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2

Constraints:
n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109

Follow-up: Could you solve the problem in linear time and in O(1) space?
* */
public class MajorityElement {
    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{2,2,1,1,1,2,2}));

        System.out.println(majorityElement1(new int[]{2,2,1,1,1,2,2}));
    }

    public static int majorityElement(int[] nums) {
        int n = nums.length;
        if(n==1) return nums[0];
        Map<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i<n; i++){
            if(m.containsKey(nums[i])){
                int cnt = m.get(nums[i]) + 1;
                if(cnt > n/2) return nums[i];
                m.put(nums[i], cnt);
            }else{
                m.put(nums[i], 1);
            }
        }
        return -1;
    }

    // Moore's voting algorithm
    // The idea is to start with an element
    // then in every iteration check if the current element matches toi this increase count
    // if it doesn't then decrease the count
    // if the count becomes 0, then we pick a new element
    // at the end we will have an element and we just need to check its count in the entire arrays to match the condition
    public static int majorityElement1(int[] nums) {
        int n = nums.length;
        int cnt = 0, ele = 0;
        for(int i = 0; i<n; i++){
            if(cnt == 0){
                ele = nums[i];
                cnt = 1;
            } else if(nums[i] == ele){
                cnt++;
            }else{
                cnt--;
            }
        }

        int count = 0;
        for(int i = 0; i<n; i++){
            if(nums[i] == ele) count++;
        }
        if(count > n/2) return ele;
        return -1;
    }
}
