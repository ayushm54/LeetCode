package org.ayush.problemsolving.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
There is an array ‘A’ of size ‘N’ with an equal number of positive and negative elements.
Without altering the relative order of positive and negative numbers, you must return an array of alternative positive and negative values.

Note:
Start the array with a positive number.
For example
Input:
A = [1, 2, -4, -5], N = 4
Output:
1 -4  2 -5
Explanation:
Positive elements = 1, 2
Negative elements = -4, -5
To maintain relative ordering, 1 must occur before 2, and -4 must occur before -5.
Detailed explanation ( Input/output format, Notes, Images )

Constraints:
2 <= N <= 10^5
-10^9 <= A[i] <= 10^9, A[i] != 0
N%2==0

Time Limit: 1 sec
Sample Input 1:
6
1 2 -3 -1 -2 3
Sample Output 1:
1 -3 2 -1 3 -2
Explanation Of Sample Input 1:
Testcase 1:
Input:
A = [1, 2, -3, -1, -2, 3], N = 6
Output:
1 -3 2 -1 3 -2
Explanation:
Positive elements = 1, 2, 3
Negative elements = -3, -1, -2
To maintain relative ordering, 1 should come before 2, and 2 must come before 3.
Also, -3 should come before -1, and -1 must come before -2.
Sample Input 2:
4
-2 -3 4 5
Sample Output 2:
4 -2 5 -3
* */
public class AlternateNumbers {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(alternateNumbers(new int[]{1, 2, -4, -5})));
        System.out.println(Arrays.toString(alternateNumbers1(new int[]{1, 2, -4, -5})));

        System.out.println(Arrays.toString(alternateNumbers2(new int[]{1, 2, -4, -5, 3, 5})));
    }

    /// Brute force
    public static int[] alternateNumbers(int []a) {
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            if (a[i] >= 0) {
                positive.add(a[i]);
            }else {
                negative.add(a[i]);
            }
        }

        int[] result = new int[a.length];
        for (int i = 0; i < a.length/2; i++) {
            result[2*i] = positive.get(i); // positive elements will be on even indexes
            result[2*i+1] = negative.get(i); // negatives are at odd index
        }
        return result;
    }

    // Optimal
    public static int[] alternateNumbers1(int []a) {
        int[] result = new int[a.length];
        int pos = 0, neg = 1;
        for (int i = 0; i < a.length; i++) {
            // we will pick elements one by one and put it to the correct index
            if (a[i] >= 0) {
                result[pos] = a[i];
                pos += 2;
            }else {
                result[neg] = a[i];
                neg += 2;
            }
        }
        return result;
    }

    // If number of positives and negatives are not equal
    public static int[] alternateNumbers2(int []a) {
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        int[] result = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            if (a[i] >= 0) {
                positive.add(a[i]);
            }else {
                negative.add(a[i]);
            }
        }
        // we can run the common loop till the smaller list
        int posLen  = positive.size();
        int negLen = negative.size();
        int loopLen =  posLen;
        String smaller = "pos";
        if(posLen > negLen){
            smaller = "neg";
            loopLen = negLen;
        }
        for(int i = 0; i<loopLen; i++){
            result[2*i] = positive.get(i); // positive elements will be on even indexes
            result[2*i+1] = negative.get(i); // negatives are at odd index
        }
        int idx = loopLen*2; // now we need to start from this index becuase letas say negatives were 2 , then 2 positives are already added and vice-versa
        if(!smaller.equals("pos")){
            for(int j = loopLen; j < posLen; j++){
                result[idx] = positive.get(j);
                idx++;
            }
        }else{
            for(int j = loopLen; j < negLen; j++){
                result[idx] = negative.get(j);
                idx++;
            }
        }
        return result;
    }
}

