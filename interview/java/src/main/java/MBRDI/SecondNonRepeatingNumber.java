package MBRDI;

import java.util.LinkedHashMap;
import java.util.Map;

public class SecondNonRepeatingNumber {
    public static void main(String[] args) {

        int[] arr = {4, 5, 1, 2, 0, 4, 5, 2};

        Map<Integer, Integer> map = new LinkedHashMap<>();

        // Step 1: Count number frequency
        for (int i = 0; i < arr.length; i++) {
            int n = arr[i];
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        // Step 2: Find second non-repeated number
        int found = 0;

        for (int i = 0; i < arr.length; i++) {
            int n = arr[i];

            if (map.get(n) == 1) {
                found++;

                if (found == 2) {
                    System.out.println(n);
                    break;
                }
            }
        }
    }
}

