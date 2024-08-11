package org.ayush.problemsolving.dynamicProgramming;

import java.util.Arrays;

/*
Given two strings, 'S' and 'T' with lengths 'M' and 'N', find the length of the 'Longest Common Subsequence'.
For a string 'str'(per se) of length K, the subsequences are the strings containing characters in the same relative order as they are present in 'str,'
but not necessarily contiguous. Subsequences contain all the strings of length varying from 0 to K.

Example :
Subsequences of string "abc" are:  ""(empty string), a, b, c, ab, bc, ac, abc.
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
0 <= M <= 10 ^ 3
0 <= N <= 10 ^ 3

Time Limit: 1 sec
Sample Input 1 :
adebc
dcadb
Sample Output 1 :
3
Explanation of the Sample Output 1 :
Both the strings contain a common subsequence 'adb', which is the longest common subsequence with length 3.
Sample Input 2 :
ab
defg
Sample Output 2 :
0
Explanation of the Sample Output 2 :
The only subsequence that is common to both the given strings is an empty string("") of length 0.
* */
public class LongestCommonSubSequence {

    public static void main(String[] args) {
        System.out.println(lcs("adebc", "dcadb"));
        System.out.println(lcs("ab", "defg"));
        System.out.println(lcs("abcde", "ace"));

        System.out.println(lcs1("adebc", "dcadb"));
        System.out.println(lcs1("ab", "defg"));
        System.out.println(lcs1("abcde", "ace"));

        System.out.println(lcs2("adebc", "dcadb"));
        System.out.println(lcs2("ab", "defg"));
        System.out.println(lcs2("abcde", "ace"));
    }

    public static int lcs(String s, String t) {
        int [][]dp = new int[s.length()][t.length()];
        for(int[] row : dp) Arrays.fill(row, -1);
        //Your code goes here
        return dfs(s.length()-1, t.length()-1, s, t, dp);
    }

    /*
        We express the problem in terms of indexes of both the strings
        Here we are saying find the lcs of string s and t for the length 0 to ids and 0 to idt
        there will be two cases MATCH and NON-MATCH
    */
    private static int dfs(int ids, int idt, String s, String t, int [][]dp) {
        if(ids < 0 || idt < 0) return 0;

        if(dp[ids][idt] != -1) return dp[ids][idt];
        // if the character matches at an index, this is a match case
        if(s.charAt(ids) == t.charAt(idt)) {
            return  dp[ids][idt] = 1 + dfs(ids-1, idt-1, s, t, dp);
        }

        // if not a match, we will have to explore by going down n both the strings one by one
        int nonMatch = Math.max(dfs(ids-1,idt,s,t, dp), dfs(ids, idt-1, s, t, dp));
        return dp[ids][idt] = nonMatch;
    }

    // We can pass n and m instead of n-1 and m-1, this is called shifting of indexes, but now we have to change the base case also
    // SHifting will help uin writing base case for tabulation
    public static int lcs1(String s, String t) {
        int [][]dp = new int[s.length()+1][t.length()+1];
        for(int[] row : dp) Arrays.fill(row, -1);
        //Your code goes here
        return dfs1(s.length(), t.length(), s, t, dp);
    }

    /*
        We express the problem in terms of indexes of both the strings
        Here we are saying find the lcs of string s and t for the length 0 to ids and 0 to idt
        there will be two cases MATCH and NON-MATCH
    */
    private static int dfs1(int ids, int idt, String s, String t, int [][]dp) {
        if(ids == 0 || idt == 0) return 0;

        if(dp[ids][idt] != -1) return dp[ids][idt];
        // if the character matches at an index, this is a match case
        if(s.charAt(ids-1) == t.charAt(idt-1)) {
            return  dp[ids][idt] = 1 + dfs1(ids-1, idt-1, s, t, dp);
        }

        // if not a match, we will have to explore by going down n both the strings one by one
        int nonMatch = Math.max(dfs1(ids-1,idt,s,t, dp), dfs1(ids, idt-1, s, t, dp));
        return dp[ids][idt] = nonMatch;
    }

    // Tabulation
    public static int lcs2(String s, String t) {
        int [][]dp = new int[s.length()+1][t.length()+1];
        /*// Base case, we dont need to write this loops as already the dp is initialized by 0
        for(int j = 0; j < t.length()+1; j++) dp[0][j] = 0; // i == 0
        for(int i = 0; i < s.length()+1; i++) dp[i][0] = 0; // j == 0
        */
        for(int i = 1; i < s.length()+1; i++) {
            for(int j = 1; j < t.length()+1; j++) {
                // match
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    // if not a match, we will have to explore by going down n both the strings one by one
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        //Your code goes here
        return dp[s.length()][t.length()];
    }
}
