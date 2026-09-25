package MBRDI_Practise;

import java.util.LinkedHashMap;
import java.util.Map;

public class FindFirstNonRepeatingChar {
    public static void main(String[] args) {
        String s = "aabbcdeff";
        Map<Character, Integer> map = new LinkedHashMap<>();
        // Step 1: Count character frequency
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        // Step 2: Find first non-repeated character
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(map.get(c)==1){
                System.out.println(c);
                break;
            }
        }
    }
}
