import java.util.Scanner;

interface Customer {
    void getDetails();
}

interface Tariff {
    void calculateBill();
}

class ElectricityBill implements Customer, Tariff {
    String name;
    int units;
    double bill;
    
    public void getDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        name = sc.nextLine();
        System.out.print("Enter units consumed: ");
        units = sc.nextInt();
    }
    
    public void calculateBill() {
        if (units <= 100) {
            bill = units * 1.5;
        } else if (units <= 300) {
            bill = 100 * 1.5 + (units - 100) * 2.5;
        } else {
            bill = 100 * 1.5 + 200 * 2.5 + (units - 300) * 4.0;
        }
    }
    
    void display() {
        System.out.println("\nCustomer: " + name);
        System.out.println("Units: " + units);
        System.out.println("Bill: Rs." + bill);
    }
}

public class Q14_ElectricityBill {
    public static void main(String[] args) {
        ElectricityBill eb = new ElectricityBill();
        eb.getDetails();
        eb.calculateBill();
        eb.display();
    }
}
