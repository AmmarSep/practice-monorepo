package MBRDI;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatingChar {
    public static void main(String[] args) {

        String s = "swiss";

        Map<Character, Integer> map = new LinkedHashMap<>();

        // Step 1: Count character frequency (same pattern as before)
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Step 2: Find first non-repeated character (FOR loop, not for-each)
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (map.get(c) == 1) {
                System.out.println(c);
                break;
            }
        }
    }
}
