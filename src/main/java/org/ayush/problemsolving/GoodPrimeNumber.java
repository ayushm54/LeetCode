package org.ayush.problemsolving;
/*
* Problem Statement  :

A prime number is a number which is divisible by one and itself. Also a number is called a good  prime number if the sum of its digits is a prime number. For example a number 23 is a good prime number because the sum of 2 and 3 ( 2+3=5) is 5 which is a prime number. You are given an integer K. Your task is to find the kth good prime number that is greater than a provided number N.

For example , 232 is a good prime number since the sum of all digits is 7 which is a prime number whereas 235 is not a good prime number.

Input format :

The first line contains an integer N.
The next line contains an integer K.
Output format :
A single integer which is a Kth good prime number that is greater than a provided number N.

Constraints :

1<=N<=10^5
1<=K<<=10^5
Sample Input 1:
4  4

Sample Output 1:
12

Explanation :
Good prime numbers starting from 4 are 5,7,11(1+1=2 which is prime number),12(1+2=3 which is prime number),14(1+4=5 which is a prime number) and so on. Because the sum of digits of an individual number is a prime number And 4 th good prime number is 12 in this series.Hence the output is 12.

Sample Input 2:
17  5

Sample Output 2:
29

Explanation :

Good prime numbers starting from 17 are 20,21,23,25,29…and the 5th prime number is 29.Hence the output is 29.
* */
public class GoodPrimeNumber {
    public static void main(String[] args) {
        System.out.println(goodPrime(4, 4));
        System.out.println(goodPrime(17, 5));
    }

    public static int goodPrime(int n, int k) {
        int ans = 0;
        int num = n;
        while (k>0){
            num += 1;
            boolean isGoodPrime = isPrime(sumOfDigits(num));
            if(isGoodPrime){
                k--;
                ans = num;
            }
        }
        return ans;
    }

    private static boolean isPrime(int n) {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to sqrt(n)
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;

        return true;
    }

    private static int sumOfDigits(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
