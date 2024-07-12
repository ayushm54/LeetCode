package org.ayush.problemsolving;
/*
* Find the longest-running positive sequence in an array.

Examples:

Input : arr[] = {1, 2, -3, 2, 3, 4, -6, 1,
                     2, 3, 4, 5, -8, 5, 6}
Output :Index : 7, length : 5

Input : arr[] = {-3, -6, -1, -3, -8}
Output : No positive sequence detected.
* */
public class LongestSequenceOfPositiveIntegersInArray {
    public static void main(String[] args) {
        System.out.println(longestSequence(new int[] {1, 2, -3, 2, 3, 4, -6, 1, 2, 3, 4, 5, -8, 5, 6}));
        System.out.println(longestSequence(new int[] {-3, -6, -1, -3, -8}));
    }

    public static int longestSequence(int[] arr) {
        int maxCount = 0;
        int count = 0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i] > 0){
                count++;
                maxCount = Math.max(maxCount, count);
            }else{
                count = 0;
            }
        }
        return maxCount;
    }
}
