package PracticePrograms;

import java.util.LinkedHashMap;
import java.util.Map;

public class FindOccurenceChar {
    public static void main(String[] args) {


//    void findOccurrences() {
        String s = "The quick brown fox jumped over the lazy dog.";
        s= s.toLowerCase();
        Map<String, Integer> occurrences = new LinkedHashMap<String, Integer>();

        for (String ch : s.split("")) {
            Integer count = occurrences.get(ch);
            occurrences.put(ch, count == null ? 1 : count + 1);
        }
        System.out.println(occurrences);
//    }
}
}
