package HackerRankCG;

import java.util.Scanner;

public class IfHacker {


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int N =7;
        if(N%2!=0)
        {
            System.out.println("Weird");
        }
        else if(N%2==0 && N>=2 && N<=5)
        {
            System.out.println("Not Weird");
        }
        else if(N%2==0 && N>=6 && N<=20)
        {
            System.out.println("Weird");
        }
        else if(N%2==0 && N>20)
        {
            System.out.println("Not Weird");
        }
    }
}