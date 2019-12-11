/*
 * Author: Amit Sachu
 * Date: December 06, 2019
 * Description: Longest Substring Without Repeating Characters
 * LeetCode problem no: 76
 * URL: https://leetcode.com/problems/minimum-window-substring/
 */


package algorithms.slidingwindow;

import java.util.HashMap;
import java.util.Map;


/*
        Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
        Example:
        Input: S = "ADOBECODEBANC", T = "ABC"
        Output: "BANC"
        Note:
        If there is no such window in S that covers all characters in T, return the empty string "".
        If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
*/


/*
* Note: Solution  works when there is no repeating char to be searched in T string. It fails for below mentioned scenario.
* Action: Need to find the solution for below scenario
* Input: S = "AA", T = "AA"
* Expected Output: "AA"
*
* */


public class MinimumWindowSubstring {


    public static void main(String[] args) {


        System.out.println(minWindow("ADOBECODEBANC", "ABC"));

        System.out.println(minWindow("AA", "AA"));

    }


/*  Fast/Slow Algorithm for finding minimum Window matching string

    Step 1:
    1. Check Edge cases and return empty string
    //if input strings are null or empty, then return empty string
    //If length of pattern string is greater than searching string, then return empty string
    2. Define a tuple variable (integer array[] of length 2 and set the values to array[]={0,maximum integer value)
    // to compare with possible minimum window substring to decide the minimum
    3. Create two Hash maps to have <key, value> pair like <Character, Integers
    // One map to have all the characters from the pattern string stored as keys with values 0.
    // Other map to have occurrences or count of each character in pattern string
        -- e.g. If pattern string is like "ABC", each characters 'A', 'B', 'C' will have occurrence of 1
        -- If pattern string is like "AA", character 'A" should have occurrence of 2.
    4. Define two pointers variables for left and right index of sliding window
    5. Define a counter variable to store the length of pattern string to know the missing characters in minimum length string in the very beginning
    // e.g. If pattern string is "ABC", then missing characters in very beginning will be 3


    // Fast sliding window
    Step 2: Start forwarding the right index of sliding window on the given string where its needed to find the minimum length of given pattern.

    // For each character, check if its there in pattern string (by checking the HashMap that store all the characters of pattern string).
     -- if it exists,
        -- then decrement the missing character counter only  if there is a character found in the string,
            and and its occurrence in Search string is less than the occurrence in pattern string
        -- Increment the value of character by 1 in the map that stores the characters from pattern string (e.g. charMap)
           It will determine how many occurrence of that character are found in search string in total while finding the minimum length window
        -- Move the right pointer index to next character on Search string and repeat all above steps



    // Slow Sliding Window
    Step 3: Slow sliding window will come in action only when a substring is found where all characters from the pattern string are found in Search string

    // Check if missing character counter variable value has become 0 (means that all characters from the pattern string are found in Search string).
    // If Yes,
    // Get the left and right index number of the found substring and replace the values in the shortest[left, right].
    // Its time to move the slow sliding window or left pointer index ( means start loosing the characters from left end one by one to find another occurrence of substring
    // where all characters from the pattern string are found in Search string and
    // Check if left pointer index character exists in charMap. If exists, decrement the value of that character key by 1
    // When the left pointer is moved to next character , it will result in loosing one character that's found in pattern string.
    // That will result in increment the missing character counter variable by 1. Follow the below condition check to get it done
    // Check if value of character in charMap is less than total occurrence of that character in pattern string (stored in charOccurrenceMap)
    // increment the missing character counter variable by 1. it will result to stop Slow Sliding window while loop and move the control to fast sliding window
    // Move the left pointer index to next character and repeat all above listed steps


    Step 4: At the end, Check the right pointer index value. If no pattern string found, no shortest[] will not be changed and it will remain same as it was set in beginning.

    To return empty string if no pattern string found in Search string, check shortest[1] value if its still Interger.MAX_VALUE. If yes, return Empty string else return the substring using the shortest[] left and right index values stored in [0,1] positions.

*/

    public static String minWindow(String s, String t) {


        if(t.length() > s.length()) return "";

        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }

        int rightIndex = 0;
        int leftIndex = 0;

        //Set shortest string as a tuple with values to have maximum length string in beginning
        int shortestString[] = {0, Integer.MAX_VALUE};

        //set the counter variable with original total missing characters to be searched
        int missingCharacters = t.length();

        Map<Character, Integer> charMap = new HashMap<>();


        Map<Character, Integer> charOccurenceMap = new HashMap<>();


        for (int i = 0; i < t.length(); i++) {
            charMap.put(t.charAt(i), 0);

            if (charOccurenceMap.containsKey(t.charAt(i))) {
                charOccurenceMap.put(t.charAt(i), charOccurenceMap.get(t.charAt(i)) + 1);

            } else {
                charOccurenceMap.put(t.charAt(i), 1);
            }

        }


        // Loop to move the fast pointer
        for (rightIndex = 0; rightIndex < s.length(); rightIndex++) {
            if (charMap.containsKey(s.charAt(rightIndex))) {
                // decrement the missing character counter only
                // if there is a character found in the string and and its occurrence in Search string is less than the occurrence in pattern string
                if (charMap.get(s.charAt(rightIndex)) < charOccurenceMap.get(s.charAt(rightIndex))) {
                    missingCharacters -= 1;
                }
                // increment the value in charMap for matching character
                charMap.put(s.charAt(rightIndex), (charMap.get(s.charAt(rightIndex)) + 1));
            }


//            System.out.println("-----------Right Pointer Increment Loop--------------");
//            System.out.println("rightIndex:    " + rightIndex);
//            System.out.println("leftIndex:  " + leftIndex);
//            System.out.println("charMap:    " + charMap);
//            System.out.println("charOccuranceMap:    " + charOccuranceMap);
//            System.out.println("missingCharacters:  " + missingCharacters);


            //while loop to get the minimum length string
            // And, to move the slow pointer
            while (missingCharacters == 0) {
                if (rightIndex - leftIndex < shortestString[1] - shortestString[0]) {
                    shortestString[0] = leftIndex;
                    shortestString[1] = rightIndex;
                }

                if (charMap.containsKey(s.charAt(leftIndex))) {
                    charMap.put(s.charAt(leftIndex), (charMap.get(s.charAt(leftIndex)) - 1));

                    if (charMap.get(s.charAt(leftIndex)) < charOccurenceMap.get(s.charAt(leftIndex))) {
                        missingCharacters += 1;

                    }

                }

                leftIndex += 1;

//                System.out.println("-----------Left Pointer Increment Loop--------------");
//                System.out.println("rightIndex:    " + rightIndex);
//                System.out.println("leftIndex:  " + leftIndex);
//                System.out.println("charMap:    " + charMap);
//                System.out.println("charOccuranceMap:    " + charOccuranceMap);
//                System.out.println("missingCharacters:  " + missingCharacters);

            }

        }

        //If there is no such window in S that covers all characters in T, return the empty string "".
        if (shortestString[1] == Integer.MAX_VALUE) {
            return "";
        } else {
            //Substring EndIndex is exclusive so increment the value to 1 to get the correct substring
            return s.substring(shortestString[0], shortestString[1] + 1);
        }

    }

}
