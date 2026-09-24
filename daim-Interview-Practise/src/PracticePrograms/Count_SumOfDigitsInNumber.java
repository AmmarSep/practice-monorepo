package PracticePrograms;

public class Count_SumOfDigitsInNumber {
    public static void main(String[] args) {
        int numd= 9239642;
        int sum = 0;
        while (numd>0)
        {
            sum = sum+numd%10;
            numd = numd/10;
        }
        System.out.println("Sum of digits in a number:"+sum);
    }
}
