package org.ayush.problemsolving.dynamicProgramming;
/*
Ninja is planing this ‘N’ days-long training schedule. Each day, he can perform any one of these three activities. (Running, Fighting Practice or Learning New Moves).
Each activity has some merit points on each day. As Ninja has to improve all his skills, he can’t do the same activity in two consecutive days.
Can you help Ninja find out the maximum merit points Ninja can earn?
You are given a 2D array of size N*3 ‘POINTS’ with the points corresponding to each day and activity.
Your task is to calculate the maximum number of merit points that Ninja can earn.

For Example
If the given ‘POINTS’ array is [[1,2,5], [3 ,1 ,1] ,[3,3,3] ],the answer will be 11 as 5 + 3 + 3.
Detailed explanation ( Input/output format, Notes, Images )

Constraints:
1 <= T <= 10
1 <= N <= 100000.
1 <= values of POINTS arrays <= 100 .

Time limit: 1 sec
Sample Input 1:
2
3
1 2 5
3 1 1
3 3 3
3
10 40 70
20 50 80
30 60 90
Sample Output 1:
11
210
Explanation of sample input 1:
For the first test case,
One of the answers can be:
On the first day, Ninja will learn new moves and earn 5 merit points.
On the second day, Ninja will do running and earn 3 merit points.
On the third day, Ninja will do fighting and earn 3 merit points.
The total merit point is 11 which is the maximum.
Hence, the answer is 11.

For the second test case:
One of the answers can be:
On the first day, Ninja will learn new moves and earn 70 merit points.
On the second day, Ninja will do fighting and earn 50 merit points.
On the third day, Ninja will learn new moves and earn 90 merit points.
The total merit point is 210 which is the maximum.
Hence, the answer is 210.
Sample Input 2:
2
3
18 11 19
4 13 7
1 8 13
2
10 50 1
5 100 11
Sample Output 2:
45
110

* */
public class NinjasTraining {
    public static void main(String[] args) {
        System.out.println(ninjaTraining(3, new int[][]{{1, 2, 5}, {3, 1, 1}, {3, 3, 3}}));
        System.out.println(ninjaTraining(2, new int[][]{{10, 50, 1}, {5, 100, 11}}));

        System.out.println(ninjaTraining1(3, new int[][]{{1, 2, 5}, {3, 1, 1}, {3, 3, 3}}));
        System.out.println(ninjaTraining1(2, new int[][]{{10, 50, 1}, {5, 100, 11}}));

        System.out.println(ninjaTraining2(3, new int[][]{{1, 2, 5}, {3, 1, 1}, {3, 3, 3}}));
        System.out.println(ninjaTraining2(2, new int[][]{{10, 50, 1}, {5, 100, 11}}));

        System.out.println(ninjaTraining3(3, new int[][]{{1, 2, 5}, {3, 1, 1}, {3, 3, 3}}));
        System.out.println(ninjaTraining3(2, new int[][]{{10, 50, 1}, {5, 100, 11}}));
    }

    // Plain recursion without DP
    public static int ninjaTraining(int n, int points[][]) {
        // we will start without performing any activity first
        // days are indexed from 0
        return  dfs(n-1, 3, points);
    }

    // We will start from last day and go down to day 0
    private static int dfs(int day, int lastActivityDone, int points[][]){
        if(day == 0){
            // get the max points by skipping the lastActivityDone
            int max = Integer.MIN_VALUE;
            for (int task= 0; task<3; task++){
                if(task!=lastActivityDone){
                    max = Math.max(max, points[0][task]);
                }
            }
            return max;
        }
        int max = Integer.MIN_VALUE;
        for (int task= 0; task<3; task++){
            if(task!=lastActivityDone){
                // considering we have done the lastActivity, we will perform task on index i
                // and then dfs on the remaining activities on the previous day
                max = Math.max(max,  points[day][task] + dfs(day-1, task, points));
            }
        }
        return max;
    }

    // DP with memoization
    public static int ninjaTraining1(int n, int points[][]) {
        // since we have n days and each day we can do 4 (3+1) activities,
        // 1 is added becauase we are starting without performing any activity
        int[][] dp = new int[n][4];
        // we will start without performing any activity first
        // days are indexed from 0
        return  dfs1(n-1, 3, points, dp);
    }

    // We will start from last day and go down to day 0
    private static int dfs1(int day, int lastActivityDone, int points[][], int[][] dp){
        if (dp[day][lastActivityDone] != 0) return dp[day][lastActivityDone];

        // Base case: When it's the first day (day == 0)
        if (day == 0) {
            // get the max points by skipping the lastActivityDone
            int maxi = 0;
            for (int task = 0; task <= 2; task++) {
                if (task != lastActivityDone)
                    maxi = Math.max(maxi, points[0][task]);
            }
            return dp[day][lastActivityDone] = maxi; // Store and return the result
        }

        int maxi = 0;
        // Loop through the three activities on the current day
        for (int task = 0; task <= 2; task++) {
            if (task != lastActivityDone) {
                // Calculate the points for the current activity and recursively
                // calculate the maximum points for the previous day
                int activity = points[day][task] + dfs1(day - 1, task, points, dp);
                maxi = Math.max(maxi, activity); // Update the maximum points
            }
        }

        return dp[day][lastActivityDone] = maxi; // Store and return the result
    }

    // DP with tabulation
    public static int ninjaTraining2(int n, int points[][]) {
        // since we have n days and each day we can do 4 (3+1) activities,
        // 1 is added becauase we are starting without performing any activity
        int[][] dp = new int[n][4];
        // we will start bottom-up, by starting from day 0
        // at day 0 we can do any of the three tasks
        // so we have base cases like (0,0), (0, 1), (0,2) and (0,3)
        dp[0][0] = Math.max(points[0][1], points[0][2]);// if we have performed task 0 then we can either do task 1 or task 2
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(Math.max(points[0][0], points[0][1]), points[0][2]); // when we reach end, we would have done all the activities
        for(int day =1; day<n; day++){
            for(int lastActivityDone =0; lastActivityDone<4; lastActivityDone++){
                for(int task = 0; task<3; task++){
                    if(task!=lastActivityDone){
                        dp[day][lastActivityDone] = Math.max(dp[day][lastActivityDone], points[day][task] + dp[day-1][task]);
                    }
                }
            }
        }
        return  dp[n-1][3];
    }

    // Space optimisation
    public static int ninjaTraining3(int n, int points[][]) {
        // since we have n days and each day we can do 4 (3+1) activities,
        // we will start bottom-up, by starting from day 0
        // at day 0 we can do any of the three tasks
        // so we have base cases like (0,0), (0, 1), (0,2) and (0,3)
        int prevDay[] = new int[4];
        prevDay[0] = Math.max(points[0][1], points[0][2]);// if we have performed task 0 then we can either do task 1 or task 2
        prevDay[1] = Math.max(points[0][0], points[0][2]);
        prevDay[2] = Math.max(points[0][0], points[0][1]);
        prevDay[3] = Math.max(Math.max(points[0][0], points[0][1]), points[0][2]);

        for(int day =1; day<n; day++){
            int[] temp = new int[4];
            for(int lastActivityDone =0; lastActivityDone<4; lastActivityDone++){
                for(int task = 0; task<3; task++){
                    if(task!=lastActivityDone){
                        temp[lastActivityDone] = Math.max(temp[lastActivityDone], points[day][task] + prevDay[task]);
                    }
                }
            }
            prevDay = temp;
        }
        return  prevDay[3];
    }
}
