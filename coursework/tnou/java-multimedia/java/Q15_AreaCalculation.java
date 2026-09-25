import java.util.Scanner;

interface Shape {
    void input();
    double area();
}

class Triangle implements Shape {
    double base, height;
    
    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter base and height: ");
        base = sc.nextDouble();
        height = sc.nextDouble();
    }
    
    public double area() {
        return 0.5 * base * height;
    }
}

class Rectangle implements Shape {
    double length, width;
    
    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter length and width: ");
        length = sc.nextDouble();
        width = sc.nextDouble();
    }
    
    public double area() {
        return length * width;
    }
}

public class Q15_AreaCalculation {
    public static void main(String[] args) {
        Triangle t = new Triangle();
        Rectangle r = new Rectangle();
        System.out.println("Triangle:");
        t.input();
        System.out.println("Area: " + t.area());
        System.out.println("\nRectangle:");
        r.input();
        System.out.println("Area: " + r.area());
    }
}
