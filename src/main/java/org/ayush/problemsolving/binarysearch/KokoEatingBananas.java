package org.ayush.problemsolving.binarysearch;

/*
* Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:
Input: piles = [3,6,7,11], h = 8
Output: 4

Example 2:
Input: piles = [30,11,23,4,20], h = 5
Output: 30

Example 3:
Input: piles = [30,11,23,4,20], h = 6
Output: 23

Constraints:
1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109
* */
public class KokoEatingBananas {
    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        System.out.println(minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
        System.out.println(minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
        System.out.println(minEatingSpeed(new int[]{332484035,524908576,855865114,632922376,222257295,690155293,112677673,
                679580077,337406589,290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184}, 823855818));
        System.out.println(minEatingSpeed(new int[]{805306368,805306368,805306368}, 1000000000));
    }
    public static int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = getMax(piles);
        while (low <= high) {
            int mid = (low + high) / 2;
            int totalH = getTotalHours(piles, mid);
            if (totalH <= h) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int getMax(int[] piles){
        int ans = Integer.MIN_VALUE;
        for(int i : piles){
            ans = Math.max(ans, i);
        }
        return ans;
    }
    private static int getTotalHours(int[] piles, int hourlyRate) {
        int totalHours = 0;
        for (int i = 0; i < piles.length; i++) {
            totalHours += Math.ceil((double) piles[i] / (double) hourlyRate);
        }
        return totalHours;
    }
}
