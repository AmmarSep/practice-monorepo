package MBRDI_Practise;

import java.util.LinkedHashMap;
import java.util.Map;

public class FindSecondNonRepeatingChar {
    public static void main(String[] args) {
        String s = "swiss";
        Map<Character, Integer> map = new LinkedHashMap<>();
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int found = 0;
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(map.get(c)==1){
                found++;
                if(found==2){
                    System.out.println(c);
                    break;
                }
            }
        }
    }
}
