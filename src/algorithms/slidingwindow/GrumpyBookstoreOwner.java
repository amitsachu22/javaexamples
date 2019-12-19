/*
 * Author: Amit Sachu
 * Date: December 18, 2019
 * Description: 1052. Grumpy Bookstore Owner
 * LeetCode problem no: 1052
  * URL: https://leetcode.com/problems/grumpy-bookstore-owner/
 */


package algorithms.slidingwindow;


/*
* Today, the bookstore owner has a store open for customers.length minutes.  Every minute, some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.

On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.

The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.

Return the maximum number of customers that can be satisfied throughout the day.



Example 1:

Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
Output: 16
Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.


Note:

1 <= X <= customers.length == grumpy.length <= 20000
0 <= customers[i] <= 1000
0 <= grumpy[i] <= 1
*
* */

public class GrumpyBookstoreOwner {


    public static void main(String... args) {


        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        int X = 3;


//        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
//        int[] grumpy =    {0, 0, 0, 0, 0, 0, 0, 1};
//        int X = 3;


//        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
//        int[] grumpy = {1, 0, 0, 0, 0, 0, 0, 1};
//        int X = 3;


//        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
//        int[] grumpy = {1, 0, 0, 0, 0, 0, 0, 1};
//        int X = 0;


        System.out.println(maximumCustomersSatisfied(customers, grumpy, X));


        System.out.println(totalCustomersSatisfied(customers, grumpy, X));


    }

    /*
    * Solution works and accepted on LeetCode however the performance is not impressive (Brute Force Approach)
    * Refer to another solution below with much improved performance (Similar as of DietPlanPerformance Algorithm)
    * */
    public static int maximumCustomersSatisfied(int[] customers, int[] grumpy, int X) {

        int maxCustomerSatisfied = 0;
        int nonGrumpyCustomersSatisfied = 0;
        int grumpyCustomersSatisfied = 0;
        int rightIndex = 0;
        int leftIndex = 0;
        int counter = 1;


        for (int i = 0; i < customers.length; i++) {

            if (grumpy[i] == 0) {

                nonGrumpyCustomersSatisfied = nonGrumpyCustomersSatisfied + customers[i];

            }

        }


        for (rightIndex = 0; rightIndex < customers.length; rightIndex++) {

            if (grumpy[rightIndex] == 1) {

                leftIndex = rightIndex;


                while (counter <= X && leftIndex < customers.length) {

                    if (grumpy[leftIndex] == 1) {

                        grumpyCustomersSatisfied = grumpyCustomersSatisfied + customers[leftIndex];
                    }

                    leftIndex += 1;
                    counter += 1;

                }

            }

            if (nonGrumpyCustomersSatisfied + grumpyCustomersSatisfied > maxCustomerSatisfied) {

                maxCustomerSatisfied = nonGrumpyCustomersSatisfied + grumpyCustomersSatisfied;

            }

            counter = 1;
            grumpyCustomersSatisfied = 0;


        }


        return maxCustomerSatisfied;


    }

    // Fast/Slow Algorithm solution with much improved performance (Similar as of DietPlanPerformance Algorithm) //
    public static int totalCustomersSatisfied(int[] customers, int[] grumpy, int X) {


        int totalCustomerSatisfied = 0;
        int nonGrumpyCustomersSatisfied = 0;
        int grumpyCustomersSatisfied = 0;
        int rightIndex = 0;
        int leftIndex = 0;


        for (int i = 0; i < customers.length; i++) {

            if (grumpy[i] == 0) {

                nonGrumpyCustomersSatisfied = nonGrumpyCustomersSatisfied + customers[i];

            }


            if (grumpy[i] == 1 && i < X) {

                grumpyCustomersSatisfied = grumpyCustomersSatisfied + customers[i];

            }

        }

        totalCustomerSatisfied = nonGrumpyCustomersSatisfied + grumpyCustomersSatisfied;


        for (rightIndex = X; rightIndex < customers.length; rightIndex++) {


            leftIndex = rightIndex - X;


            if (grumpy[rightIndex] == 1) {
                grumpyCustomersSatisfied = grumpyCustomersSatisfied + customers[rightIndex];


            }

            if (grumpy[leftIndex] == 1) {
                grumpyCustomersSatisfied = grumpyCustomersSatisfied - customers[leftIndex];


            }

            if (nonGrumpyCustomersSatisfied + grumpyCustomersSatisfied > totalCustomerSatisfied) {

                totalCustomerSatisfied = nonGrumpyCustomersSatisfied + grumpyCustomersSatisfied;

            }


        }


        return totalCustomerSatisfied;
    }

}
