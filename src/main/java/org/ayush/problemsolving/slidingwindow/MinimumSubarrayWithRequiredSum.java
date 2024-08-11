package org.ayush.problemsolving.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;

/*
You have been given an array(ARR) of positive integers and an integer X. You have to find the minimum length subarray such that the sum of all of its elements is strictly greater than the given integer X.

Note:
A subarray is a contiguous block of elements that can be formed by deleting some (possibly zero) elements from the beginning or the end of the original array.
For example :
If the given array is [1, 2, 3, 4, 5], then [2, 3, 4], [1, 2], [5] are some subarrays while [1, 3], [2, 3, 5] are not.

If there are multiple subarrays with minimum length, find one which appears earlier in the array (i.e. subarray that starts with lower index).

If there is no such subarray, print an empty line.
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
1 <= N <= 5 * 10^5
1 <= X <= 10^9
1 <= ARR[i] <= 10^9

Time Limit: 1 sec
Sample Input 1:
4 13
13 7 6 12
Sample Output 1:
13 7
Explanation For Sample Input 1:
Out of all the subarrays, we have [13, 7] and [6, 12] with minimum length of 2 and sum of their elements greater than X = 13. As the starting index of [13, 7] is lower, we print it as the output.
Sample Input 2:
5 6
1 2 3 4 5
Sample Output 2:
3 4
* */
public class MinimumSubarrayWithRequiredSum {
    public static void main(String[] args) {
        System.out.println(minSubarray(new ArrayList<>(Arrays.asList(13, 7, 6, 12)), 4, 13));
        System.out.println(minSubarray(new ArrayList<>(Arrays.asList(6, 8, 9, 5, 3)), 5, 15));
        System.out.println(minSubarray(new ArrayList<>(Arrays.asList(12, 38, 103, 72, 38)), 5, 100));

        System.out.println(minsubarray2(new ArrayList<>(Arrays.asList(13, 7, 6, 12)), 4, 13));
        System.out.println(minsubarray2(new ArrayList<>(Arrays.asList(6, 8, 9, 5, 3)), 5, 15));
        System.out.println(minsubarray2(new ArrayList<>(Arrays.asList(11, 25, 10, 17, 11, 16, 17, 15, 7, 16 )), 10, 50));
        System.out.println(minsubarray2(new ArrayList<>(Arrays.asList(12, 38, 103, 72, 38)), 5, 100));
    }

    // O(N^2)
    public static ArrayList<Integer> minSubarray(ArrayList<Integer> arr, int n, int x) {
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i<n; i++){
            ArrayList<Integer> subArr = new ArrayList<>();
            int j = i+1;
            int sum = arr.get(i);
            subArr.add(arr.get(i));
            while(j < n){
                if(sum > x){
                    break;
                }
                sum += arr.get(j);
                subArr.add(arr.get(j));
                j++;
            }
            if(sum>x && (ans.size()==0 || ans.size() > subArr.size())) {
                ans = subArr;
            }
        }
        return ans;
    }

    /*
    O(N)
    Start with an empty subarray
    add elements to the subarray until the sum is less than or equal to target
    If the sum becomes greater than k, remove elements from the start of the current subarray.
    * */
    public static ArrayList<Integer> minsubarray2(ArrayList<Integer> arr, int n, int x) {
        ArrayList<Integer> ans = new ArrayList<>();
        int sum = 0;
        int start = 0, end = 0;
        ArrayList<Integer> subArr = new ArrayList<>();
        while (end < n) {
            // Keep adding array elements while current sum
            // is smaller than or equal to x
            while (sum <= x && end < n) {
                sum += arr.get(end);
                subArr.add(arr.get(end));
                end++;
            }

            // If current sum becomes greater than x.
            while (sum > x && start < n) {
                if(sum>x && (ans.size()==0 || ans.size() > subArr.size())) {
                    ans = new ArrayList<>(subArr);
                }
                // remove starting elements
                sum -= arr.get(start);
                subArr.remove(arr.get(start));
                start++;
            }
        }
        return ans;
    }
}
