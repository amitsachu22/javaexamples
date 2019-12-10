/*
 * Author: Amit Sachu
 * Date: December 06, 2019
 * Description: Longest Substring Without Repeating Characters
 * LeetCode problem no: 3
 */


package algorithms.slidingwindow;

import java.util.HashMap;
import java.util.Map;

// Given a string, find the length of the longest substring without repeating characters.
//
//        Example 1:
//
//        Input: "abcabcbb"
//        Output: 3
//        Explanation: The answer is "abc", with the length of 3.
//        Example 2:
//
//        Input: "bbbbb"
//        Output: 1
//        Explanation: The answer is "b", with the length of 1.
//        Example 3:
//
//        Input: "pwwkew"
//        Output: 3
//        Explanation: The answer is "wke", with the length of 3.
//        Note that the answer must be a substring, "pwke" is a subsequence and not a substring.


public class LongestSubstringWithoutRepeatingCharacters {


    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring("c"));

    }


    // Step 1: Have two pointers, startIndex and endIndex to calculate the length
    //         of substring with distinct chars
    //          Create a map with key as Character and value as Integer

    // Step 2: Loop through the given string and increment the endIndex with 1 for each loop iteration. (its confusing otherwise difference between startIndex and endIndex will come 0 for a single length char or distinct characters string
    //          add the each character of the given string and char's index
    //          position in a map with key as character and value as index of that char.
    //          Reason for putting values in such map is to replace the latest index of a char with the existing index (if any character gets repeated)
    //          it will be used to move the startIndex of sliding window

    // Step 3: During loop, check if the character already exits in the map, if exists then move the
    // startIndex pointer to index which is next to it.

    // Step 4: Calculate the maximum length by taking the difference of endendex and current.

    // Step 5: every time max is calculated, compare it with previous window's max and then keep new max if the
    // new max is bigger than old max

    public static int lengthOfLongestSubstring(String s) {


        if (s.isEmpty()) return 0;
        if (s.trim().isEmpty() || s.length() == 1) return 1;

        int startIndex = 0;
        int endIndex = 0;
        int currlen = 0;
        int longlen = 0;


        Map<Character, Integer> map = new HashMap<>();


        for (int stringIndex = 0; stringIndex < s.length(); stringIndex++) {
            endIndex++;

            if (map.containsKey(s.charAt(stringIndex))) {

                if (map.get(s.charAt(stringIndex)) > startIndex) {

                    startIndex = map.get(s.charAt(stringIndex));
                }
            }

            map.put(s.charAt(stringIndex), endIndex);

            currlen = endIndex - startIndex;

            if (currlen > longlen) longlen = currlen;

        }

        return longlen;
    }

}
