package PracticePrograms;

import java.util.Scanner;

//public class AreaOfCircle {
//    public static void main(String[] args) {
//        Scanner s = new Scanner(System.in);
//        System.out.println("Enter the radius:");
//        double r = s.nextDouble();
//        double area = (22*r*r)/7;
//        System.out.println("Area of circle: "+area);
//    }
//}

public class AreaOfTriangle
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the base dimension: ");
        int b = sc.nextInt();
        System.out.println("Enter the height dimension: ");
        int h =  sc.nextInt();
        int area = (b*h)/2;
        System.out.println("Area of triangle: " +area);
    }
}
