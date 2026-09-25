package ArraysInBuilt;

import java.util.Arrays;

public class CopyOf {
    public static void main(String[] args) {
        int[] ar = {5, 34, 53, 63, 6243};
        int[] br = {3, 53, 34, 345};
        int[] cr = Arrays.copyOf(ar, 2);
        for (int ind : cr) {
            System.out.println(ind);
        }
        int[] dr = Arrays.copyOf(br,3);
        for(int i=0; i<dr.length; i++){
            System.out.println(dr[i]);
        }
    }
}
