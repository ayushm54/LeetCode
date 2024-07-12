package org.ayush.problemsolving;

import org.ayush.datastructure.Trie1;
import org.ayush.datastructure.TrieNode1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
* Given a string 'S', you are supposed to return the number of distinct substrings(including empty substring) of the given string. You should implement the program using a trie.

Note :
A string ‘B’ is a substring of a string ‘A’ if ‘B’ that can be obtained by deletion of, several characters(possibly none) from the start of ‘A’ and several characters(possibly none) from the end of ‘A’.

Two strings ‘X’ and ‘Y’ are considered different if there is at least one index ‘i’  such that the character of ‘X’ at index ‘i’ is different from the character of ‘Y’ at index ‘i’(X[i]!=Y[i]).
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
1 <= T <= 5
1 <= |S| <= 10^3

‘S’ contains only lowercase English letters.

Time Limit: 1 sec
Sample Input 1 :
2
sds
abc
Sample Output 1 :
6
7
Explanation of Sample Input 1 :
In the first test case, the 6 distinct substrings are { ‘s’,’ d’, ”sd”, ”ds”, ”sds”, “” }

In the second test case, the 7 distinct substrings are {‘a’, ‘b’, ‘c’, “ab”, “bc”, “abc”, “” }.
Sample Input 2 :
2
aa
abab
Sample Output 2 :
3
8
Explanation of Sample Input 2 :
In the first test case, the two distinct substrings are {‘a’, “aa”, “” }.

In the second test case, the seven distinct substrings are {‘a’, ‘b’, “ab”, “ba”, “aba”, “bab”, “abab”, “” }

Hints:
1. Can you think about a data structure that can be used to store the distinct substrings?
2. Can you think about using the fact that every substring of ‘S’ is a prefix of some suffix string of ‘S’?
3. Try to insert every suffix of the string in Trie.

* */
public class CountDistinctSubstrings {
    public static void main(String[] args) {
        System.out.println(countDistinctSubstrings("sds"));
        System.out.println(countDistinctSubstrings("abc"));
        System.out.println(countDistinctSubstrings("aa"));
        System.out.println(countDistinctSubstrings("abab"));

        System.out.println("using countDistinctSubstrings2: ");

        System.out.println(countDistinctSubstrings2("sds"));
        System.out.println(countDistinctSubstrings2("abc"));
        System.out.println(countDistinctSubstrings2("aa"));
        System.out.println(countDistinctSubstrings2("abab"));

    }

    // here we inserted the whole string in the trie and then running loop on each char to count substrinds
    public static int countDistinctSubstrings(String s) {
        Trie1 trie = new Trie1();
        trie.insert(s);
        return trie.countDistinctSubstrings(s);
        /*Set<String> subsets = new HashSet<>();
        getSubsets(0, subsets, "", s);
        System.out.println(subsets);
        return subsets.size();*/
    }

    // Here we will loop over the string and insert the substring starting with current character
    public static int countDistinctSubstrings2(String s) {
        int count = 0;
        TrieNode1 root = new TrieNode1();
        for (int i = 0; i < s.length(); i++) {
            TrieNode1 node = root;
            for (int j = i; j < s.length(); j++) {
                if(!node.containsKey(s.charAt(j))){
                    node.put(s.charAt(j), new TrieNode1());
                    count++;
                }
                node = node.getNode(s.charAt(j));
            }
        }
        return count+1; // +1 for empty string
    }


    /*
     * At every call we have two choices:
     *   1. Take the current element and move forward
     *   2. Do not take the current element but move forward
     * */
    /*public static void getSubsets(int i, Set<String> subsets, String substr, String s){
        if(i >= s.length()){
            if(!subsets.contains(substr)){
                subsets.add(substr);
            }
            return;
        }
        // Take the element and move forward, this will start one recursion tree considering the current element as part of the subset
        substr += s.charAt(i);
        getSubsets(i+1, subsets, substr, s);

        // Do not take the element but move forward, this will start another recursion tree by skipping the current element as part of the subset
        substr = substr.substring(0, substr.length()-1);
        getSubsets(i+1, subsets, substr, s);
    }*/
}
