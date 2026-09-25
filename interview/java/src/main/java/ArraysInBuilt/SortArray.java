package ArraysInBuilt;

import java.util.Arrays;

public class SortArray {
    public static void main(String[] args) {
        int[] ar = {5, 34,985, 53, 63, 6243};
        int[] br = {3, 53, 34, 345};
        Arrays.sort(ar);
        System.out.println(ar[2]);
        for(int arp : ar){
            System.out.println(arp);
        }
    }
}
