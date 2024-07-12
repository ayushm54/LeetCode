package org.ayush.datastructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Trie1 {
    private TrieNode1 root;

    public Trie1(){
        root = new TrieNode1(); // root is empty initially
    }

    //Inserts a word into the trie
    public void insert(String word) {
        TrieNode1 node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!node.containsKey(c)){
                node.put(c, new TrieNode1());
            }
            node = node.getNode(c); // move to the next trie node
        }
        node.setEnd();
    }

    //Returns if the word is in the trie
    public boolean search(String word) {
        TrieNode1 node = root;
        for (int i = 0; i < word.length(); i++) {
            if(!node.containsKey(word.charAt(i))){
                return false;
            }
            node = node.getNode(word.charAt(i));
        }
        return node.isEnd();
    }


    //Returns if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode1 node = root;
        for (int i = 0; i < prefix.length(); i++) {
            if(!node.containsKey(prefix.charAt(i))){
                return false;
            }
            node = node.getNode(prefix.charAt(i));
        }
        return true;
    }

    public int countDistinctSubstrings(String word) {
        TrieNode1 node = root;
        Set<String> distinctSubstrings = new HashSet<>();
        distinctSubstrings.add("");
        for (int i = 0; i < word.length(); i++) {
            String substr = word.substring(i, i + 1);
            if(!distinctSubstrings.contains(substr)){
                distinctSubstrings.add(substr);
            }
            if(node.containsKey(word.charAt(i))){
                node = node.getNode(word.charAt(i));
                TrieNode1 node1 = node;
                for (int j = i+1; j < word.length(); j++){
                    substr += word.substring(j, j + 1);
                    if(!distinctSubstrings.contains(substr)){
                        distinctSubstrings.add(substr);
                    }
                    node1 = node1.getNode(word.charAt(j));
                }
            }
        }
       return distinctSubstrings.size();
    }
}
