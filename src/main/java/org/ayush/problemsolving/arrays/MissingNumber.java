package org.ayush.problemsolving.arrays;

import java.util.HashMap;
import java.util.Map;

/*
Given an array ‘a’ of size ‘n’-1 with elements of range 1 to ‘n’. The array does not contain any duplicates. Your task is to find the missing number.

For example:
Input:
'a' = [1, 2, 4, 5], 'n' = 5

Output :
3

Explanation: 3 is the missing value in the range 1 to 5.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
4
1 2 3
Sample Output 1:
4
Explanation Of Sample Input 1:
4 is the missing value in the range 1 to 4.
Sample Input 2:
8
1 2 3 5 6 7 8
Sample Output 2:
4
Explanation Of Sample Input 2:
4 is the missing value in the range 1 to 8.
Expected time complexity:
The expected time complexity is O(n).
Constraints:
1 <= 'n' <= 10^6
1 <= 'a'[i] <= 'n'
Time Limit: 1 sec
* */
public class MissingNumber {

    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{1, 2, 4, 5}, 5));
        System.out.println(missingNumber(new int[]{1, 2, 3, 5, 6, 7, 8}, 8));

        System.out.println(missingNumber1(new int[]{1, 2, 4, 5}, 5));
        System.out.println(missingNumber1(new int[]{1, 2, 3, 5, 6, 7, 8}, 8));

        System.out.println(missingNumber2(new int[]{1, 2, 4, 5}, 5));
        System.out.println(missingNumber2(new int[]{1, 2, 3, 5, 6, 7, 8}, 8));
    }

    public static int missingNumber(int []a, int N) {
        // Write your code here.
        for(int i=1;i<=N;i++){
            int flag = 0;
            for(int j = 0; j< a.length; j++){
                if(a[j] == i){
                    flag = 1;
                    break;
                }
            }
            if(flag == 0) return i;
        }
        return -1;
    }

    public static int missingNumber1(int []a, int N) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<a.length;i++){
            map.put(a[i], i);
        }
        for(int i=1;i<=N;i++){
            if (!map.containsKey(i)) {
                return i;
            }
        }
        return -1;
    }

    public static int missingNumber2(int []a, int N) {
        int requiredSum = N*(N+1)/2;
        int actualSum = 0;
        for(int i=0;i<a.length;i++){
            actualSum += a[i];
        }
        return requiredSum - actualSum;
    }
}
