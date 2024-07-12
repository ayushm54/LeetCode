package org.ayush.problemsolving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
* You are given an array/list ‘ARR’ consisting of ‘N’ non-negative integers. You are also given a list ‘QUERIES’ consisting of ‘M’ queries, where the ‘i-th’ query is a list/array of two non-negative integers ‘Xi’, ‘Ai’, i.e ‘QUERIES[i]’ = [‘Xi’, ‘Ai’].

The answer to the ith query, i.e ‘QUERIES[i]’ is the maximum bitwise xor value of ‘Xi’ with any integer less than or equal to ‘Ai’ in ‘ARR’.

You should return an array/list consisting of ‘N’ integers where the ‘i-th’ integer is the answer of ‘QUERIES[i]’.

Note:

1. If all integers are greater than ‘Ai’ in array/list ‘ARR’  then the answer to this ith query will be -1.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 50
1 <= N, M <= 10000
0 <= ARR[i], Xi, Ai <= 10^9

Where ‘T’ is the number of test cases, 'N' is the size of ‘ARR’, ‘M’  is the number of queries, ‘ARR[i]’ is an element of array/list ‘ARR’ and ‘Xi’, ‘Ai’ are the integers in ‘QUERIES[i]’.

Time limit: 1 sec
Sample Input 1:
2
5 2
0 1 2 3 4
1 3
5 6
1 1
1
1 0
Sample Output 1:
3 7
-1
Explanation of sample input 1:
In the first test case, the answer of query [1, 3] is 3 because 1^2 = 3 and 2 <= 3,  and the answer of query [5, 6] is 7 because  5 ^ 2 = 7 and 2 <= 6.

In the second test case, no element is less than or equal to 0 in the given array ‘ARR’.
Sample Input 2:
2
6 3
6 6 3 5 2 4
6 3
8 1
12 4
5 2
0 0 0 0 0
1 0
1 1
Sample Output 2:
5 -1 15
1 1
Java (SE 1.8)
1234567
import java.util.ArrayList;

public class Solution {
    public static ArrayList<Integer> maxXorQueries(ArrayList<Integer> arr, ArrayList<ArrayList<Integer>> queries) {
        // Write your code here.
    }
}

* */
public class MaxXORQueries {
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
        Integer[][] queries = new Integer[][]{{6, 3}, {8, 1}};
        ArrayList<ArrayList<Integer>> q = new ArrayList<>();
        for (Integer[] query : queries) {
            ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(query));
            q.add(ints);
        }
        System.out.println(maxXorQueries(new ArrayList<>(Arrays.asList(new Integer[]{6, 6, 3, 5, 2, 4})), q));
    }

    public static ArrayList<Integer> maxXorQueries(ArrayList<Integer> arr, ArrayList<ArrayList<Integer>> queries) {
        Trie trie = new Trie();
        ArrayList<Integer> ans = new ArrayList<>(queries.size());
        Collections.sort(arr);
        ArrayList<ArrayList<Integer>> queries1 = new ArrayList<>();
        for(int i =0; i< queries.size(); i++){
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(queries.get(i).get(1));
            temp.add(queries.get(i).get(0));
            temp.add(i);
            queries1.add(temp);
        }
        /*
        * in queries1 we will have something like [[3, 1, 0], [1, 5, 1], [4, 2, 2]] (in format: [Ai, Xi, i])
        * To solve the problem effectively we need to make sure we insert the smallest element(Ai) first into the trie and solve for tyhe query corresponding to this Ai
        * */
        Collections.sort(queries1, (a, b) -> a.get(0).compareTo(b.get(0)));

        for(int i = 0;i< queries.size();i++) ans.add(-1); // initializing thr answer list with -1

        int idx = 0; // to loop over the integer array
        for(int i = 0;i< queries.size();i++){
            while(idx<arr.size() && arr.get(idx) <= queries1.get(i).get(0)) {
                trie.insert(arr.get(idx));
                idx++;
            }
            int queryInd = queries1.get(i).get(2);
            if(idx!=0) ans.set(queryInd, trie.getMaxXOR(queries1.get(i).get(1)));
            else ans.set(queryInd, -1);
        }
        return ans;
    }
}
