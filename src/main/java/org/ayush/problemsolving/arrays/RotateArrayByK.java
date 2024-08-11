package org.ayush.problemsolving.arrays;

import java.util.ArrayList;
import java.util.Arrays;

/*
Given an array 'arr' with 'n' elements, the task is to rotate the array to the left by 'k' steps, where 'k' is non-negative.

Example:
'arr '= [1,2,3,4,5]
'k' = 1  rotated array = [2,3,4,5,1]
'k' = 2  rotated array = [3,4,5,1,2]
'k' = 3  rotated array = [4,5,1,2,3] and so on.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
8
7 5 2 11 2 43 1 1
2
Sample Output 1:
2 11 2 43 1 1 7 5
Explanation of Sample Input 1:
Rotate 1 steps to the left: 5 2 11 2 43 1 1 7
Rotate 2 steps to the left: 2 11 2 43 1 1 7 5
Sample Input 2:
4
5 6 7 8
3
Sample Output 2:
8 5 6 7
Explanation of Sample Input 2:
Rotate 1 steps to the left: 6 7 8 5
Rotate 2 steps to the left: 7 8 5 6
Rotate 2 steps to the left: 8 5 6 7
Expected Time Complexity:
O(n), where ‘n’ is the size of the array ‘arr’ and ‘k’ is the number of rotations.
Constraints:
1 <= 'n' <= 10^3
1 <= 'arr'[i] <= 10^9
1 <= 'k' < 'n'

Hints:
1. For an index ‘i’, find where it lands after k swaps.
2. When performing rotation once observe how the positions of all elements change.
* */
public class RotateArrayByK {
    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Before rotation: " + l);
        rotateArray(l, 3);
        System.out.println("After rotation: " + l);

        l = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Before rotation: " + l);
        rotateArray1(l, 3);
        System.out.println("After rotation: " + l);

        l = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Before rotation: " + l);
        rotateArray2(l, 3);
        System.out.println("After rotation: " + l);
    }

    public static ArrayList<Integer> rotateArray(ArrayList<Integer> arr, int k) {
        // Write your code here.
        if(k>arr.size()) k = k%arr.size();
        while(k>0){
            Integer temp = arr.get(0);
            for(int i=1;i<arr.size();i++){
                arr.set(i-1, arr.get(i));
            }
            arr.set(arr.size()-1, temp);
            k--;
        }
        return arr;
    }

    public static ArrayList<Integer> rotateArray1(ArrayList<Integer> arr, int k) {
        // Write your code here.
        int n = arr.size();
        if(k>n) k = k%n;
        int[] temp = new int[k];

        // copy 1st k elements to a temp space
        for(int i =0; i<k; i++){
            temp[i] = arr.get(i);
        }

        // shifting
        for(int i = k; i<n; i++){
            arr.set(i-k, arr.get(i));
        }

        // put back the temp elements
        for(int i = n-k; i<n; i++){
            arr.set(i, temp[i -(n-k)]);
        }
        return arr;
    }

    // Optimal Approach without extra space
    public static ArrayList<Integer> rotateArray2(ArrayList<Integer> arr, int k) {
        int n = arr.size();
        if(k>n) k = k%n;
        // Reverse the array from 0, to k-1
        int l = 0, r = k - 1;
        while (l < r) {
            int temp = arr.get(l);
            arr.set(l, arr.get(r));
            arr.set(r, temp);
            r--;
            l++;
        }
        // reverse the array from k, n-1
        l = k;
        r = n - 1;
        while (l < r) {
            int temp = arr.get(l);
            arr.set(l, arr.get(r));
            arr.set(r, temp);
            r--;
            l++;
        }
        // reverse the full array
        l = 0;
        r = n - 1;
        while (l < r) {
            int temp = arr.get(l);
            arr.set(l, arr.get(r));
            arr.set(r, temp);
            r--;
            l++;
        }
        return arr;
    }
}
