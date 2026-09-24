package ArraysInBuilt;

import java.util.Arrays;

public class WritingArrays {
    public static void main(String[] args) {
        int[] arrayNumbers1= {324,345,634,323,100,4};
        int oneHundred = arrayNumbers1[4];
        int[] arrayNumbers2 = new int[5];
        arrayNumbers2[0]= 1;
        arrayNumbers2[1]= 3;
        arrayNumbers2[2]= 5;
        arrayNumbers2[3]= 7;
        arrayNumbers2[4]= 9;
        System.out.println("Numbers in the array set 1 :" +arrayNumbers1);
        System.out.println("Numbers in the array set 2 :" +arrayNumbers2);
        System.out.println("Numbers in the array set 2 :" + Arrays.toString(arrayNumbers1));
        System.out.println("Numbers in the array set 2 :" + Arrays.toString(arrayNumbers2));
        System.out.println("Wrong length in array set 2:" + Arrays.toString(arrayNumbers2).length());
        System.out.println("Right length in array set 2:" + arrayNumbers2.length);
        System.out.println(oneHundred);
    }
}
