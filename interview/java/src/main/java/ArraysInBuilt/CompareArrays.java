package ArraysInBuilt;

import java.util.Arrays;

public class CompareArrays {
    public static void main(String[] args) {
        int[] ar =  {5,563,435,34,345,6423};
        int[] br =  {1,23,34,53};
        int result = Arrays.compare(ar,br);
        System.out.println(result);
    }
}
