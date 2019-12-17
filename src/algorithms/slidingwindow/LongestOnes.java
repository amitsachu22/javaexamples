/*
 * Author: Amit Sachu
 * Date: December 16, 2019
 * Description: ax Consecutive Ones III
 * LeetCode problem no: 1004
 * URL: https://leetcode.com/problems/max-consecutive-ones-iii/
 */


package algorithms.slidingwindow;

/*

Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
Return the length of the longest (contiguous) subarray that contains only 1s.

Example 1:

Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation:
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
Example 2:

Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation:
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.


Note:

1 <= A.length <= 20000
0 <= K <= A.length
A[i] is 0 or 1


* */


public class LongestOnes {


    public static void main(String[] args) {


//        //test case 1
//        int[] A = {1, 1, 1, 0, 0, 1, 0};
//        int K = 2;


//        //test case 2
//        int[] A = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
//        int K = 2;

////        test case 3
//        int[] A = {0,  0, 1, 1, 0, 0, 1, 1, 1,0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
//        int K = 3;


//        //test case 4 (Failed after submitting in LeetCode
//          Expected output = 0
//        int[] A = {0, 0, 0, 0};
//        int K = 0;

//        //test case 5 (Failed after submitting in LeetCode
//        // Expected output = 3
//        int[] A = {1, 1, 1};
//        int K = 0;


//        int[] A = {0,0,0,1};
//        int K = 4;


        //       int[] A = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        //     int K = 3;


        int A[] = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int K = 0;


        System.out.println(longestOnesLength(A, K));


    }


    public static int longestOnesLength(int[] A, int K) {


        if (A == null || A.length == 0 || K > A.length) return 0;

        int rightIndex = 0;
        int leftIndex = 0;

        int zeroCounter = 0;

        int longestOnesLength = 0;


        for (rightIndex = 0; rightIndex < A.length; rightIndex++) {


            if (A[rightIndex] == 0) {

                zeroCounter += 1;

            }


            if (zeroCounter <= K) {

                if ((rightIndex - leftIndex) + 1 > longestOnesLength) {

                    longestOnesLength = (rightIndex - leftIndex) + 1;


                }

            }


            while (zeroCounter > K) {

                if ((rightIndex - leftIndex) > longestOnesLength) {

                    longestOnesLength = (rightIndex - leftIndex);

                }

                if (A[leftIndex] == 0) {

                    zeroCounter -= 1;

                }

                leftIndex += 1;

            }


        }

        return longestOnesLength;


    }

}


