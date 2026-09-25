package MBRDI_Practise;

import java.util.LinkedHashMap;
import java.util.Map;

public class FindSecondNonRepeatingNumber {
    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 2, 0, 4, 5, 2};
        Map<Integer, Integer> map = new LinkedHashMap<>();
        // Step 1: Count frequency
        for(int i = 0; i<arr.length; i++){
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        int found = 0;
        // Step 2: Find second non-repeated number using index for loop
        for(int i = 0; i<arr.length; i++){
            int n = arr[i];
            if(map.get(n)==1){
                found++;
                if(found==2){
                    System.out.println(n);
                    break;
                }
            }
        }
    }
}
