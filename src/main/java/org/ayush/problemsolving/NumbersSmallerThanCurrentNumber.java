package org.ayush.arrays;

import java.util.Arrays;
import java.util.Collections;

/*
* Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].

Return the answer in an array.

Example 1:
Input: nums = [8,1,2,2,3]
Output: [4,0,1,1,3]
Explanation:
For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
For nums[1]=1 does not exist any smaller number than it.
For nums[2]=2 there exist one smaller number than it (1).
For nums[3]=2 there exist one smaller number than it (1).
For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).

* Example 2:
Input: nums = [6,5,4,8]
Output: [2,1,0,3]

* Example 3:
Input: nums = [7,7,7,7]
Output: [0,0,0,0]
*
Constraints:
2 <= nums.length <= 500
0 <= nums[i] <= 100
* */
public class NumbersSmallerThanCurrentNumber {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3})));
        System.out.println(Arrays.toString(smallerNumbersThanCurrent(new int[]{6,5,4,8})));
        System.out.println(Arrays.toString(smallerNumbersThanCurrent(new int[]{7,7,7,7})));

        System.out.println(Arrays.toString(smallerNumbersThanCurrentUsingFrequencies(new int[]{8, 1, 2, 2, 3})));
        System.out.println(Arrays.toString(smallerNumbersThanCurrentUsingFrequencies(new int[]{6,5,4,8})));
        System.out.println(Arrays.toString(smallerNumbersThanCurrentUsingFrequencies(new int[]{7,7,7,7})));
    }

    private static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] ans = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            int count = 0;
            for(int j=0; j<nums.length; j++){
                if(i!=j && nums[i]> nums[j]){
                    count+=1;
                }
            }
            ans[i] = count;
        }
        return ans;
    }

    private static int[] smallerNumbersThanCurrentUsingFrequencies(int[] nums) {
        int max = -1;
        for(int num : nums){
            max = Math.max(max, num);
        }
        int [] frequencies = new int[max+1];
        //Get the frequency of each integer availabe in nums[]
        for(int i=0;i<nums.length;i++) {
            frequencies[nums[i]]++;
        }
        //Get the number of smaller integer with respect to current +1 by adding current and previous value;
        for(int i = 1;i<frequencies.length;i++) {
            frequencies[i]+=frequencies[i-1];
        }
        //Create an array of size nums.length
        int [] result = new int[nums.length];
        //Loop through frequencies[] using nums[i] and place it in result[].
        for(int i =0;i<result.length;i++){
            if (nums[i]==0) result[i] = 0;
            else result[i]=frequencies[nums[i]-1];
        }
        return result;
    }
}
