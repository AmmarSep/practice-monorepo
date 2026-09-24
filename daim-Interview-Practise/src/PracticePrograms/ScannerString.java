package PracticePrograms;

import java.util.Scanner;

public class ScannerString {
    public static void main(String[] args)
    {
        System.out.println("Enter any sentence for print: ");
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        System.out.println(text);

    }
}
