package org.ayush.problemsolving;
/*
* Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
Return the kth positive integer that is missing from this array.

* Example 1:
Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.

Example 2:
Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.

Constraints:
1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
arr[i] < arr[j] for 1 <= i < j <= arr.length

Follow up:
Could you solve this problem in less than O(n) complexity?
* */
public class KthMissingNumber {
    public static void main(String[] args) {
        System.out.println(findKthPositive(new int[]{2,3,4,7,11}, 5));
        System.out.println(findKthPositive(new int[]{1,2,3,4}, 2));

        System.out.println(findKthPositive2(new int[]{2,3,4,7,11}, 5));
        System.out.println(findKthPositive2(new int[]{1,2,3,4}, 2));
        System.out.println(findKthPositive2(new int[]{4, 7, 9}, 3));
    }

    public static int findKthPositive(int[] arr, int k) {
        for(int i=0; i < arr.length; i++) {
            if(arr[i] <= k) k++;
            else break;
        }
        return k;
    }

    /*
    * Here we will use the binary search
    * since it is a increasing sequence, ideally the array should have been [1, 2, 3, 4, 5], hence using this information we can find number of missing elements at an index,
    * so for below example, in place of 2, it shoul;d have been 1, hence at index 0, 1 element is missing,
    * similarly, at index 1, it should have been 2, so again 1 element missing, same for index 2
    * at index 3, we have 7, but idealy 4 should have been there, hence 7-4= 3 elements are missing here and so on
    * indexes: [0, 1, 2, 3, 4]
    * example: [2, 3, 4, 7, 11]
    * ideally: [1, 2, 3, 4, 5]
    * missing: [1, 1, 1, 3, 6]
    *          [2-(0+1), 3 - (1+1), 4-(2+1), 7-(3+1), 11-(4+1)], in general missing = arr[i] - (i+1)
    * hence we can perform binary search on missing elements to figure out where should have been K present, here between 7(index 3) and 11(index 4)
    * */
    public static int findKthPositive2(int[] arr, int k) {
       int low = 0, high = arr.length - 1;
       while(low <= high) {
           int mid = low + (high - low) / 2;
           int missingNums = arr[mid] - (mid+1);
           if(missingNums < k) low = mid + 1;
           else high = mid - 1;
       }
       // finally high will cross low and will point to an index where missingNums will be <= k
       /* e.g. if arr[high] = 7, missingNumsAtHigh = 3, K = 5, hence we need 2nd element from high (5 - 3)
            ans  = arr[high] + (k - (arr[high] - (high+1)
            ans = arr[high] + k - arr[high] + high + 1
            ans = k + high + 1
        */
        return k + high + 1;
    }
}
