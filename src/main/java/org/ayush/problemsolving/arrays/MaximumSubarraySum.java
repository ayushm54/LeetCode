package org.ayush.problemsolving.arrays;
/*
You are given an array 'arr' of length 'n', consisting of integers.
A subarray is a contiguous segment of an array. In other words, a subarray can be formed by removing 0 or more integers
from the beginning and 0 or more integers from the end of an array.
Find the sum of the subarray (including empty subarray) having maximum sum among all subarrays.
The sum of an empty subarray is 0.

Example :
Input: 'arr' = [1, 2, 7, -4, 3, 2, -10, 9, 1]
Output: 11
Explanation: The subarray yielding the maximum sum is [1, 2, 7, -4, 3, 2].
Detailed explanation ( Input/output format, Notes, Images )

Sample Input 1 :
9
1 2 7 -4 3 2 -10 9 1
Sample Output 1 :
11
Explanation for Sample 1 :
The subarray yielding the maximum sum is [1, 2, 7, -4, 3, 2].

Sample Input 2 :
6
10 20 -30 40 -50 60
Sample Output 2 :
60

Sample Input 3 :
3
-3 -5 -6
Sample Output 3 :
0

Expected time complexity :
The expected time complexity is O(n).

Constraints :
1 <= 'n' <= 10 ^ 6
-10 ^ 6 <= 'arr[i]' <= 10 ^ 6

Time limit: 1sec
* */
public class MaximumSubarraySum {
    public static void main(String[] args) {
        System.out.println(maxSubarraySum(new int[]{1, 2, 7, -4, 3, 2, -10, 9, 1}, 9));
        System.out.println(maxSubarraySum(new int[]{18, -6, -6, -5, 7, 10, 16, -6, -2, 0}, 10));

        System.out.println(maxSubarraySum1(new int[]{1, 2, 7, -4, 3, 2, -10, 9, 1}, 9));
        System.out.println(maxSubarraySum1(new int[]{18, -6, -6, -5, 7, 10, 16, -6, -2, 0}, 10));

        System.out.println(maxSubarraySum2(new int[]{1, 2, 7, -4, 3, 2, -10, 9, 1}, 9));
        System.out.println(maxSubarraySum2(new int[]{18, -6, -6, -5, 7, 10, 16, -6, -2, 0}, 10));
    }

    // Brute Force
    public static long maxSubarraySum(int[] arr, int n) {
        // write your code here
        int maxi = Integer.MIN_VALUE; // maximum sum
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // subarray = arr[i.....j]
                int sum = 0;
                //add all the elements of subarray:
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                maxi = Math.max(maxi, sum);
            }
        }
        return maxi;
    }

    // Better: O(N^2)
    private static int maxSubarraySum1(int[] arr, int n) {
        int maxi = Integer.MIN_VALUE; // maximum sum
        for (int i = 0; i < n; i++) {
            // subarray = arr[i.....j]
            int sum = 0;
            for (int j = i; j < n; j++) {
                //add all the elements of subarray:
                sum += arr[j];
                maxi = Math.max(maxi, sum);
            }
        }
        return maxi;
    }

    /* Optimal: Kadane's algo
       The intuition of the algorithm is not to consider the subarray as a part of the answer if its sum is less than 0.
       A subarray with a sum less than 0 will always reduce our answer and so this type of subarray cannot be a part of the subarray with maximum sum.
    */
    private static int maxSubarraySum2(int[] arr, int n) {
        int maxi = arr[0]; // maximum sum
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum+=arr[i];
            if (sum > maxi) {
                maxi = sum;
            }
            if(sum < 0){
                sum = 0;
            }
        }
        if (maxi < 0) maxi = 0;
        return maxi;
    }
}
