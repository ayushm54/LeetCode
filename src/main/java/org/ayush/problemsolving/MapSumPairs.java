package org.ayush.problemsolving;

import org.ayush.datastructure.TrieNode1;

import java.util.HashMap;
import java.util.Map;

/*
* Design a map that allows you to do the following:

Maps a string key to a given value.
Returns the sum of the values that have a key with a prefix equal to a given string.
Implement the MapSum class:

MapSum() Initializes the MapSum object.
void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.


Example 1:

Input
["MapSum", "insert", "sum", "insert", "sum"]
[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
Output
[null, null, 3, null, 5]

Explanation
MapSum mapSum = new MapSum();
mapSum.insert("apple", 3);
mapSum.sum("ap");           // return 3 (apple = 3)
mapSum.insert("app", 2);
mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)


Constraints:

1 <= key.length, prefix.length <= 50
key and prefix consist of only lowercase English letters.
1 <= val <= 1000
At most 50 calls will be made to insert and sum.
* */
public class MapSumPairs {
    static class Node {
        Map<Character, Node> children = new HashMap<>();
        int sum;
    }

    Map<String, Integer> map;
    Node root;

    MapSumPairs(){
        root = new Node();
        map = new HashMap<>();
    }

    public void insert(String key, int value) {
        int delta = value - map.getOrDefault(key, 0); // saving the delta if the string already is in the map
        map.put(key, value); // replacing the value for the key with current value
        Node cur = root;
        // we will add the delta in each node to match it with the value inputed to the function
        cur.sum += delta;
        for (Character c : key.toCharArray()) {
            cur.children.putIfAbsent(c, new Node());
            cur = cur.children.get(c);
            cur.sum += delta;
        }
    }

    public int sum(String prefix) {
        Node cur = root;
        for (char c: prefix.toCharArray()) {
            cur = cur.children.get(c);
            if (cur == null) return 0;
        }
        return cur.sum;
    }

    public static void main(String[] args) {
        MapSumPairs mapSumPairs = new MapSumPairs();
        mapSumPairs.insert("apple", 3);
        System.out.println(mapSumPairs.sum("ap"));
        mapSumPairs.insert("app", 2);
        System.out.println(mapSumPairs.sum("ap"));

        // using trie without map
        Trie trie = new Trie();
        trie.insert("apple", 3);
        System.out.println(trie.sum("ap"));
        trie.insert("app", 2);
        System.out.println(trie.sum("ap"));
    }
}

class Node {
    Node[] children;
    int sum;
    public Node() {
        children = new Node[26];
        sum = 0;
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

    public void setSum(int sum) { this.sum = sum; }

    public int getSum(){ return sum; }
}

class Trie {
    Node root;
    Map<String, Integer> map;
    public Trie() {
        root = new Node();
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        Node cur = root;
        cur.sum += delta;
        for (Character c : key.toCharArray()) {
            if(!cur.containsKey(c)){
                cur.put(c, new Node());
            }
            cur = cur.getNode(c);
            cur.sum += delta;
        }
    }

    public int sum(String prefix) {
        Node cur = root;
        for (char c: prefix.toCharArray()) {
            cur = cur.getNode(c);
            if (cur == null) return 0;
        }
        return cur.sum;
    }
}
