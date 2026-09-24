package MBRDI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateElementWithCount {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("apple");
        list.add("orange");
        list.add("banana");
        list.add("apple");

        Map<String, Integer> map = new HashMap<>();

        // Step 1: Count frequency using indexed for loop
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        // Step 2: Print duplicates and their occurrences
        Object[] keys = map.keySet().toArray();

        for (int i = 0; i < keys.length; i++) {
            String key = (String) keys[i];

            if (map.get(key) > 1) {
                System.out.println(key + " -> " + map.get(key));
            }
        }
    }
}
