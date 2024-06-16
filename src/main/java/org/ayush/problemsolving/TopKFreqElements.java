package org.ayush.problemsolving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.



Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.


Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
* */
public class TopKFreqElements {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(topKFrequent2(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int x: nums){
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        int[] result = new int[k];
        for (int i =0; i<k; i++){
            Integer maxEntry = null;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if(maxEntry == null || entry.getValue() > map.get(maxEntry)){
                    maxEntry = entry.getKey();
                }
            }
            result[i] = maxEntry;
            map.remove(maxEntry);
        }
        return result;
    }

    public static int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int x: nums){
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        int[] result = new int[k];
        for (int i =0; i<k; i++){
            result[i] = entryList.get(i).getKey();
        }
        return result;
    }
}
