package JavaSimplePrograms;

/**
 * This class demonstrates encapsulation in Java
 * Encapsulation is the mechanism of wrapping data (variables) and code acting on the data (methods) together as a single unit
 */
public class EncapsulationDemo {

    public static void main(String[] args) {
        System.out.println("===== Encapsulation Demonstration =====\n");

        // Creating objects of the encapsulated classes
        BankAccount account = new BankAccount("John Doe", "123456789", 1000.0);

        // Accessing data through public methods
        System.out.println("Initial Account Information:");
        System.out.println("Account Holder: " + account.getAccountHolder());
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Balance: $" + account.getBalance());

        // Cannot directly access the private fields
        // account.balance = 5000; // This will cause a compilation error

        // Updating data through controlled methods
        System.out.println("\nPerforming transactions:\n");

        // Deposit money
        account.deposit(500.0);
        System.out.println("After deposit of $500, balance is: $" + account.getBalance());

        // Withdraw money - successful withdrawal
        boolean withdrawalResult = account.withdraw(300.0);
        if (withdrawalResult) {
            System.out.println("Withdrawal of $300 successful. New balance: $" + account.getBalance());
        } else {
            System.out.println("Withdrawal of $300 failed. Insufficient funds.");
        }

        // Withdraw money - unsuccessful withdrawal (insufficient funds)
        withdrawalResult = account.withdraw(2000.0);
        if (withdrawalResult) {
            System.out.println("Withdrawal of $2000 successful. New balance: $" + account.getBalance());
        } else {
            System.out.println("Withdrawal of $2000 failed. Insufficient funds.");
        }

        // Demonstrating encapsulation for validation
        System.out.println("\nDemonstrating data validation:\n");

        // Trying to set invalid data
        account.setAccountHolder(""); // Empty name
        System.out.println("After attempting to set empty name, account holder is still: " + 
                         account.getAccountHolder());

        // Trying to set valid data
        account.setAccountHolder("Jane Smith");
        System.out.println("After setting valid name, account holder is: " + account.getAccountHolder());

        // Example with Student class
        System.out.println("\n--- Student Class Example ---\n");

        Student student = new Student();

        // Using setter methods to set values with validation
        student.setName("Alice Johnson");
        student.setAge(15);  // Valid age
        student.setGrade(90.5); // Valid grade

        System.out.println("Student Information:\n" + student);

        // Attempting to set invalid values
        System.out.println("\nAttempting to set invalid values:");

        student.setAge(-5); // Invalid age
        student.setGrade(110); // Invalid grade

        System.out.println("Student Information after invalid attempts:\n" + student);

        // Employee example with constructor initialization
        System.out.println("\n--- Employee Class Example ---\n");

        Employee1 employee = new Employee1("Bob Smith", 35, 50000);
        System.out.println(employee);

        // Salary increase with validation
        employee.increaseSalary(5000); // Valid increase
        System.out.println("\nAfter valid salary increase:\n" + employee);

        employee.increaseSalary(-1000); // Invalid increase
        System.out.println("\nAfter invalid salary increase:\n" + employee);
    }
}

/**
 * BankAccount class demonstrating encapsulation
 * Private data fields with public accessor methods
 */
class BankAccount {
    // Private data fields - not accessible from outside the class
    private String accountHolder;
    private String accountNumber;
    private double balance;

    // Constructor - initializes the private fields
    public BankAccount(String accountHolder, String accountNumber, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Getter methods - provide read access to private fields
    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Setter method with validation
    public void setAccountHolder(String accountHolder) {
        // Validate input before setting the field
        if (accountHolder != null && !accountHolder.trim().isEmpty()) {
            this.accountHolder = accountHolder;
        } else {
            System.out.println("Error: Account holder name cannot be empty.");
        }
    }

    // The account number should not be changed after creation
    // No setter method for accountNumber

    // Business logic methods that operate on the private fields
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + " to account");
        } else {
            System.out.println("Error: Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive.");
            return false;
        }

        if (amount > balance) {
            System.out.println("Error: Insufficient funds.");
            return false;
        }

        balance -= amount;
        System.out.println("Withdrew $" + amount + " from account");
        return true;
    }
}

/**
 * Student class demonstrating encapsulation with validation
 */
class Student {
    private String name;
    private int age;
    private double grade;

    // Default constructor
    public Student() {
        this.name = "Unknown";
        this.age = 0;
        this.grade = 0.0;
    }

    // Getters and setters with validation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: Name cannot be empty.");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 120) { // Reasonable age range
            this.age = age;
        } else {
            System.out.println("Error: Age must be between 0 and 120.");
        }
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade >= 0 && grade <= 100) { // Valid grade range
            this.grade = grade;
        } else {
            System.out.println("Error: Grade must be between 0 and 100.");
        }
    }

    // Useful method to print student information
    @Override
    public String toString() {
        return "Name: " + name + "\nAge: " + age + "\nGrade: " + grade;
    }
}

/**
 * Employee1 class demonstrating encapsulation with constructor initialization
 */
class Employee1 {
    private String name;
    private int age;
    private double salary;

    // Constructor with validation
    public Employee1(String name, int age, double salary) {
        // Validate and set the name
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown";
            System.out.println("Warning: Empty name provided, using 'Unknown' as default.");
        }

        // Validate and set the age
        if (age >= 18 && age <= 65) { // Typical working age range
            this.age = age;
        } else {
            this.age = 18; // Default to minimum working age
            System.out.println("Warning: Invalid age provided, using 18 as default.");
        }

        // Validate and set the salary
        if (salary >= 0) {
            this.salary = salary;
        } else {
            this.salary = 0;
            System.out.println("Warning: Negative salary provided, using 0 as default.");
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    // Business method with validation
    public void increaseSalary(double amount) {
        if (amount > 0) {
            salary += amount;
            System.out.println("Salary increased by $" + amount);
        } else {
            System.out.println("Error: Salary increase must be positive.");
        }
    }

    // Useful method to print employee information
    @Override
    public String toString() {
        return "Name: " + name + "\nAge: " + age + "\nSalary: $" + salary;
    }
}
