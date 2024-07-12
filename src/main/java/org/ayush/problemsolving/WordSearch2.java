package org.ayush.problemsolving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
* Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



Example 1:


Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
Example 2:


Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []


Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.
* */
public class WordSearch2 {
    Node root = new Node();

    static class Node {
        private Node[] children;
        private boolean isWord;

        public Node() {
            this.children = new Node[26]; // store letters a-z
            this.isWord = false;
        }

        public void setEnd() {
            isWord = true;
        }

        public boolean isEnd() {
            return isWord;
        }
    }

    public void addWord(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(node.children[c-'a'] == null){
                node.children[c-'a'] = new Node();
            }
            node = node.children[c-'a']; // move to the next trie node
        }
        node.setEnd();
    }

    public static void main(String[] args) {
        WordSearch2 wordSearch = new WordSearch2();
        System.out.println(wordSearch.findWords(new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}}, new String[]{"oath","pea","eat","rain"}));
    }
    public List<String> findWords(char[][] board, String[] words) {
        for(String word : words){
            addWord(word);
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        Set<String> res = new HashSet<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                search(board, "", visited, i, j, res, root);
            }
        }
        return  new ArrayList<>(res);
    }

    private void search(char[][] board, String word, boolean[][] visited, int row, int col, Set<String> res, Node node) {
        if(row<0 || col <0
                || row>=board.length || col>=board[0].length
                || visited[row][col]) return; // We cannot use already visited position
        char c = board[row][col];
        if(node.children[c-'a']==null) return;
        visited[row][col] = true;
        node = node.children[c-'a'];
        word += board[row][col];
        if(node.isEnd()){
            res.add(word);
        }
        search(board, word, visited, row+1, col, res, node);
        search(board, word, visited, row-1, col, res, node);
        search(board, word, visited, row, col+1, res, node);
        search(board, word, visited, row, col-1, res, node);
        visited[row][col] = false;
    }
}
