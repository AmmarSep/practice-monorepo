package ConceptJavaPrograms;

import java.util.Scanner;

public class IfString {

    public static void main(String[] args) {
        System.out.println("Enter any sentence for print: ");
        String STR1 = "HTML";
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
//        System.out.println(text);
       /* if (text == "Java") {
            System.out.println("Best Program to learn");
        } else {
            System.out.println("nOT NORmAL PROGram");
        }*/
        if (text.equalsIgnoreCase("Java")){
            System.out.println("Learned Program");
        }
        else
            if(text.equalsIgnoreCase(STR1)){
                System.out.println("Front End Program");
            }
        else
        {
            System.out.println("New Program");
        }

    }
}
