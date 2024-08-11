package org.ayush.problemsolving.dynamicProgramming;

import java.util.Arrays;

/*
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character


Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')

Constraints:
0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
* */
public class EditDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
        System.out.println(minDistance1("horse", "ros"));
        System.out.println(minDistance2("horse", "ros"));
    }

    public static int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n][m];
        for(int[] row : dp) Arrays.fill(row, -1);
        return dfs(word1, word2, n-1, m-1, dp);
    }

    private static int dfs(String word1, String word2, int i, int j, int[][] dp) {
        // Base case: when strings are over
        // case 1: word1 is over
        // if word 1 is over, but let's say we have 2 characters still to match in word 2(j = 1), then we will need at max 2 operations, that is 2 inserts to match word 1 to word 2
        if(i < 0) return j+1;
        // case 2: word2 is over
        // if word 2 is over, but let's say we have 2 characters still in word 1(i = 1), then we will need at max 2 operations, that is 2 deletes to match word 1 to word 2
        if(j < 0) return i+1;

        if(dp[i][j] != -1) return dp[i][j];

        // if the character matches we just go on and try to match the remaining characters
        if(word1.charAt(i) == word2.charAt(j)){
            return dp[i][j] = dfs(word1, word2, i-1, j-1, dp);
        }else {
            // 1. hypothetically insert a matching char
            int insert = 1 + dfs(word1, word2, i, j-1, dp);

            // 2. hypothetically delete a matching char
            int delete = 1 + dfs(word1, word2, i-1, j, dp);

            // 3. replace operation
            int replace = 1 + dfs(word1, word2, i-1, j-1, dp);

            return dp[i][j] = Math.min(replace, Math.min(insert, delete));
        }
    }

    public static int minDistance1(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n+1][m+1];
        for(int[] row : dp) Arrays.fill(row, -1);
        return dfs1(word1, word2, n, m, dp);
    }

    private static int dfs1(String word1, String word2, int i, int j, int[][] dp) {
        // Base case: when strings are over
        // case 1: word1 is over
        // if word 1 is over, but let's say we have 2 characters still to match in word 2(j = 1), then we will need at max 2 operations, that is 2 inserts to match word 1 to word 2
        if(i == 0) return j;
        // case 2: word2 is over
        // if word 2 is over, but let's say we have 2 characters still in word 1(i = 1), then we will need at max 2 operations, that is 2 deletes to match word 1 to word 2
        if(j == 0) return i;

        if(dp[i][j] != -1) return dp[i][j];

        // if the character matches we just go on and try to match the remaining characters
        if(word1.charAt(i-1) == word2.charAt(j-1)){
            return dp[i][j] = dfs1(word1, word2, i-1, j-1, dp);
        }else {
            // 1. hypothetically insert a matching char
            int insert = 1 + dfs1(word1, word2, i, j-1, dp);

            // 2. hypothetically delete a matching char
            int delete = 1 + dfs1(word1, word2, i-1, j, dp);

            // 3. replace operation
            int replace = 1 + dfs1(word1, word2, i-1, j-1, dp);

            return dp[i][j] = Math.min(replace, Math.min(insert, delete));
        }
    }

    // Tabulation
    public static int minDistance2(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n+1][m+1];

        // case 1: word1 is over
        // if word 1 is over, but let's say we have 2 characters still to match in word 2(j = 1), then we will need at max 2 operations, that is 2 inserts to match word 1 to word 2
        for(int j= 0; j<=m; j++) dp[0][j] = j;
        // case 2: word2 is over
        // if word 2 is over, but let's say we have 2 characters still in word 1(i = 1), then we will need at max 2 operations, that is 2 deletes to match word 1 to word 2
        for(int i = 0; i<=n; i++) dp[i][0] = i;

        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=m; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    // 1. hypothetically insert a matching char
                    int insert = 1 + dp[i][j-1];

                    // 2. hypothetically delete a matching char
                    int delete = 1 + dp[i-1][j];

                    // 3. replace operation
                    int replace = 1 + dp[i-1][j-1];

                    dp[i][j] = Math.min(replace, Math.min(insert, delete));
                }
            }
        }
        return dp[n][m];
    }
}
