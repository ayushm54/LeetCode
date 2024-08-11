package org.ayush.problemsolving.dynamicProgramming;

import java.util.Arrays;

/*
Given two strings s and t, return the number of distinct subsequences of s which equals t.
The test cases are generated so that the answer fits on a 32-bit signed integer.

Example 1:
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit

Example 2:
Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag

Constraints:
1 <= s.length, t.length <= 1000
s and t consist of English letters.
* */
public class DistinctSubsequencesString {
    public static void main(String[] args) {
        System.out.println(numDistinct("babgbag", "bag"));
        System.out.println(numDistinct1("babgbag", "bag"));
        System.out.println(numDistinct2("babgbag", "bag"));
        System.out.println(numDistinct3 ("babgbag", "bag"));
        System.out.println(numDistinct4 ("babgbag", "bag"));
    }

    public static int numDistinct(String s, String t) {
        int dp[][] = new int[s.length()][t.length()];
        for(int[] row : dp) Arrays.fill(row, -1);
        return dfs(s, t, s.length()-1, t.length()-1, dp);
    }

    private static int dfs(String s, String t, int i, int j, int dp[][]) {
        // Base case
        if(j < 0) return 1; // if all characters of t are matched
        if(i < 0 && j >= 0) return 0;  // when we have exhausted string s, but still finding a match for string t
        if(dp[i][j] != -1) return dp[i][j];
        // take: when the i and j matches
        if(s.charAt(i) == t.charAt(j)){
            // we do both things first we take the matching characters and then we don't
            return dp[i][j] = dfs(s, t, i-1, j-1, dp) + dfs(s, t, i-1, j, dp);
        }

        // not take: when i and j are noth matching
        return dp[i][j] = dfs(s, t, i-1, j, dp);
    }

    public static int numDistinct1(String s, String t) {
        int dp[][] = new int[s.length()+1][t.length()+1];
        for(int[] row : dp) Arrays.fill(row, -1);
        return dfs1(s, t, s.length(), t.length(), dp);
    }

    private static int dfs1(String s, String t, int i, int j, int dp[][]) {
        // Base case
        if(j == 0) return 1; // if all characters of t are matched
        if(i == 0 && j > 0) return 0;  // when we have exhausted string s, but still finding a match for string t
        if(dp[i][j] != -1) return dp[i][j];
        // take: when the i and j matches
        if(s.charAt(i-1) == t.charAt(j-1)){
            // we do both things first we take the matching characters and then we don't
            return dp[i][j] = dfs1(s, t, i-1, j-1, dp) + dfs1(s, t, i-1, j, dp);
        }

        // not take: when i and j are noth matching
        return dp[i][j] = dfs1(s, t, i-1, j, dp);
    }

    // tabulation
    public static int numDistinct2(String s, String t) {
        int dp[][] = new int[s.length()+1][t.length()+1]; // since we cant handle negative indices we are shifting indices by 1 to make it 1 based indexing

        // Base case:  if(j == 0) return 1; // if all characters of t are matched
        for(int i = 0; i <dp.length; i++) dp[i][0] = 1;
        // Base case 2: when we have exhausted string s, but still finding a match for string t -> since dp is initialized with 0 no need to write this case
        for(int i = 1; i <= s.length(); i++){
            for(int j = 1; j <= t.length(); j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }

    // tabulation space optimized
    public static int numDistinct3(String s, String t) {
        int prev[] = new int[t.length() + 1];
        int cur[] = new int[t.length() + 1];
        // Base case:  if(j == 0) return 1; // if all characters of t are matched
        prev[0] = cur[0] = 1;
        for(int i = 1; i <= s.length(); i++){
            for(int j = 1; j <= t.length(); j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    cur[j] = prev[j-1] + prev[j];
                }else{
                    cur[j] = prev[j];
                }
            }
            prev = cur;
        }
        return prev[t.length()];
    }

    // tabulation space optimized without cur array
    public static int numDistinct4(String s, String t) {
        int prev[] = new int[t.length() + 1];
        // Base case:  if(j == 0) return 1; // if all characters of t are matched
        prev[0]  = 1;
        for(int i = 1; i <= s.length(); i++){
            for(int j = t.length(); j >= 1; j--){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    prev[j] = prev[j-1] + prev[j];
                }
            }
        }
        return prev[t.length()];
    }
}
