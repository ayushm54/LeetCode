package org.ayush.problemsolving.binarysearch;
/*
* Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).

Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

Constraints:
nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
* */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3, 4}));
        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{2, 3}));

        System.out.println(findMedianSortedArraysOptimized(new int[]{1,3}, new int[]{2}));
        System.out.println(findMedianSortedArraysOptimized(new int[]{1,2}, new int[]{3, 4}));
        System.out.println(findMedianSortedArraysOptimized(new int[]{}, new int[]{2, 3}));

        System.out.println(findMedianSortedArraysBinarySearch(new int[]{1,3}, new int[]{2}));
        System.out.println(findMedianSortedArraysBinarySearch(new int[]{1,2}, new int[]{3, 4}));
        System.out.println(findMedianSortedArraysBinarySearch(new int[]{}, new int[]{2, 3}));
    }
    //  BRUTE FORCE
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int nums[] = mergeArrays(nums1, nums2);
        int n = nums.length;
        if (n % 2 == 0) {
            return (nums[n / 2 - 1] + nums[n / 2]) / 2.0;
        }
        return nums[n / 2];
    }

    //  Reducing Extra Space, when we are merging we can find the element at the median indexes directly without creating a merge array
    public static double findMedianSortedArraysOptimized(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        int ind1 = n/2 - 1, element1 = -1;
        int ind2 = n/2, element2 = -1;
        int i = 0, j = 0, k = 0;
        while (i<nums1.length && j<nums2.length) {
            if (nums1[i] <= nums2[j]) {
                if(k == ind1) element1 = nums1[i];
                if(k == ind2) element2 = nums1[i];
                k++;
                i++;
            }else {
                if(k == ind1) element1 = nums2[j];
                if(k == ind2) element2 = nums2[j];
                k++;
                j++;
            }
        }
        while(i<nums1.length){
            if(k == ind1) element1 = nums1[i];
            if(k == ind2) element2 = nums1[i];
            k++;
            i++;
        }
        while(j<nums2.length){
            if(k == ind1) element1 = nums2[j];
            if(k == ind2) element2 = nums2[j];
            k++;
            j++;
        }
        if(n%2 == 0) return (element1 + element2) / 2.0;
        return  element2;
    }

    /*
    * Median creates a partition on the final merged array: Upon closer observation, we can easily show that the median divides the final merged array into two halves. For example,
    * arr1 = [1, 3, 4, 7, 10, 12]
    * arr2 = [2, 3, 6, 15]
    * merged = [1, 2, 3, 3, 4,        6, 7, 10, 12]
    *                          Median
    * Each half contains (n/2) elements.
    * Each half also contains x elements from the first array i.e. arr1[] and (n/2)-x elements from the second array i.e. arr2[].
    * The value of x might be different for the two halves. For example, in the above array, the left half contains 3 elements from arr1[] and 2 elements from arr2[].
    * Considering different values of x, we can get different left and right halves(x = the number of elements taken from arr1[] for a particular half)
    * Our goal is to find this x such that we get the sorted array.
    * Try to form the unique left half:
        * For a valid merged array, the configurations of the two halves are unique.
        * So, we can try to form the halves with different values of x, where x = the number of elements taken from arr1[] for a particular half.
        * There's no need to construct both halves. Once we have the correct left half, the right half is automatically determined,
          consisting of the remaining elements not yet considered. Therefore, our focus will solely be on creating the unique left half.
        * How to form all configurations of the left half:
            * We know that the left half will surely contain x elements from arr1[] and (n/2)-x elements from arr2[]. Here the only variable is x.
            * The minimum possible value of x is 0 and the maximum possible value is n1(i.e. The length of the considered array).
            * For all the values,[0, n1] of x, we will try to form the left half and then we will check if that half’s configuration is valid.
    * Check if the formed left half is valid:
        * For a valid left half, the merged array will always be sorted. So, if the merged array containing the formed left half is sorted, the formation is valid.
        * How to check if the merged array is sorted without forming the array:
            * In order to check we will consider 4 elements, i.e. l1, l2, r1, r2.
            * l1 = the maximum element belonging to arr1[] of the left half.
            * l2 = the maximum element belonging to arr2[] of the left half.
            * r1 = the minimum element belonging to arr1[] of the right half.
            * r2 = the minimum element belonging to arr2[] of the right half.
    * How to apply Binary search to form the left half:
        * We will check the formation of the left half for all possible values of x.
          Now, we know that the minimum possible value of x is 0 and the maximum is n1(i.e. The length of the considered array).
          Now the range is sorted. So, we will apply the binary search on the possible values of x i.e. [0, n1].
    * How to eliminate the halves based on the values of x:
        * Binary search works by eliminating the halves in each step. Upon closer observation, we can eliminate the halves based on the following conditions:
            * If l1 > r2: This implies that we have considered more elements from arr1[] than necessary. So, we have to take less elements from arr1[] and more from arr2[].
              In such a scenario, we should try smaller values of x. To achieve this, we will eliminate the right half (high = mid-1).
            * If l2 > r1: This implies that we have considered more elements from arr2[] than necessary. So, we have to take less elements from arr2[] and more from arr1[].
              In such a scenario, we should try bigger values of x. To achieve this, we will eliminate the left half (low = mid+1).
    * Until now, we have learned how to use binary search but with the assumption that (n1+n2) is even. Let’s generalize this.
    * If (n1+n2) is odd: In the case of even, we have considered the length of the left half as (n1+n2) / 2. In this case, that length will be (n1 + n2 + 1) / 2.
      This much change is enough to handle the case of odd. The rest of the things will be completely the same.

    * What will be the answer i.e. the median:
        * If l1 <= r2 && l2 <= r1: This condition assures that we have found the correct elements.
        * If (n1+n2) is odd: The median will be max(l1, l2).
        * Otherwise, median = (max(l1, l2) + min(r1, r2)) / 2.0
    * Note: We are applying binary search on the possible values of x i.e. [0, n1]. Here n1 is the length of arr1[].
    * Now, to further optimize it, we will consider the smaller array as arr1[]. So, the actual range will be [0, min(n1, n2)].
    *
    * Algorithm:
        * First, we have to make sure that the arr1[] is the smaller array. If not by default, we will just swap the arrays. Our main goal is to consider the smaller array as arr1[].
        * Calculate the length of the left half: left = (n1+n2+1) / 2.
        * Place the 2 pointers i.e. low and high: Initially, we will place the pointers. The pointer low will point to 0 and the high will point to n1(i.e. The size of arr1[]).
        * Calculate the ‘mid1’ i.e. x and ‘mid2’ i.e. left-x: Now, inside the loop, we will calculate the value of ‘mid1’ using the following formula:
            * mid1 = (low+high) // 2 ( ‘//’ refers to integer division)
            * mid2 = left-mid1
        * Calculate l1, l2, r1, and r2: Generally,
            * l1 = arr1[mid1-1]
            * l2 = arr2[mid2-1]
            * r1 = arr1[mid1]
            * r2 = arr2[mid2]
            * The possible values of ‘mid1’ and ‘mid2’ might be 0 and n1 and n2 respectively. So, to handle these cases, we need to store some default values for these four variables. The default value for l1 and l2 will be INT_MIN and for r1 and r2, it will be INT_MAX.
        * Eliminate the halves based on the following conditions:
            * If l1 <= r2 && l2 <= r1: We have found the answer.
            * If (n1+n2) is odd: Return the median = max(l1, l2).
            * Otherwise: Return median = (max(l1, l2)+min(r1, r2)) / 2.0
            * If l1 > r2: This implies that we have considered more elements from arr1[] than necessary. So, we have to take less elements from arr1[] and more from arr2[]. In such a scenario, we should try smaller values of x. To achieve this, we will eliminate the right half (high = mid1-1).
            * If l2 > r1: This implies that we have considered more elements from arr2[] than necessary. So, we have to take less elements from arr2[] and more from arr1[]. In such a scenario, we should try bigger values of x. To achieve this, we will eliminate the left half (low = mid1+1).
            * Finally, outside the loop, we will include a dummy return statement just to avoid warnings or errors.
        * The steps from 4-6 will be inside a loop and the loop will continue until low crosses high.
    * */
    public static double findMedianSortedArraysBinarySearch(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        //if n1 is bigger swap the arrays:
        if (n1 > n2) return findMedianSortedArraysBinarySearch(nums2, nums1);
        int n = n1 + n2; //total length
        int left = (n1 + n2 + 1) / 2; //length of left half
        //apply binary search:
        int low = 0, high = n1;
        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = left - mid1;

            //calculate l1, l2, r1 and r2;
            int l1 = (mid1 > 0) ? nums1[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? nums2[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n1) ? nums1[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n2) ? nums2[mid2] : Integer.MAX_VALUE;

            if (l1 <= r2 && l2 <= r1) {
                if (n % 2 == 1) return Math.max(l1, l2);
                else return ((double) (Math.max(l1, l2) + Math.min(r1, r2))) / 2.0;
            } else if (l1 > r2) high = mid1 - 1;
            else low = mid1 + 1;
        }
        return 0;
    }

    private static int[] mergeArrays(int[] nums1, int[] nums2) {
        int nums[] = new int[nums1.length+nums2.length];
        int i = 0, j = 0, k=0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                nums[k++] = nums1[i++];
            }else {
                nums[k++] = nums2[j++];
            }
        }
        while(i<nums1.length){
            nums[k++] = nums1[i++];
        }
        while(j<nums2.length){
            nums[k++] = nums2[j++];
        }
        return nums;
    }
}
