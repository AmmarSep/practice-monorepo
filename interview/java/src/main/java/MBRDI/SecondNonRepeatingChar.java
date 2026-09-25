package MBRDI;

import java.util.LinkedHashMap;
import java.util.Map;

public class SecondNonRepeatingChar {
    public static void main(String[] args) {
        // Step 1: Declare the string
        String s = "swiss";
        // Step 2: LinkedHashMap to preserve order
        Map<Character, Integer> map = new LinkedHashMap<>();

        // Step 3: Count character frequency
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Step 4: Find second non-repeated character
        int found = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (map.get(c) == 1) {
                found++;

                if (found == 2) {
                    System.out.println(c);
                    break;
                }
            }
        }
    }
}

