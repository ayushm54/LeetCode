package org.ayush.problemsolving.arrays;
/*
You are given an array ‘ARR’ of length ‘N’ consisting of only ‘0’s and ‘1’s. Your task is to determine the maximum length of the consecutive number of 1’s.

For Example:
ARR = [0, 1, 1, 0, 0, 1, 1, 1], here you can see the maximum length of consecutive 1’s is 3. Hence the answer is 3.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
2
8
0 1 1 0 0 1 1 1
4
0 1 1 0
Sample Output 1:
3
2
Explanation for Sample Output 1:
For the first test case, ‘ARR’ = [0, 1, 1, 0, 0, 1, 1, 1], here you can see the maximum length of consecutive 1’s is 3 when we select ARR[5], ARR[6] and ARR[7]. Hence the answer is 3.

For the second test, ‘ARR’ = [0, 1, 1, 0], here you can see the maximum length of consecutive 1’s is 2 when we select ARR[1] and ARR[2]. Hence the answer is 2.
Sample Input 2:
2
6
1 1 1 1 0 0
4
1 1 1 1
Sample Output 2:
4
4
Constraints:
1 ≤ T ≤ 10
1 ≤ N ≤ 1000
ARR[i] = {0, 1}

Time Limit: 1 sec
* */
public class MaximumConsecutiveOnes {

    public static void main(String[] args) {
        System.out.println(consecutiveOnes(8, new int[]{0, 1, 1, 0, 0, 1, 1, 1}));
        System.out.println(consecutiveOnes(4, new int[]{0, 1, 1, 0}));
        System.out.println(consecutiveOnes(6, new int[]{1, 1, 1, 1, 0, 0}));
        System.out.println(consecutiveOnes(4, new int[]{1, 1, 1, 1}));
        System.out.println();
        System.out.println(consecutiveOnes1(8, new int[]{0, 1, 1, 0, 0, 1, 1, 1}));
        System.out.println(consecutiveOnes1(4, new int[]{0, 1, 1, 0}));
        System.out.println(consecutiveOnes1(6, new int[]{1, 1, 1, 1, 0, 0}));
        System.out.println(consecutiveOnes1(4, new int[]{1, 1, 1, 1}));
    }

    public static int consecutiveOnes(int n, int[] arr) {
        int maxCount = Integer.MIN_VALUE;
        for(int i = 0; i<n; i++){
            int j = i;
            int count = 0;
            while(j < n){
                if(arr[j] == 0) break;
                count++;
                j++;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }

    public static int consecutiveOnes1(int n, int[] arr) {
        int maxCount = Integer.MIN_VALUE;
        int count = 0;
        for(int i = 0; i<n; i++){
            if(arr[i] == 0) count = 0;
            else count++;
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
}
