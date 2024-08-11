package org.ayush.problemsolving.binarysearch;

import java.util.Arrays;

/*
* You are given an array 'arr' consisting of 'n' integers which denote the position of a stall.
You are also given an integer 'k' which denotes the number of aggressive cows.
You are given the task of assigning stalls to 'k' cows such that the minimum distance between any two of them is the maximum possible.
Print the maximum possible minimum distance.
Example:
Input: 'n' = 3, 'k' = 2 and 'arr' = {1, 2, 3}
Output: 2
Explanation: The maximum possible minimum distance will be 2 when 2 cows are placed at positions {1, 3}. Here distance between cows is 2.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
6 4
0 3 4 7 10 9
Sample Output 1 :
3
Explanation to Sample Input 1 :
The maximum possible minimum distance between any two cows will be 3 when 4 cows are placed at positions {0, 3, 7, 10}. Here distance between cows are 3, 4 and 3 respectively.

Sample Input 2 :
5 2
4 2 1 3 6

Sample Output 2 :
5

Expected time complexity:
Can you solve this in O(n * log(n)) time complexity?

Constraints :
2 <= 'n' <= 10 ^ 5
2 <= 'k' <= n
0 <= 'arr[i]' <= 10 ^ 9
Time Limit: 1 sec.
* */
public class AgressiveCows {
    public static void main(String[] args) {
        System.out.println(aggressiveCows(new int[]{0, 3, 4, 7, 10, 9}, 4));
        System.out.println(aggressiveCows(new int[]{4, 2, 1, 3, 6}, 2));
        System.out.println(aggressiveCows(new int[]{1, 2, 3}, 2));
    }

    public static int aggressiveCows(int []stalls, int k) {
        Arrays.sort(stalls);
        int low = 1, high = stalls[stalls.length - 1] - stalls[0]; // high can be the max distance between starting stall and ending stall
        int ans = Integer.MIN_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            boolean isArrangementPossible = isArrangementPossible(stalls, mid, k);
            if(isArrangementPossible){
                ans = Math.max(ans, mid);
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private static boolean isArrangementPossible(int[] stalls, int minDistance , int k) {
        // we will always place the first cow at the starting stall
        int countCows = 1, lastCowPosition = stalls[0];
        for(int i = 1; i < stalls.length; i++) {
            if(countCows < k && stalls[i] - lastCowPosition >= minDistance) {
                countCows++;
                lastCowPosition = stalls[i];
            }
        }
        return countCows == k;
    }
}
