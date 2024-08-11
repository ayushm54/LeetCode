package org.ayush.problemsolving.binarysearch;
/*
* You are given an array 'arr' having 'n' distinct integers sorted in ascending order. The array is right rotated 'r' times



Find the minimum value of 'r'.



Right rotating an array means shifting the element at 'ith' index to (‘i+1') mod 'n' index, for all 'i' from 0 to ‘n-1'.



Example:
Input: 'n' = 5 , ‘arr’ = [3, 4, 5, 1, 2]

Output: 3

Explanation:
If we rotate the array [1 ,2, 3, 4, 5] right '3' times then we will get the 'arr'. Thus 'r' = 3.


Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
4
2 3 4 1


Sample Output 1:
3


Explanation of sample output 1:
If we right rotate the array {1, 2, 3, 4} by '3' times then we will get {2, 3, 4, 1}. Thus 'r' = 3.


Sample Input 2:
3
1 2 3


Sample Output 2:
0


Explanation of sample output 2:
If we right rotate the array {1, 2, 3} by '0' time then we will get {1, 2, 3}. Thus 'r' = 0.


Expected time complexity:
Can you solve this in O(logn) time complexity?


Constraints:
1 <= ‘n’ <= 10^5
1 <= ‘arr[i]’ <= 10^9
Time Limit: 1 sec

* */
public class NumberOfRotationsInASortedArray {
    public static void main(String[] args) {
        System.out.println(findKRotation(new int[]{1,2,3}));
        System.out.println(findKRotation(new int[]{2, 3, 4, 1}));
        System.out.println(findKRotation(new int[]{3, 4, 5, 1, 2}));
        System.out.println(findKRotation(new int[]{27, 31, 43, 45, 46, 5, 11, 13, 18, 19, 20}));
    }

    public static int findKRotation(int []nums){
        // The array will always be rotated at the minimum element index, which is our answer
        if(nums.length == 1) return 0;
        int low = 0;
        int high = nums.length - 1;
        int min = Integer.MAX_VALUE;
        int ans = 0;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(nums[low] == nums[mid] && nums[mid] == nums[high]) {
                // Edge case:If we have duplicate elements then we truncate the search space
                if(min > nums[low]){
                    min = nums[low];
                    ans = low;
                }
                low = low + 1;
                high = high - 1;
                continue;
            }
            /* Now we check which half is sorted and get the min element from this sorted half
             * based on this we will reduce the search space*/
            if(nums[low] <= nums[mid] && nums[mid] <= nums[high]) {
                // Meaning both the halfs are sorted
                if(min > nums[low]){
                    min = nums[low];
                    ans = low;
                }
                break;
            }else if(nums[low] <= nums[mid]) { // this means left half is sorted
                if(min > nums[low]){
                    min = nums[low];
                    ans = low;
                }
                low = mid + 1;
            }else { // means right half is sorted
                if(min > nums[mid]){
                    min = nums[mid];
                    ans = mid;
                }
                high = mid - 1;
            }
        }
        return ans;
    }
}
