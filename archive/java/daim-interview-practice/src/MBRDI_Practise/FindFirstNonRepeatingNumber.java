package MBRDI_Practise;

import java.util.LinkedHashMap;
import java.util.Map;

public class FindFirstNonRepeatingNumber {
    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 2, 0, 4, 5, 2};
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for(int i = 0; i<arr.length; i++){
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == 1){
                System.out.println(entry.getKey());
                break;
            }
        }
    }
}
