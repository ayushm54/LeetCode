package org.ayush.problemsolving.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/*
* Given a string s, find the length of the longest
substring
 without repeating characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
* */
public class LongestSubstrWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("abbcacbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring(" "));
        System.out.println(lengthOfLongestSubstring(""));
        System.out.println(lengthOfLongestSubstring("c"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int ans = 0;
        if(s.length() == 0) return ans;
        if(s.trim().length()==0 || s.length() == 1) return 1;
        for (int i = 0; i < s.length()-1; i++) {
            int j = i+1;
            while(j < s.length() && s.substring(i, j).indexOf(s.charAt(j))==-1) {
                j+=1;
            }
            ans = Math.max(ans, j-i);
        }
        return ans;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int maxlens = 0;
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int n = s.length();

        while (right < n) {
            char currentChar = s.charAt(right);
            if (map.containsKey(currentChar)) {
                left = Math.max(map.get(currentChar) + 1, left);
            }
            map.put(currentChar, right);
            maxlens = Math.max(maxlens, right - left + 1);
            right++;
        }
        return maxlens;
    }
}