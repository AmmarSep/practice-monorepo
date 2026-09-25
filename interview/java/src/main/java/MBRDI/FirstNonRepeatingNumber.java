package MBRDI;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatingNumber {
    public static void main(String[] args) {

        int[] arr = {4, 5, 1, 2, 0, 4, 5, 2};

        Map<Integer, Integer> map = new LinkedHashMap<>();

        // Step 1: Count number frequency
        for (int i = 0; i < arr.length; i++) {
            int n = arr[i];
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        // Step 2: Find first non-repeated number
        for (int i = 0; i < arr.length; i++) {
            int n = arr[i];

            if (map.get(n) == 1) {
                System.out.println(n);
                break;
            }
        }
    }
}
