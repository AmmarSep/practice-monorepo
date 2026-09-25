package PracticePrograms;

import java.util.Scanner;

public class Scanner_Array
{
    public static void main(String[] args)
    {
//        Scanner sc = new Scanner(System.in);
//        String[] arr = new String[2];
//        System.out.println("Enter two names to print: ");
//        Scanner sa =  new Scanner(System.in);
//        for (int i=0; i<= arr.length; i++)
//        {
//            arr[i]=sa.nextLine();
//            System.out.println(arr[i]);
//
//        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number(count) of names to be entered: ");
        int  count = sc.nextInt();
        sc.close();
        System.out.println("Count of the names to be entered: " +count);
        for (int i=0; i<=count; i++)
        {
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter name 01:");

            String name1 = scan.next();

            System.out.println("Enter name 02:");

            String name2 = scan.next();
            System.out.println("Print the name 01: " +name1);
            System.out.println("Print the name 02: " +name2);
        }

    }
}