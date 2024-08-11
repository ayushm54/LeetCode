package org.ayush.problemsolving.arrays;

import java.util.HashMap;
import java.util.Map;

/*
You are given an array 'a' of size 'n' and an integer 'k'.
Find the length of the longest subarray of 'a' whose sum is equal to 'k'.

Example :
Input: ‘n’ = 7 ‘k’ = 3
‘a’ = [1, 2, 3, 1, 1, 1, 1]
Output: 3

Explanation: Subarrays whose sum = ‘3’ are:

[1, 2], [3], [1, 1, 1] and [1, 1, 1]

Here, the length of the longest subarray is 3, which is our final answer.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
7 3
1 2 3 1 1 1 1


Sample Output 1 :
3


Explanation Of Sample Input 1 :
Subarrays whose sum = ‘3’ are:
[1, 2], [3], [1, 1, 1] and [1, 1, 1]
Here, the length of the longest subarray is 3, which is our final answer.


Sample Input 2 :
4 2
1 2 1 3


Sample Output 2 :
1


Sample Input 3 :
5 2
2 2 4 1 2


Sample Output 3 :
1


Expected time complexity :
The expected time complexity is O(n).


Constraints :
1 <= 'n' <= 5 * 10 ^ 6
1 <= 'k' <= 10^18
0 <= 'a[i]' <= 10 ^ 9

Time Limit: 1-second
* */
public class LongestSubarrayWithSumK {
    public static void main(String[] args) {
        System.out.println(longestSubarrayWithSumK(new int[]{1, 2, 3, 1, 1, 1, 1}, 3));
        System.out.println(longestSubarrayWithSumK(new int[]{1, 2, 1, 3}, 2));
        System.out.println(longestSubarrayWithSumK(new int[]{8, 15, 17, 0, 11}, 17));

        System.out.println(longestSubarrayWithSumK1(new int[]{1, 2, 3, 1, 1, 1, 1}, 3));
        System.out.println(longestSubarrayWithSumK1(new int[]{1, 2, 1, 3}, 2));
        System.out.println(longestSubarrayWithSumK1(new int[]{8, 15, 17, 0, 11}, 17));

        System.out.println(longestSubarrayWithSumK2(new int[]{1, 2, 3, 1, 1, 1, 1}, 3));
        System.out.println(longestSubarrayWithSumK2(new int[]{1, 2, 1, 3}, 2));
        System.out.println(longestSubarrayWithSumK2(new int[]{8, 15, 17, 0, 11}, 17));
    }

    public static int longestSubarrayWithSumK(int []a, long k) {
        int n = a.length; // size of the array.

        int len = 0;
        for (int i = 0; i < n; i++) { // starting index
            long s = 0; // Sum variable
            for (int j = i; j < n; j++) { // ending index
                // add the current element to
                // the subarray a[i...j-1]:
                s += a[j];

                if (s == k)
                    len = Math.max(len, j - i + 1);
            }
        }
        return len;
    }

    // Optimized
    // The ideas is to store the cumulative sum till an index in a map
    // at every index we check if the cummulative sum is k  then we find max Len
    // if it is greater than k, but previously there was a sub array with sum k in the map, we subtract it and find max len
    public static int longestSubarrayWithSumK1(int []a, long k) {
        int n = a.length; // size of the array.

        Map<Long, Integer> preSumMap = new HashMap<>();
        long sum = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            //calculate the prefix sum till index i
            sum += a[i];

            // if the sum = k, update the maxLen:
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // calculate the sum of remaining part i.e. x-k:
            long rem = sum - k;

            //Calculate the length and update maxLen:
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            //Finally, update the map checking the conditions:
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum, i);
            }
        }

        return maxLen;
    }

    // 2 pointer approach most optimal
    public static int longestSubarrayWithSumK2(int []nums, long k) {
        int left = 0, right = 0, maxLen = 0;
        long sum = nums[0];
        while(right < nums.length){
            while(left<= right && sum > k){
                sum -= nums[left];
                left++;
            }
            if(sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
            right++;
            if(right < nums.length) sum += nums[right];
        }
        return maxLen;
    }
}
