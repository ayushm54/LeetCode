package org.ayush.problemsolving.recursion;

import java.util.ArrayList;
import java.util.List;

/*
* Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).



Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false


Constraints:

0 <= s.length <= 100
0 <= t.length <= 104
s and t consist only of lowercase English letters.


Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?
* */
public class IsSubsequence {
    public static void main(String[] args) {
        System.out.println(isSubsequence("abc", "ahbgdc"));
        System.out.println(isSubsequence("axc", "ahbgdc"));
        System.out.println(isSubsequence("", "ahbgdc"));
    }
    public static boolean isSubsequence(String s, String t) {
        List<String> subsequences = new ArrayList<>();
        isSubsequence(s, 0, new ArrayList<>(), subsequences, t);
        return subsequences.contains(s);
    }

    public static void isSubsequence(String s, int i, List<Character> currentSequence, List<String> subsequences, String t){
        String seq = !currentSequence.isEmpty() ?
                currentSequence.stream()
                .map(e->e.toString())
                .reduce((acc, e) -> acc  + e)
                .get()
                : "";
        if(s.equals(seq)) {
            subsequences.add(s);
            return ;
        }
        if(i >= t.length()){
            return;
        }
        // Take the element and move forward, this will start one recursion tree considering the current element as part of the subset
        currentSequence.add(t.charAt(i));
        isSubsequence(s, i+1, currentSequence, subsequences, t);

        // Do not take the element but move forward, this will start another recursion tree by skipping the current element as part of the subset
        currentSequence.remove(currentSequence.size()-1);
        isSubsequence(s, i+1, currentSequence, subsequences, t);
    }

}
