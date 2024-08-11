package org.ayush.problemsolving.arrays;

import java.util.Arrays;

/*
Example :
Input: 'arr' = [1, 2, 7, -4, 3, 2, -10, 9, 1]
maxSun: 11
Output: [1, 2, 7, -4, 3, 2]
Explanation: The subarray yielding the maximum sum is [1, 2, 7, -4, 3, 2].
Detailed explanation ( Input/output format, Notes, Images )
* */
public class MaximumSubArrayPrint {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSubarray(new int[]{1, 2, 7, -4, 3, 2, -10, 9, 1}, 9)));
        System.out.println(Arrays.toString(maxSubarray(new int[]{18, -6, -6, -5, 7, 10, 16, -6, -2, 0}, 10)));
    }

    private static int[] maxSubarray(int[] arr, int n) {
        int maxi = arr[0]; // maximum sum
        int sum = 0;
        int start = -1;
        int ansStart = -1, ansEnd = -1;
        for (int i = 0; i < n; i++) {
            if(sum == 0){
                // whenever sum is 0, it means we are starting with new subarray
                start = i;
            }
            sum+=arr[i];
            if (sum > maxi) {
                maxi = sum;
                ansStart = start;
                ansEnd = i;
            }
            if(sum < 0){
                sum = 0;
            }
        }
        if (maxi < 0) maxi = 0;
        return new int[]{ansStart, ansEnd};
    }
}
