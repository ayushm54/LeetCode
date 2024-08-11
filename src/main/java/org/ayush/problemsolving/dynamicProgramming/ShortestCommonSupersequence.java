package org.ayush.problemsolving.dynamicProgramming;

import java.util.Arrays;

/*
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.
A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.

Example 1:
Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation:
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.

Example 2:
Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"

Constraints:
1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.
* */
public class ShortestCommonSupersequence {
    public static void main(String[] args) {
        System.out.println(shortestCommonSupersequence("abac", "cab"));
        System.out.println(shortestCommonSupersequence("brute", "groot"));
    }

    // ans = lesn(s) + len(t) - lcs(s, t)
    // example: s = brute, t = groot, lcs = rt
    // ans = 5 + 5 - 2 = 8 and the string will be "bgruoote"
    // we can use the lcs tabulation code and can form the string
    public static String shortestCommonSupersequence(String s, String t) {
        int [][]dp = new int[s.length()+1][t.length()+1];
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
        // Now dp will have all the calculated lcs, we will use dp to form the string
        String ans = "";
        int i = s.length(), j = t.length();
        while(i > 0 && j > 0) {
            if(s.charAt(i-1) == t.charAt(j-1)) {
                ans += s.charAt(i-1);
                i--;
                j--;
            } else if (dp[i-1][j] > dp[i][j-1]) {
                ans += s.charAt(i-1);
                i--;
            }else{
                ans += t.charAt(j-1);
                j--;
            }
        }

        while(i > 0) {
            ans += s.charAt(i-1);
            i--;
        }
        while(j > 0) {
            ans += t.charAt(j-1);
            j--;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ans);
        return stringBuilder.reverse().toString();
    }
}
