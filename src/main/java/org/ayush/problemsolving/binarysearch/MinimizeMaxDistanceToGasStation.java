package org.ayush.problemsolving.binarysearch;

import java.util.PriorityQueue;

/*
* You are given a sorted array ‘arr’ of length ‘n’, which contains positive integer positions of ‘n’ gas stations on the X-axis.
You are also given an integer ‘k’.
You have to place 'k' new gas stations on the X-axis.
You can place them anywhere on the non-negative side of the X-axis, even on non-integer positions.
Let 'dist' be the maximum value of the distance between adjacent gas stations after adding 'k' new gas stations.
Find the minimum value of dist.

Example:
Input: ‘n' = 7 , ‘k’=6, ‘arr’ = {1,2,3,4,5,6,7}.
Answer: 0.5
Explanation:
We can place 6 gas stations at 1.5, 2.5, 3.5, 4.5, 5.5, 6.5.
Thus the value of 'dist' will be 0.5. It can be shown that we can't get a lower value of 'dist' by placing 6 gas stations.

Note:
You will only see 1 or 0 in the output where:
  1 represents your answer is correct.
  0 represents your answer is wrong.
Answers within 10^-6 of the actual answer will be accepted.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
5 4
1 2 3 4 5
Expected Answer:
0.5
Output Printed On Console:
1
Explanation of sample output 1:
k = 4, arr = {1,2,3,4,5}
One of the possible ways to place 4 gas stations is {1,1.5,2,2.5,3,3.5,4,4.5,5}
Thus the maximum difference between adjacent gas stations is 0.5.
Hence, the value of ‘dist’ is 0.5. It can be shown that there is no possible way to add 4 gas stations in such a way that the value of ‘dist’ is lower than this. (Note: 1 will be printed in the output for the correct answer).

Sample Input 2:
10 1
1 2 3 4 5 6 7 8 9 10
Expected Answer:
1
Output Printed On Console:
1
Explanation of sample output 2:
k = 1, arr = {1,2,3,4,5,6,7,8,9,10}
One of the possible ways to place 1 gas station is {1,1.5,2,3,4,5,6,7,8,9,10}
Thus the maximum difference between adjacent gas stations is still 1.
Hence, the value of ‘dist’ is 1. It can be shown that there is no possible way to add 1 gas station in such a way that the value of ‘dist’ is lower than this. (Note: 1 will be printed in the output for the correct answer).

Expected Time Complexity
Try solving this in O(n*log(A)), where A is the maximum value in array 'arr'.

Constraints:
2 <= n <= 10^5
1 <= k <= 10^6
1 <= arr[i] <= 10^9
Time Limit: 1 sec
* */
public class MinimizeMaxDistanceToGasStation {
    public static void main(String[] args) {
        System.out.println(minimiseMaxDistance(new int[]{1,2,3,4,5,6,7}, 6));
        System.out.println(minimiseMaxDistance(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 1));
        System.out.println(minimiseMaxDistance(new int[]{1,2,3,4,5}, 4));

        System.out.println(minimiseMaxDistanceUsingHeap(new int[]{1,2,3,4,5,6,7}, 6));
        System.out.println(minimiseMaxDistanceUsingHeap(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 1));
        System.out.println(minimiseMaxDistanceUsingHeap(new int[]{1,2,3,4,5}, 4));

        System.out.println(minimiseMaxDistanceUsingBinarySearch(new int[]{1,2,3,4,5,6,7}, 6));
        System.out.println(minimiseMaxDistanceUsingBinarySearch(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 1));
        System.out.println(minimiseMaxDistanceUsingBinarySearch(new int[]{1,2,3,4,5}, 4));
    }


    /* BRUTE FORCE
    * We are given n gas stations. Between them, there are n-1 sections where we may insert the new stations to reduce the distance.
    * So, we will create an array of size n-1 and each of its indexes will represent the respective sections between the given gas stations.
    * In each iteration, we will identify the index 'i' where the distance (arr[i+1] - arr[i]) is the maximum.
    * Then, we will insert new stations into that section to reduce that maximum distance.
    * The number of stations inserted in each section will be tracked using the previously declared array of size n-1.
    * Finally, after placing all the stations we will find the maximum distance between two consecutive stations.
    * To calculate the distance using the previously discussed formula, we will just do as follows for each section:
        distance = section_length / (number_of_stations_ inserted+1)
    * Among all the values of ‘distance’, the maximum one will be our answer.
    * */
    public static double minimiseMaxDistance(int []arr, int k){
        int n = arr.length; //size of array.
        // we will keep an array to count how many stations we placed at a section between two stations in the array
        int[] howMany = new int[n - 1];
        for (int gasStations = 1; gasStations <= k; gasStations++) {
            //Since we need to minimize the distance between two stations,
            //we need to find the section with current max distance so that we can minimize it and insert the gas station:
            double maxSection = -1;
            int maxInd = -1; // section index where the distance is max
            for (int i = 0; i < n - 1; i++) {
                double diff = arr[i + 1] - arr[i];
                double sectionLength = diff / (double)(howMany[i] + 1); // 1 station in a section will divide its length by 2
                if (sectionLength > maxSection) {
                    maxSection = sectionLength;
                    maxInd = i;
                }
            }
            //insert the current gas station, by incrementing the count
            howMany[maxInd]++;
        }
        //Find the maximum distance i.e. the answer
        double maxAns = -1;
        for (int i = 0; i < n - 1; i++) {
            double diff = arr[i + 1] - arr[i];
            double sectionLength =
                    diff / (double)(howMany[i] + 1);
            maxAns = Math.max(maxAns, sectionLength);
        }
        return maxAns;
    }

    public static double minimiseMaxDistanceUsingHeap(int []arr, int k){
        int n = arr.length; // size of array.
        int[] howMany = new int[n - 1];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Double.compare(b.distance, a.sectionIndex));

        // insert the first n-1 elements into pq
        // with respective distance values:
        for (int i = 0; i < n - 1; i++) {
            pq.add(new Pair(arr[i + 1] - arr[i], i));
        }

        // Pick and place k gas stations:
        for (int gasStations = 1; gasStations <= k; gasStations++) {
            // Find the maximum section
            // and insert the gas station:
            Pair tp = pq.poll();
            int secInd = tp.sectionIndex;

            // insert the current gas station:
            howMany[secInd]++;

            double inidiff = arr[secInd + 1] - arr[secInd];
            double newSecLen = inidiff / (double) (howMany[secInd] + 1);
            pq.add(new Pair(newSecLen, secInd));
        }

        return pq.peek().distance;
    }

    public static class Pair {
        double distance;
        int sectionIndex;

        Pair(double distance, int sectionIndex) {
            this.distance = distance;
            this.sectionIndex = sectionIndex;
        }
    }

    /*
    * Observations:
         * Minimum possible answer: We will get the minimum answer when we place all the gas stations in a single location. Now, in this case, the maximum distance will be 0.
         * Maximum possible answer: We will not place stations before the first or after the last station rather we will place stations in between the existing stations. So, the maximum possible answer is the maximum distance between two consecutive existing stations.
      From the observations, it is clear that our answer lies in the range [0, max(dist)].
      * Upon closer observation, we can recognize that our answer space is actually sorted. ]
      * Additionally, we can identify a pattern that allows us to divide this space into two halves:
         * one consisting of potential answers and the other of non-viable options. So, we will apply binary search on the answer space.
        Changes in the binary search algorithm to apply it to the decimal answer space:
        The traditional binary search algorithm used for integer answer space won't be effective in this case. As our answer space consists of decimal numbers, we need to adjust some conditions to tailor the algorithm to this specific context. The changes are the following:

        while(low <= high): The condition 'while(low <= high)' inside the 'while' loop won't work for decimal answers, and using it might lead to a TLE error. To avoid this, we can modify the condition to 'while(high - low > 10^(-6))'. This means we will only check numbers up to the 6th decimal place. Any differences beyond this decimal precision won't be considered, as the question explicitly accepts answers within 10^-6 of the actual answer.
        low = mid+1: We have used this operation to eliminate the left half. But if we apply the same here, we might ignore several decimal numbers and possibly our actual answer. So, we will use this: low = mid.
        high = mid-1: Similarly, We have used this operation to eliminate the right half. But if we apply the same here, we might ignore several decimal numbers and possibly the actual answer. So, we will use this: high = mid.
        We are applying binary search on the answer i.e. the possible values of distances. So, we have to figure out a way to check the number of gas stations we can place for a particular value of distance.

        How to check the number of gas stations we can place with a particular distance ‘dist’:

        In order to find out the number of gas stations we will use the following function:

        numberOfGasStationsRequired(dist, arr[]):

        We will use a loop(say i) that will run from 1 to n.
        For each section between i and i-1, we will do the following:
        No. of stations = (arr[i]-arr[i-1]) / dist
        Let's keep in mind a crucial edge case: if the section_length (arr[i] - arr[i-1]) is completely divisible by 'dist', the actual number of stations required will be one less than what we calculate.
        if (arr[i]-arr[i-1] == (No. of stations*dist): No. of stations -= 1.
        Now, we will add the no. of stations regarding all the sections and the total will be the answer.
    * */
    public static double minimiseMaxDistanceUsingBinarySearch(int []arr, int k){
        int n = arr.length; // size of the array
        double low = 0;
        double high = 0;

        //Find the maximum distance:
        for (int i = 0; i < n - 1; i++) {
            high = Math.max(high, (double)(arr[i + 1] - arr[i]));
        }

        //Apply Binary search:
        double diff = 1e-6 ;
        while (high - low > diff) {
            double mid = (low + high) / (2.0);
            int cnt = numberOfGasStationsRequired(mid, arr);
            if (cnt > k) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return high;
    }

    public static int numberOfGasStationsRequired(double dist, int[] arr) {
        int n = arr.length; // size of the array
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            int numberInBetween = (int)((arr[i] - arr[i - 1]) / dist);
            if ((arr[i] - arr[i - 1]) == (dist * numberInBetween)) {
                numberInBetween--;
            }
            cnt += numberInBetween;
        }
        return cnt;
    }
}
