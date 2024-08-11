package org.ayush.problemsolving.binarysearch;
/*
* You are given two positive integers 'n' and 'm'. You have to return the 'nth' root of 'm', i.e. 'm(1/n)'. If the 'nth root is not an integer, return -1.



Note:
'nth' root of an integer 'm' is a number, which, when raised to the power 'n', gives 'm' as a result.


Example:
Input: ‘n’ = 3, ‘m’ = 27
Output: 3
Explanation:
3rd Root of 27 is 3, as (3)^3 equals 27.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
3 27
Sample Output 1:
3

Explanation For Sample Input 1:
3rd Root of 27 is 3, as (3)^3 equals 27.

Sample Input 2:
4 69
Sample Output 2:
-1
Explanation For Sample Input 2:
4th Root of 69 is not an integer, hence -1.
Expected Time Complexity:
Try to do this in O(log(n+m)).

Constraints:
1 <= n <= 30
1 <= m <= 10^9
Time Limit: 1 sec.
*  */
public class NthRootOfM {
    public static void main(String[] args) {
        System.out.println(NthRoot(3,27));
        System.out.println(NthRoot(4,69));
        System.out.println(NthRoot(9,1953125));
    }

    public static int NthRoot(int n, int m) {
        if(m==0) return 0;
        int low = 1, high = m;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int powComparedToM = pow(n, m, mid);
            if(powComparedToM == 1){
                return mid;
            }else if(powComparedToM == 2){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return -1;
    }

    /*
    * If we get a large number as input and try to find power of it, it will overflow, hence to cater out condition, we will use the below logic
    * if at any moment during power calculation, the intermediate answer crosses m, we break and return 2
    * if the ans == m, we return 1
    * if ans > m, we return 2
    * else we return 0
    * */
    private static int pow(int n, int m, int mid){
        long ans = 1;
        for(int i=1; i<=n; i++){
            ans *= mid;
            if(ans > m){
                return 2;
            }
        }
        return ans == m ? 1: 0;
    }
}
