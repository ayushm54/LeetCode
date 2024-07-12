package org.ayush.problemsolving;

import java.util.ArrayList;
import java.util.Arrays;

/*
* Problem statement
You are given two arrays of non-negative integers say ‘arr1’ and ‘arr2’. Your task is to find the maximum value of ( ‘A’ xor ‘B’ ) where ‘A’ and ‘B’ are any elements from ‘arr1’ and ‘arr2’ respectively and ‘xor’ represents the bitwise xor operation.

Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <=  T  <= 5
1 <=  N, M <= 1000
0 <=  arr1[i], arr2[i]  <= 10 ^ 9

Where 'T' denotes the number of test cases, 'N', ‘M’ denotes the number of elements in the first array and second array, ‘arr1[i]', and ‘arr2[i]’ denotes the 'i-th' element of the first array and second array.

Time limit: 1 sec
Sample Input 1:
1
7 7
6 6 0 6 8 5 6
1 7 1 7 8 0 2
Sample Output 1:
15
Explanation of sample input 1:
First testcase:
Possible pairs are (6, 7), (6, 8), (6, 2), (8, 7), (8, 8), (6, 2). And 8 xor 7 will give the maximum result i.e. 15
Sample Input 2:
1
3 3
25 10 2
8 5 3
Sample Output 2:
28
Explanation of sample input 2:
First test case:
28 is the maximum possible xor given by pair = (25, 5). It is the maximum possible xor among all possible pairs.
* */
public class MaximumXOR {
    static class Node {
        private Node[] children;

        public Node() {
            this.children = new Node[2]; // store letters binary bits, 0,1
        }

        public boolean containsKey(int bit) {
            return children[bit] != null; // 'a' is at index 0;
        }

        public Node getNode(int bit) {
            return children[bit];
        }

        public void put(int bit, Node node) {
            children[bit] = node;
        }
    }

    static class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(int num) {
            // We will insert the number as binary in the trie in a 32 bit format as int in java is 32 bit long
            // example num = 2, binary = 00000000000000000000000000000100, here the indexing is as belo:
            // 0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  1  0  0
            // 31 30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9  8  7  6  5  4  3  2  1  0
            // here we can see that the 1st index is 31 and the last is 0
            // so we need to insert it from left to right, 31 to 0
            Node node = this.root;
            for(int i = 31; i>=0; i--){
                // we are right shifting the number by i and doing & with 1 to check if the ith bit is 1 or not
                // if the bit is ) we will not insert in trie treating it a null
                int bit = (num >> i) & 1;
                if(!node.containsKey(bit)){
                    node.put(bit, new Node());
                }
                node = node.getNode(bit);
            }
        }

        public int getMaxXOR(int num) {
            Node node = this.root;
            int maxXor = 0;
            for(int i = 31; i>=0; i--) {
                int bit = (num >> i) & 1; // this gives the ith bit
                // Now we need to check if for this bit we have a opposite bit in the trie, becuase xor will be max if the bits are compliment
                // XOR: 1^1 = 0, 0^0 = 0, 1^0 = 1, 0^1 = 1
                if(node.containsKey(1 - bit)){
                    maxXor = maxXor | (1<<i); //  We are left shifting the binary representation of 1 to the current bit index and then adding to current max
                    node = node.getNode(1-bit);
                }else{
                    node = node.getNode(bit); // if the opposite bit is not present we cannot get max xor and have to move forward
                }
            }
            return maxXor;
        }
    }
    public static void main(String[] args) {
        System.out.println(maxXOR(7, 7, new ArrayList<>(Arrays.asList(new Integer[]{6, 6, 0, 6, 8, 5, 6})), new ArrayList<>(Arrays.asList(new Integer[]{1,7, 1, 7, 8, 0, 2}))));
    }

    public static int maxXOR(int n, int m, ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
        // We will insert one array numbers to trie and then using numbers from array 2 we will find max xor
        Trie trie = new Trie();
        for(int i = 0; i<n; i++){
            trie.insert(arr1.get(i));
        }
        int maxXor = 0;
        for(int i = 0; i<m; i++){
            maxXor = Math.max(maxXor, trie.getMaxXOR(arr2.get(i)));
        }
        return maxXor;
    }
}
