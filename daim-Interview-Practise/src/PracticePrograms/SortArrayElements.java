package PracticePrograms;

import java.util.Arrays;

public class SortArrayElements {
    public static void main(String[] args) {
        int[] arra = new int[] { 56,89,94,12,8,32,-2,5,9,3};
        System.out.println("Elements of original array : ");
        for(int i =0; i<arra.length; i++)
        {
            System.out.print(arra[i] + " ");
        }
        Arrays.sort(arra);
        System.out.println();
        System.out.println("Elements of array after sorting : " +Arrays.toString(arra));
    }
}
