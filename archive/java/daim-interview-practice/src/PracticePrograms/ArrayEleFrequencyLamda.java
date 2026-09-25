package PracticePrograms;

import java.util.HashMap;
import java.util.Map;

// Java program to count frequencies of array items


public class ArrayEleFrequencyLamda {
    static void ghjghj(int arr[], int n) {
        Map<Integer, Integer> mp = new HashMap<>();

        // Traverse through array elements and
        // count frequencies
        for (int i = 0; i < n; i++) {
            mp.put(arr[i], mp.get(arr[i]) == null ? 1 : mp.get(arr[i]) + 1);
        }
        //System.out.println(mp);
        // To print elements according to first
        // occurrence, traverse array one more time
        // print frequencies of elements and mark
        // frequencies as -1 so that same element
        // is not printed multiple times.
        mp.forEach((k, v) -> System.out.println((k + " " + v)));
//            mp.strforEach(m-> {
//                System.out.println(m.g + " " + mp.get(arr[i]));
//            });
//            for (int i = 0; i < n; i++) {
//                    System.out.println(arr[i] + " " + mp.get(arr[i]));
//                    //mp.put(arr[i], -1);
//            }
    }

    // Driver code
    public static void main(String[] args) {
        int arr[] = {10, 20, 20, 10, 10, 20, 5, 20};
        int n = arr.length;
        ghjghj(arr, n);
    }
}
