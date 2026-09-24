package ConceptJavaPrograms;

import java.util.Scanner;

public class IfStringAndInt {
//    private static String Java_Program;

    public static void main(String[] args) {
        String s1= "Java_Program";
        System.out.println("Enter any sentence for print: ");
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
     /*   Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter the number(count) of names to be entered: ");
        int  count = sc1.nextInt();*/
//        System.out.println(text);
        if(text == s1){
            System.out.println("Best Program to learn");
        }
        else {
            System.out.println("nOT NORmAL PROGram");
        }

    }
      /*  if(count == 1){
            System.out.println("Best Program to learn");
        }
        else {
            System.out.println("nOT NORmAL PROGram");
        }

    }*/
}
