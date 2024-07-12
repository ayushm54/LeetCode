package org.ayush.problemsolving;

import java.util.Arrays;
import java.util.List;
/*
* n English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word derivative. For example, when the root "help" is followed by the word "ful", we can form a derivative "helpful".

Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the derivatives in the sentence with the root forming it. If a derivative can be replaced by more than one root, replace it with the root that has the shortest length.

Return the sentence after the replacement.



Example 1:

Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
Example 2:

Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
Output: "a a b c"


Constraints:

1 <= dictionary.length <= 1000
1 <= dictionary[i].length <= 100
dictionary[i] consists of only lower-case letters.
1 <= sentence.length <= 106
sentence consists of only lower-case letters and spaces.
The number of words in sentence is in the range [1, 1000]
The length of each word in sentence is in the range [1, 1000]
Every two consecutive words in sentence will be separated by exactly one space.
sentence does not have leading or trailing spaces.
* */
public class ReplaceWords {
    Node root = new Node();

    static class Node {
        private Node[] children;
        private boolean isWord;

        public Node() {
            this.children = new Node[26]; // store letters a-z
            this.isWord = false;
        }

        public boolean containsKey(char c) {
            return children[c - 'a'] != null; // 'a' is at index 0;
        }

        public Node getNode(char c) {
            return children[c - 'a'];
        }

        public void put(char c, Node node) {
            children[c - 'a'] = node;
        }

        public void setEnd() {
            isWord = true;
        }

        public boolean isEnd() {
            return isWord;
        }
    }

    public static void main(String[] args) {
        ReplaceWords rp = new ReplaceWords();
        System.out.println(rp.replaceWords(Arrays.asList("cat","bat","rat"), "the cattle was rattled by the battery"));

        rp = new ReplaceWords();
        System.out.println(rp.replaceWords(Arrays.asList("a","b","c"), "aadsfasf absbs bbab cadsfafs"));

        rp = new ReplaceWords();
        System.out.println(rp.replaceWords(Arrays.asList("a", "aa", "aaa", "aaaa"), "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"));
    }

    public void insert(String word){
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!node.containsKey(c)){
                node.put(c, new Node());
            }
            node = node.getNode(c); // move to the next trie node
        }
        node.setEnd();
    }

    public String findPrefix(String word){
        Node node = root;
        String prefix = "";
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!node.containsKey(c)){
                break;
            }
            prefix += String.valueOf(c);
            node = node.getNode(c);
            if(node.isEnd()){
                break;
            }
        }
        if(node.isEnd()){
            return prefix;
        }
        return word;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        for (String word : dictionary) {
            insert(word);
        }
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String prefix = findPrefix(words[i]);
            words[i] = prefix;
        }
        return String.join(" ", words);
    }
}
