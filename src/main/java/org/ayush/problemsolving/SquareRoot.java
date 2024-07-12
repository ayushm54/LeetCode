package org.ayush.problemsolving;
/*
* Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.


Example 1:

Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.
Example 2:

Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.


Constraints:

0 <= x <= 231 - 1
* */
public class SquareRoot {
    public static void main(String[] args) {
        System.out.println(squareRoot(4));
        System.out.println(squareRoot(8));
        System.out.println(squareRoot(2));
        System.out.println(squareRoot(5));
        System.out.println(squareRoot(28));

        System.out.println(squareRoot2(4));
        System.out.println(squareRoot2(8));
        System.out.println(squareRoot2(2));
        System.out.println(squareRoot2(5));
        System.out.println(squareRoot2(28));
        System.out.println(squareRoot2(52147395599l));
    }

    private static int squareRoot(int n) {
        if(n==0) return 0;
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            if(i*i <= n){
                ans = i;
            }else{
                break;
            }
        }
        return ans;
    }

    // Binary Search
    private static long squareRoot2(long n) {
        if(n==0) return 0;
        long low = 1, high = n;
        long ans = 1;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if(mid*mid <= n){
                ans = mid;
                low = mid + 1;
            }else if(mid*mid > n){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return (int) ans;
    }
}
