package org.ayush.datastructure;

public class Trie2 {
    private TrieNode2 root;

    public Trie2() {
        //Write your code here
        root = new TrieNode2();
    }

    public void insert(String word) {
        // Write your code here.
        //Write your code here
        TrieNode2 node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!node.containsKey(c)){
                node.put(c, new TrieNode2());
            }
            node.setCountPrefix(node.getCountPrefix()+1);
            node = node.getNode(c); // move to the next trie node
        }
        node.setEndsWith(node.getEndsWith()+1);
    }

    public int countWordsEqualTo(String word) {
        // Write your code here.
        TrieNode2 node = root;
        for (int i = 0; i < word.length(); i++) {
            if(!node.containsKey(word.charAt(i))){
                return 0;
            }
            node = node.getNode(word.charAt(i));
        }
        return node.getEndsWith();
    }

    public int countWordsStartingWith(String prefix) {
        // Write your code here.
        TrieNode2 node = root;
        for (int i = 0; i < prefix.length(); i++) {
            if(!node.containsKey(prefix.charAt(i))){
                return 0;
            }
            node = node.getNode(prefix.charAt(i));
        }
        return node.getCountPrefix();
    }

    public void erase(String word) {
        // Write your code here.
        TrieNode2 node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            node.setCountPrefix(node.getCountPrefix()-1); // as soon as we found a trie node we reduce the prefix count, indicating the letter has been erased
            node = node.getNode(c); // move to the next trie node
        }
        node.setEndsWith(node.getEndsWith()-1); // as soon as we found a end trie node we reduce the endwith count, indicating the letter has been erased
    }
}
