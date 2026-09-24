package ArraysInBuilt;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] ar = {5,8,30,35,57};
        int result = Arrays.binarySearch(ar, 30);
        System.out.println(result);
    }
}
