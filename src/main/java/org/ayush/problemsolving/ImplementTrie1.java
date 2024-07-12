package org.ayush.problemsolving;

import org.ayush.datastructure.Trie1;
import org.ayush.datastructure.TrieNode1;

/*
* A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.


Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True


Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 104 calls in total will be made to insert, search, and startsWith.
* */
public class ImplementTrie1 {
    public static void main(String[] args) {
       Trie1 trie = new Trie1();
        trie.insert("apple");
        trie.insert("appl");
        trie.insert("ap");
        trie.insert("bat");

        System.out.println(trie.search("apple"));
        System.out.println(trie.search("appl"));
        System.out.println(trie.search("appx"));
        System.out.println(trie.startsWith("ba"));
    }
}