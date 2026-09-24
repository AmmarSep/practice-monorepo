// Program 11: Program for preparing Employee salary Report (Concept: Array of Objects)

import java.util.Scanner;

class Employee {
    String name;
    String empId;
    double basicSalary;
    double hra;
    double da;
    double grossSalary;

    Employee(String name, String empId, double basicSalary) {
        this.name = name;
        this.empId = empId;
        this.basicSalary = basicSalary;
        this.hra = basicSalary * 0.20;  // 20% HRA
        this.da = basicSalary * 0.10;   // 10% DA
        this.grossSalary = basicSalary + hra + da;
    }

    void displaySalarySlip() {
        System.out.println("\n===== SALARY SLIP =====");
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("HRA (20%): " + hra);
        System.out.println("DA (10%): " + da);
        System.out.println("Gross Salary: " + grossSalary);
        System.out.println("========================");
    }
}

public class P11_EmployeeSalary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of employees: ");
        int n = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Employee " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Employee ID: ");
            String empId = scanner.nextLine();

            System.out.print("Basic Salary: ");
            double basicSalary = scanner.nextDouble();
            scanner.nextLine();  // Consume newline

            employees[i] = new Employee(name, empId, basicSalary);
        }

        System.out.println("\n===== EMPLOYEE SALARY REPORT =====");
        for (Employee emp : employees) {
            emp.displaySalarySlip();
        }

        scanner.close();
    }
}
