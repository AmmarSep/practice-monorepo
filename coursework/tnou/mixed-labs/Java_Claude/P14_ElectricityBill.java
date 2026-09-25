// Program 14: Program for Electricity charge calculation (Concept: Implementing Multiple Inheritance using Interfaces)

import java.util.Scanner;

interface CustomerDetails {
    void getCustomerDetails();
}

interface BillCalculation {
    double calculateBill(int units);
}

class ElectricityBill implements CustomerDetails, BillCalculation {
    String customerName;
    String customerId;
    int unitsConsumed;
    double billAmount;

    @Override
    public void getCustomerDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        customerName = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        customerId = scanner.nextLine();
        System.out.print("Enter units consumed: ");
        unitsConsumed = scanner.nextInt();
    }

    @Override
    public double calculateBill(int units) {
        double amount = 0;

        if (units <= 100) {
            amount = units * 2.0;
        } else if (units <= 200) {
            amount = 100 * 2.0 + (units - 100) * 3.0;
        } else if (units <= 300) {
            amount = 100 * 2.0 + 100 * 3.0 + (units - 200) * 4.0;
        } else {
            amount = 100 * 2.0 + 100 * 3.0 + 100 * 4.0 + (units - 300) * 5.0;
        }

        return amount;
    }

    void displayBill() {
        billAmount = calculateBill(unitsConsumed);

        System.out.println("\n===== ELECTRICITY BILL =====");
        System.out.println("Customer Name: " + customerName);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Units Consumed: " + unitsConsumed);
        System.out.println("Bill Amount: Rs. " + billAmount);
        System.out.println("============================");
    }
}

public class P14_ElectricityBill {
    public static void main(String[] args) {
        ElectricityBill bill = new ElectricityBill();
        bill.getCustomerDetails();
        bill.displayBill();
    }
}
