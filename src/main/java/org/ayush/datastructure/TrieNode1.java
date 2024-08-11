package org.ayush.datastructure;

import org.ayush.problemsolving.trie.WildcardMatching;

public class TrieNode1 {
    private TrieNode1[] children;
    private boolean isWord;

    public TrieNode1() {
        this.children = new TrieNode1[26]; // store letters a-z
        this.isWord = false;
    }

    public boolean containsKey(char c) {
        return children[c - 'a'] != null; // 'a' is at index 0;
    }

    public TrieNode1 getNode(char c) {
        return children[c - 'a'];
    }

    public void put(char c, TrieNode1 node) {
        children[c - 'a'] = node;
    }

    public void setEnd() {
        isWord = true;
    }

    public boolean isEnd() {
        return isWord;
    }

    public TrieNode1 getNonNullChild() {
        for (TrieNode1 child : this.children) {
            if(child != null) return child;
        }
        return null;
    }
}
