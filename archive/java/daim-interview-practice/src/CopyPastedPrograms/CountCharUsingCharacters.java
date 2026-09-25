package CopyPastedPrograms;

import java.util.LinkedHashMap;
import java.util.Map;

public class CountCharUsingCharacters {
    public static void main(String[] args) {
        String name = "My name is Ammar Mohamed";
        String nameWithoutSpace = name.replaceAll(" ","");
        Map<Character, Integer>  map = new LinkedHashMap<Character, Integer>();
//        String[] nam = name.trim().split(" ");
        for(int i =0; i<nameWithoutSpace.length(); i++){
            char ch  = nameWithoutSpace.charAt(i);
            if(map.get(ch)== null)
                map.put(ch,1);
            else
                map.put(ch,map.get(ch)+1);
        }
        System.out.println(map);

    }
}
