import java.util.Scanner;

class Employee {
    String name;
    int id;
    double basic, da, hra, gross;
    
    void input(Scanner sc) {
        System.out.print("Enter ID: ");
        id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        name = sc.nextLine();
        System.out.print("Enter Basic Salary: ");
        basic = sc.nextDouble();
    }
    
    void calculate() {
        da = basic * 0.4;
        hra = basic * 0.2;
        gross = basic + da + hra;
    }
    
    void display() {
        System.out.println(id + "\t" + name + "\t" + basic + "\t" + da + "\t" + hra + "\t" + gross);
    }
}

public class Q11_EmployeeSalary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();
        Employee[] emp = new Employee[n];
        for (int i = 0; i < n; i++) {
            emp[i] = new Employee();
            emp[i].input(sc);
            emp[i].calculate();
        }
        System.out.println("\nID\tName\tBasic\tDA\tHRA\tGross");
        for (int i = 0; i < n; i++) {
            emp[i].display();
        }
        sc.close();
    }
}
