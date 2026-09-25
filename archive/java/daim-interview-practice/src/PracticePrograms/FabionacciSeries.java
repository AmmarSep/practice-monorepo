package PracticePrograms;

import java.util.Scanner;

public class FabionacciSeries {
    public static void main(String[] args) {
        int num, a,b,c;
        a=0;b=0;c=1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of times : ");
        num = sc.nextInt();
        System.out.print("The fabionacci series is: ");
        for(int i =0; i<num ; i++)
        {
            a=b;
            b=c;
            c=a+b;
            System.out.println(a+"");
        }
    }
}
