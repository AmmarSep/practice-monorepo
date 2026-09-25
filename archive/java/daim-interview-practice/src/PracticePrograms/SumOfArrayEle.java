package PracticePrograms;

public class SumOfArrayEle {
    public static void main(String[] args) {
        int[] arr = new int[]{43,53,90,79,89};
        int sum = 0;
        for(int i = 0; i<= arr.length-1; i++)
        {
            sum = sum +arr[i];
        }
        System.out.println("Sum of the array element: "+sum);
    }
}
