package org.ayush.problemsolving.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/*
* The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
Given n, calculate F(n).

Example 1:
Input: n = 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.

Example 2:
Input: n = 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.

Example 3:
Input: n = 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.

Constraints:
0 <= n <= 30
* */
public class Fibonacci {
    public static void main(String[] args) {
        Map<Integer, Integer> dp = new HashMap<Integer, Integer>();
        System.out.println(fib1(2, dp));
        System.out.println(fib1(3, dp));
        System.out.println(fib1(4, dp));

        System.out.println(fib(2));
        System.out.println(fib(3));
        System.out.println(fib(4));
    }

    /*Using extra space*/
    public static int fib1(int n, Map<Integer, Integer> dp) {
        if(n<=1) return n;
        if(dp.containsKey(n)) return dp.get(n);
        dp.put(n, fib1(n-1, dp) + fib1(n-2, dp));
        return dp.get(n);
    }

    /* Most optimized without extra space*/
    public static int fib(int n) {
        if(n<=1) return n;
        int prev = 1, prev2 = 0;
        for(int i = 2; i<=n; i++){
            int temp = prev + prev2;
            prev2 = prev;
            prev = temp;
        }
        return prev;
    }
}
