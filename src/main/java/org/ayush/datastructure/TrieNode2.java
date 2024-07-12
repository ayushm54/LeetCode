package org.ayush.datastructure;

public class TrieNode2 {
    private TrieNode2[] children;
    private int endWith;
    private int countPrefix;

    public TrieNode2() {
        this.children = new TrieNode2[26]; // store letters a-z
        this.endWith = 0;
        this.countPrefix = 0;
    }

    public boolean containsKey(char c) {
        return children[c - 'a'] != null; // 'a' is at index 0;
    }

    public TrieNode2 getNode(char c) {
        return children[c - 'a'];
    }

    public void put(char c, TrieNode2 node) {
        children[c - 'a'] = node;
    }

    public void setEndsWith(int endWith){
        this.endWith = endWith;
    }

    public int  getEndsWith(){
        return this.endWith;
    }

    public void setCountPrefix(int cp){
        this.countPrefix = cp;
    }

    public int  getCountPrefix(){
        return this.countPrefix;
    }
}
