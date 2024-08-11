package org.ayush.problemsolving.arrays;

import java.util.Arrays;

/*
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent,
with the colors in the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
You must solve this problem without using the library's sort function.

Example 1:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Example 2:
Input: nums = [2,0,1]
Output: [0,1,2]

Constraints:
n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.
* */
public class SortColors {
    public static void main(String[] args) {
        int[] colors = new int[]{2, 0, 2, 1, 1 ,0};
        System.out.println(Arrays.toString(colors));
        sortColors(colors);
        System.out.println(Arrays.toString(colors));

        colors = new int[]{2, 0, 2, 1, 1 ,0};
        System.out.println(Arrays.toString(colors));
        sortColors1(colors);
        System.out.println(Arrays.toString(colors));

        colors = new int[]{2, 0, 2, 1, 1 ,0};
        System.out.println(Arrays.toString(colors));
        sortColors2(colors);
        System.out.println(Arrays.toString(colors));
    }

    // Brute force using sorting algorithm
    public static void sortColors(int[] nums) {
        Arrays.sort(nums);
    }

    // Using count
    public static void sortColors1(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) count0++;
            else if (nums[i] == 1) count1++;
            else if (nums[i] == 2) count2++;
        }
        int i = 0;
        while (i < count0) {
            nums[i] = 0;
            i++;
        }
        int j = 0;
        while (j < count1) {
            nums[i] = 1;
            i++;
            j++;
        }
        j = 0;
        while (j < count2) {
            nums[i] = 2;
            i++;
            j++;
        }
    }

    // Dutch National Flag Algorithm (DNF) using single loop
    /*
        The algorithm uses three pointers low, mid and high
        everything between 0 to low-1 will be 0    -> extreme left
        everything between low to mid-1 will be 1
        everything between high+1 to n-1 will be 2 -> extreme right

        between mid to high we will have unsorted region which needs to be sorted, once mid crosses high array will be sorted
    */
    public static void sortColors2(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            }else if(nums[mid] == 1){
                mid++;
            }else{
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
