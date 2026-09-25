package MBRDI_Practise;

import java.util.LinkedHashMap;
import java.util.Map;

public class CountFrequencyOfChar {
    public static void main(String[] args) {
        String s = "aabbcc";
        Map<Character, Integer> map = new LinkedHashMap<>();
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        System.out.println(map);
    }
}
