package org.ayush.problemsolving;

import java.util.HashMap;
import java.util.Map;

/*
* Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".



Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.


Constraints:

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lowercase English letters.
* */
public class LongestCommonPrefix {
    static class Node {
        private Map<Character, Node> children;
        private boolean isWord;

        public Node() {
            this.children = new HashMap<>(); // store letters a-z
            this.isWord = false;
        }
    }

    Node root;

    LongestCommonPrefix(){
        root = new Node();
    }

    public String addWord(String word) {
        Node node = root;
        boolean prefixFound = false;
        String prefix = "";
        if(word.length() == 0){
            node.children.put('#', new Node());
            node.isWord = true;
            return prefix;
        }
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!node.children.containsKey(c)){
                node.children.put(c, new Node());
            }
            if(!prefixFound && node.isWord) {
                prefixFound = true;
            }else if(node.children.size() > 1){
                prefixFound = true;
            } else if(!prefixFound && node.children.size() == 1){
                prefix += c;
            }
            node = node.children.get(c); // move to the next trie node
        }
        node.isWord = true;
        return prefix;
    }

    public static void main(String[] args) {
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        System.out.println(longestCommonPrefix.longestCommonPrefix(new String[]{"flower","flow","flight"}));
        longestCommonPrefix = new LongestCommonPrefix();
        System.out.println(longestCommonPrefix.longestCommonPrefix(new String[]{"dog","racecar","car"}));
        longestCommonPrefix = new LongestCommonPrefix();
        System.out.println(longestCommonPrefix.longestCommonPrefix(new String[]{"","b"}));
        longestCommonPrefix = new LongestCommonPrefix();
        System.out.println(longestCommonPrefix.longestCommonPrefix(new String[]{"aaa","aa","aaa"}));

        longestCommonPrefix = new LongestCommonPrefix();
        System.out.println(longestCommonPrefix.longestCommonPrefix2(new String[]{"flower","flow","flight"}));
        System.out.println(longestCommonPrefix.longestCommonPrefix2(new String[]{"dog","racecar","car"}));
        System.out.println(longestCommonPrefix.longestCommonPrefix2(new String[]{"aaa","aa","aaa"}));
        System.out.println(longestCommonPrefix.longestCommonPrefix2(new String[]{"","b"}));

        longestCommonPrefix = new LongestCommonPrefix();
        System.out.println(longestCommonPrefix.longestCommonPrefix3(new String[]{"flower","flow","flight"}));
        System.out.println(longestCommonPrefix.longestCommonPrefix3(new String[]{"dog","racecar","car"}));
        System.out.println(longestCommonPrefix.longestCommonPrefix3(new String[]{"aaa","aa","aaa"}));
        System.out.println(longestCommonPrefix.longestCommonPrefix3(new String[]{"","b"}));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        String prefix = "";
        for(String s : strs){
            prefix = addWord(s);
        }
        return prefix;
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        String prefix = "";
        for (int i = 0; i < strs[0].length(); i++) {
            for(String s: strs){
                if(i == s.length() || s.charAt(i) != strs[0].charAt(i)){
                    return prefix;
                }
            }
            prefix+=strs[0].charAt(i);
        }
        return prefix;
    }

    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (String s : strs)
            while (s.indexOf(prefix) != 0)
                prefix = prefix.substring(0, prefix.length() - 1);
        return prefix;
    }
}
