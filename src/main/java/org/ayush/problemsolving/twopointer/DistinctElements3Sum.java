package org.ayush.problemsolving.twopointer;

import java.util.ArrayList;
import java.util.Arrays;

/*
* Problem statement
You are given an array/list ARR consisting of N integers. Your task is to find all the distinct triplets present in the array which adds up to a given number K.

An array is said to have a triplet {ARR[i], ARR[j], ARR[k]} with sum = 'K' if there exists three indices i, j and k such that i!=j, j!=k and i!=j and ARR[i] + ARR[j] + ARR[k] = 'K'.

Note:
1. You can return the list of values in any order. For example, if a valid triplet is {1, 2, -3}, then {2, -3, 1}, {-3, 2, 1} etc is also valid triplet. Also, the ordering of different triplets can be random i.e if there are more than one valid triplets, you can return them in any order.
2. The elements in the array need not be distinct.
3. If no such triplet is present in the array, then return an empty list, and the output printed for such a test case will be "-1".
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <=  50
1 <= N <= 10^3
-10^6 <= ARR[i] <= 10^6
-10^9 <= K <= 10^9

Time Limit: 1 sec
Sample Input 1:
1
5
10 5 5 5 2
12
Sample Output 1:
5 5 2
Explanation for Sample Input 1:
5 5 2 is the only triplet that adds up to 12. Note that the order of the output doesn’t matter, so 5 2 5 or 2 5 5 is also acceptable.
Sample Input 2:
3
6
1 2 3 1 2 3
6
4
1 2 3 4
11
6
1 1 2 2 1 1
4
Sample Output 2:
1 2 3
-1
1 1 2
Explanation for Sample Input 2:
In the first input, (1, 2, 3) is the only unique triplet that adds up to six.

In the second input, since there are no such triplets, we print -1.

For the third input, the only distinct triplet is (1, 1, 2), so just print 1 1 2.
* */
public class DistinctElements3Sum {
    public static void main(String[] args) {
        System.out.println(findTriplets(new int[]{1, 2, 3, 4}, 4, 11));
    }

    public static ArrayList<ArrayList<Integer>> findTriplets(int[] arr, int n, int K)  {

        // Write your code here.
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Arrays.sort(arr);
        for(int i =0; i<n; i++){
            int target = K-arr[i];
            int l = i+1, r = n-1;
            while(l<r){
                if(arr[l]+arr[r] == target){
                    ans.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{arr[i], arr[l], arr[r]})));
                    break;
                }
                l++;
                r--;
            }
        }
        return ans;
    }
}
