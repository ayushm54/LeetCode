package org.ayush.problemsolving;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
* Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.



Example 1:

Input: n = 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
Example 2:

Input: n = 2
Output: false


Constraints:

1 <= n <= 231 - 1
* */
public class HappyNumber {
    public static void main(String[] args) {
        System.out.println(isHappy(19));
        System.out.println(isHappy(2));
        System.out.println(isHappy(3));
    }
    public static boolean isHappy(int n) {
        int temp = n;
        Set<Integer> m = new HashSet<>();
        while(true){
            int result = 0;
            String numStr = Integer.toString(temp);
            for (int i = 0; i < numStr.length(); i++) {
                int digit = numStr.charAt(i)-'0';
                result += digit*digit;
            }
            if(m.contains(1)){
                return true;
            }
            if(m.contains(result) || m.contains(n)){
                break;
            }
            m.add(result);
            temp = result;
        }
        return false;
    }

}
