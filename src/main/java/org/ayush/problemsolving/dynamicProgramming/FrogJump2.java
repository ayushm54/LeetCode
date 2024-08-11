package org.ayush.problemsolving.dynamicProgramming;

import java.util.Arrays;

/*
There are N stones, numbered 1,2,…,N. For eachi (1≤i≤N), the height of Stone i is hi.
There is a frog who is initially on Stone 1. He will repeat the following action some number of times to reach Stone N:
If the frog is currently on Stone i, jump to one of the following: Stone i+1,i+2,…,i+K. Here, a cost of ∣hi - hj∣ is incurred,
where j is the stone to land on.
Find the minimum possible total cost incurred before the frog reaches Stone N.

Constraints
All values in input are integers.
2≤N≤10
5

1≤K≤100
1≤hi≤10^4

Input
Input is given from Standard Input in the following format:

N
K
h1 h2...hN

Output
Print the minimum possible total cost incurred.

Sample Input 1
Copy
5 3
10 30 40 50 20
Sample Output 1
Copy
30
If we follow the path
1 →
2 →
5, the total cost incurred would be
∣10−30∣+∣30−20∣=30.

Sample Input 2
Copy
3 1
10 20 10
Sample Output 2
Copy
20
If we follow the path
1 →
2 →
3, the total cost incurred would be
∣10−20∣+∣20−10∣=20.

Sample Input 3
Copy
2 100
10 10
Sample Output 3
Copy
0
If we follow the path
1 →
2, the total cost incurred would be
∣10−10∣=0.

Sample Input 4
Copy
10 4
40 10 20 70 80 10 20 70 80 60
Sample Output 4
Copy
40
If we follow the path
1 →
4 →
8 →
10, the total cost incurred would be
∣40−70∣+∣70−70∣+∣70−60∣=40.
* */
public class FrogJump2 {

    public static void main(String[] args) {
        System.out.println(frogJump(5, new int[]{10, 30, 40, 50, 20}, 3));
        System.out.println(frogJump(1, new int[]{10, 100}, 100));
        System.out.println(frogJump(10, new int[]{40, 10, 20, 70, 80, 10, 20, 70, 80, 60}, 4));

        System.out.println(frogJum1(5, new int[]{10, 30, 40, 50, 20}, 3));
        System.out.println(frogJum1(1, new int[]{10, 100}, 100));
        System.out.println(frogJum1(10, new int[]{40, 10, 20, 70, 80, 10, 20, 70, 80, 60}, 4));
    }

    /*DP with tabulation(top-down), has overlapping sub-problems
    * space = O(N)*/
    public static int frogJum1(int n, int heights[], int K) {
        int[] dp = new int[heights.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i < heights.length; i++) {
            for(int j = 1; j <= K; j++) {
                if(i-j >= 0){
                    dp[i] = Math.min(dp[i], dp[i-j] + Math.abs(heights[i]-heights[i-j]));
                }
            }
        }
        return dp[n - 1];
    }

    /*Recursion with DP using memoization(top-down), has overlapping sub-problems*/
    public static int frogJump(int n, int heights[], int K) {
        int[] dp = new int[heights.length];
        Arrays.fill(dp, -1);
        return dfs(heights, n-1, dp, K);
    }

    private static int dfs(int[] heights, int i, int[] dp, int K) {
        if (i == 0){
            return 0;
        }
        if(dp[i] != -1) return dp[i];
        int minEnergy = Integer.MAX_VALUE;
        for (int j = 1; j <= K; j++) {
            if(i-j >= 0) minEnergy = Math.min(minEnergy, dfs(heights, i-j, dp, K) + Math.abs(heights[i]-heights[i-j]));
        }
        dp[i] = minEnergy;
        return dp[i];
    }
}
