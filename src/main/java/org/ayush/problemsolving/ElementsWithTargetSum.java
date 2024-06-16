package org.ayush.problemsolving;

import java.util.Arrays;

/*
* Given a list of numbers and a target, we need to find exactly three elements whose sum matches the target
* */
public class ElementsWithTargetSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(elementWithTargetSum(new int[]{1, 6, 3, 9, 12, 7}, 13)));
    }

    private static int[] elementWithTargetSum(int[] nums, int target){
        int[] ans = new int[3];
        Arrays.sort(nums);
        for(int i=0; i<nums.length; i++){
            int ele1 = nums[i];
            int l = i+1;
            int r = nums.length - 1;
            boolean ansFound = false;
            while(l<r){
                if(nums[l]+nums[r] == target-ele1){
                    ans[0] = ele1;
                    ans[1] = nums[l];
                    ans[2] = nums[r];
                    ansFound = true;
                    break;
                } else if (nums[l]+nums[r] < target-ele1) {
                    l += 1;
                }else {
                    r -= 1;
                }
            }
            if(ansFound){
                break;
            }
        }
        return ans;
    }
}
