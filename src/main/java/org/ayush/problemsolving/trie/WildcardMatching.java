package org.ayush.problemsolving.trie;

import java.util.Arrays;

/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).



Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.


Constraints:

0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.
* */
public class WildcardMatching {

    static class Node {
        public Node[] children;
        public boolean isWord;

        public Node() {
            this.children = new Node[26]; // store letters a-z
            this.isWord = false;
        }

        public void setEnd() {
            isWord = true;
        }

        public boolean isEnd() {
            return isWord;
        }

        public boolean containsKey(char c) {
            return children[c - 'a'] != null; // 'a' is at index 0;
        }

        public Node getNode(char c) {
            return children[c - 'a'];
        }

        public Node getNonNullChild() {
            for (Node child : this.children) {
                if(child != null) return child;
            }
            return null;
        }
    }

    public void addWord(String word,  Node root) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(node.children[c-'a'] == null){
                node.children[c-'a'] = new Node();
            }
            node = node.children[c-'a']; // move to the next trie node
        }
        node.setEnd();
    }

    public static void main(String[] args) {
        /*WildcardMatching wm = new WildcardMatching();
        System.out.println(wm.isMatch("aa", "a"));
        System.out.println(wm.isMatch("aa", "*"));
        System.out.println(wm.isMatch("aa", "?a"));
        System.out.println(wm.isMatch("acdcb", "a*c?b"));
        System.out.println(wm.isMatch("adceb", "*a*b"));*/

        System.out.println(isMatch1("aa", "a"));
        System.out.println(isMatch1("aa", "*"));
        System.out.println(isMatch1("aa", "?a"));
        System.out.println(isMatch1("acdcb", "a*c?b"));
        System.out.println(isMatch1("adceb", "*a*b"));

        System.out.println(isMatch2("aa", "a"));
        System.out.println(isMatch2("aa", "*"));
        System.out.println(isMatch2("aa", "?a"));
        System.out.println(isMatch2("acdcb", "a*c?b"));
        System.out.println(isMatch2("adceb", "*a*b"));
    }

    public boolean isMatch(String s, String p) {
        Node root = new Node();
        addWord(s, root);
        if(p.equals("*")) return true;
        return search(0, root, p);
    }

    private boolean search(int idx, Node node, String p) {
        Node cur = node;
        int i = idx;
        while(i < p.length()){
            char c = p.charAt(i);
            if(c == '*') {
                Node temp = cur;
                if(i<p.length() - 1 && (!temp.containsKey(p.charAt(i+1)) || i!=0)) {
                    boolean found = false;
                    while(!found){
                        if (temp.containsKey(p.charAt(i + 1))) {
                            found = true;
                        }else {
                            temp = temp.getNonNullChild();
                        }
                    }
                    cur = temp;
                }
            }
            else if(c == '?') {
                // we will skip the '?' at index i and recursively search each child tree (dfs)
                // and if any subtree results in a solution we will return true else we will explore other childrens
                for(int j=0; j<cur.children.length; j++){
                    if(cur.children[j] != null && search(i+1, cur.children[j], p)) return true;
                }
            } else {
                if(!cur.containsKey(c)) return false;
                else {
                    cur = cur.getNode(c);
                }
            }
            i++;
        }
        return cur.isEnd();
    }

    // Recursion with DP
    public static boolean isMatch1(String s, String p) {
        int n = s.length(), m = p.length();
        int[][] dp = new int[n + 1][m + 1];
        for(int[] row : dp) Arrays.fill(row, -1);
        if(dfs(s, p, n, m, dp) == 1) return true;
        return false;
    }

    private static int dfs(String s, String p, int i , int j, int[][] dp) {
        // Base case: when strings are over
        // If pattern is over and the string as well, then we found a match
        if(i == 0 && j == 0) return 1;
        // If pattern is not over but the string is then we need to check for *
        if(i == 0 && j > 0) {
            // check if all the remaining characters in pattern are *
            for(int k = 0; k<j; k++){
                if(p.charAt(k) != '*') return 0;
            }
            return 1;
        }
        // if pattern is over but string is not
        if(i > 0 && j == 0) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        // MATCH: char at i == j or char at j = ? then 1 character is matched
        if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
            return dp[i][j] = dfs(s, p, i-1, j-1, dp);
        }
        // NON MATCH
        // We can do two things here:
        // 1. consider star is not matching anything and move forward with other characters in pattern
        // 2. match one character from s with start, keep the pointer on * but move ahead in string s
        if(p.charAt(j-1) == '*') return dp[i][j] = (dfs(s, p, i, j-1, dp) == 1 || dfs(s, p, i-1, j, dp) == 1) ? 1: 0;

        return 0;
    }

    // Tabulation
    public static boolean isMatch2(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];

        // Base case: when strings are over
        // If pattern is over and the string as well, then we found a match
        dp[0][0] = true;

        // if pattern is over but string is not if(i > 0 && j == 0) return 0, this is impicit as boolean array sis initialized to false

        // If pattern is not over but the string is then we need to check for *
        for (int j = 1; j <= m; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = true;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // MATCH: char at i == j or char at j = ? then 1 character is matched
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // NON MATCH
                // We can do two things here:
                // 1. consider star is not matching anything and move forward with other characters in pattern
                // 2. match one character from s with start, keep the pointer on * but move ahead in string s
                else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else{
                    dp[i][j] = false;
                }
            }
        }
        return dp[n][m];
    }
}