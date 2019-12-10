package algorithms.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {


    public static void main(String[] args) {


        System.out.println(minWindow("ADOBECODEBANC", "ABC"));

    }


    public static String minWindow(String s, String t) {


        int rightIndex = 0;
        int leftIndex = 0;

        //Set shortest string as a tuple with values to have maximum length string in beginning
        // to compare with possible substring to decide the minimum
        int shortestString[] = {0, Integer.MAX_VALUE};


        int missingCharacters = t.length();

        Map<Character, Integer> charMap = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {

            charMap.put(t.charAt(i), 0);
        }

//        System.out.println(charMap);


        //int counter = 0;


        // Loop to move the fast pointer
        for (rightIndex = 0; rightIndex < s.length(); rightIndex++) {


            if (charMap.containsKey(s.charAt(rightIndex))) {


                // decrement the missing character counter only if there is only 1 matching character found in the string

                if (charMap.get(s.charAt(rightIndex)) == 0) {

                    missingCharacters -= 1;

                }


                // increment the value in charMap for matching character
                charMap.put(s.charAt(rightIndex), (charMap.get(s.charAt(rightIndex)) + 1));


            }
/*

            System.out.println("-----------Right Pointer Increment Loop--------------");
            System.out.println("rightIndex:    " + rightIndex);
            System.out.println("leftIndex:  " + leftIndex);
            System.out.println("charMap:    " + charMap);
            System.out.println("missingCharacters:  " + missingCharacters);
*/

            //while loop to get the minimum length string
            // And, to move the slow pointer
            while (missingCharacters == 0) {


                if (rightIndex - leftIndex < shortestString[1] - shortestString[0]) {


                    shortestString[0] = leftIndex;
                    shortestString[1] = rightIndex;

                }


                if (charMap.containsKey(s.charAt(leftIndex))) {


                    charMap.put(s.charAt(leftIndex), (charMap.get(s.charAt(leftIndex)) - 1));


                    if (charMap.get(s.charAt(leftIndex)) == 0) {

                        missingCharacters += 1;


                    }


                }

                leftIndex += 1;

/*

                System.out.println("-----------Left Pointer Increment Loop--------------");
                System.out.println("rightIndex:    " + rightIndex);
                System.out.println("leftIndex:  " + leftIndex);
                System.out.println("charMap:    " + charMap);
                System.out.println("missingCharacters:  " + missingCharacters);

*/

            }


        }


        //System.out.println(shortestString[]);
        //Substring EndIndex is exclusive so increment the value to 1 to get the correct substring

        return s.substring(shortestString[0], shortestString[1] + 1);


    }


}
