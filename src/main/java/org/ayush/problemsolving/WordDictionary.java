package org.ayush.problemsolving;

import org.ayush.datastructure.TrieNode1;

/*
* Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.


Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True


Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 2 dots in word for search queries.
At most 104 calls will be made to addWord and search.
* */
public class WordDictionary {
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
    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
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

    public boolean search(String word) {
        return search(0, root, word);
    }

    private boolean search(int idx, Node node, String word) {
        Node cur = node;
        for (int i = idx; i < word.length(); i++) {
            char c = word.charAt(i);
            if(c=='.'){
                for(int j=0; j<node.children.length; j++){
                    // we will skip the '.' at index i and recursively search each child tree (dfs)
                    // and if any subtree results in a solution we will return true else we will explore other childrens
                    if (cur.children[j]!=null && search(i+1, cur.children[j], word)) {
                        return true;
                    }
                }
                return false; // No match found
            }else {
                if(!cur.containsKey(c)) return false;
                else {
                    cur = cur.getNode(c);
                }
            }
        }
        return cur.isEnd(); // if the recursion does not kicks in and there is no '.' then after the loop is cur is the end its a match
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True
    }
}
