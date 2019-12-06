package algorithms.slidingwindow;
/*
       Leetcode: Diet Plan Performance
        Posted on September 1, 2019 by braindenny
        Diet Plan Performance
        Problem# 1176


        CheatSheet: Leetcode For Code Interview
        Tag: #slidingwindow
        A dieter consumes calories[i] calories on the i-th day. For every consecutive sequence of k days, they look at T, the total calories consumed during that sequence of k days:

        If T < lower, they performed poorly on their diet and lose 1 point;
        If T > upper, they performed well on their diet and gain 1 point;
        Otherwise, they performed normally and there is no change in points.
        Return the total number of points the dieter has after all calories.length days.

        Note that: The total points could be negative.

        Example 1:

        Input: calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
        Output: 0
        Explaination: calories[0], calories[1] < lower and calories[3], calories[4] > upper, total points = 0.
        Example 2:

        Input: calories = [3,2], k = 2, lower = 0, upper = 1
        Output: 1
        Explaination: calories[0] + calories[1] > upper, total points = 1.
        Example 3:

        Input: calories = [6,5,0,0], k = 2, lower = 1, upper = 5
        Output: 0
        Explaination: calories[0] + calories[1] > upper, calories[2] + calories[3] < lower, total points = 0.
        Constraints:

        1 <= k <= calories.length <= 10^5
        0 <= calories[i] <= 20000
        0 <= lower <= upper
        Github: code.dennyzhang.com

        Credits To: leetcode.com

*/


public class DietPlanPerformance {


    public static void main(String... args) {

        int[] calories = {1, 2, 3, 4};

        int k = 2;

        int lower = 3;

        int upper = 4;


        /*Print Total points using Brute Force approach*/
        System.out.println(calculateTotalPoints(calories, k, lower, upper));


        /*Print Total points using Sliding Window algorithm approach*/
        System.out.println(totalDieterPoints(calories, k, lower, upper));


    }

    /*Brute Force Approach algorithms with Big 0 complexities like O(n2) */
    public static int calculateTotalPoints(int[] calories, int k, int lower, int upper) {

        int i;
        int j;

        int t = 0;
        int output = 0;


        int len = calories.length;

        /*handle ArrayIndexOutOfBoundsException and Check less than 0 value for k */
        if (k <= 0 || k > len) throw new IllegalArgumentException("Invalid value of k");

        for (i = 0; i < len; i++) {


            for (j = i; j < i + k; j++) {

                t = t + calories[j];


            }

            //System.out.println(t);

            if (t < lower) {
                output = output - 1;
            }
            if (t > upper) {
                output = output + 1;
            }

            t = 0;

            if (j == len) return output;


        }


        return output;

    }


    /*Best Approach - Sliding Window Algorithm */
    /*Algorithm to calculate total number of points with O(n) complexity*/


    /* Steps to be performed
    // Step 1: Check for all edge cases like k value is 0, or greater than array size then return 0
    // Step 2: Calculate the sum of the first window in the first loop
    // Step 3: Now calculate the data for rest of the windows
    // Step 4: To calculate the window sum, first remove the first element that was added to the sum from the totalSum and
    //         add the current element to the total Sum. Let the loop run until the end of the array
    */
    public static int totalDieterPoints(int[] calories, int k, int lower, int upper) {

        int totalPoints = 0;
        int sumCalories = 0;
        int len = calories.length;

        /*handle ArrayIndexOutOfBoundsException and Check less than 0 value for k */
        if (k <= 0 || k > len) throw new IllegalArgumentException("Invalid value of k");

        for (int i = 0; i < k; i++) {

            sumCalories = sumCalories + calories[i];

        }

        if (sumCalories < lower) {
            totalPoints = totalPoints - 1;

        }

        if (sumCalories > upper) {

            totalPoints = totalPoints + 1;

        }

        for (int i = k; i < len; i++) {

            sumCalories = sumCalories + calories[i] - calories[i - k];

            //System.out.println(sumCalories);

            if (sumCalories < lower) {
                totalPoints = totalPoints - 1;

            }

            if (sumCalories > upper) {

                totalPoints = totalPoints + 1;

            }

        }


        return totalPoints;
    }


}