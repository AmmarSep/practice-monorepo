package FrequentInterviewQuestions;

/**
 * This class demonstrates Encapsulation in Java
 * 
 * Encapsulation is the technique of making the fields in a class private
 * and providing access to the fields via public methods (getters and setters).
 * 
 * It is one of the four fundamental OOP concepts, providing:
 * 1. Data Hiding - restricting direct access to data
 * 2. Controlled access - validating input before setting values
 * 3. Flexibility - ability to change implementation without affecting the API
 * 4. Reusability - encapsulated code can be reused more easily
 */
public class EncapsulationDemo {

    public static void main(String[] args) {
        System.out.println("===== Encapsulation Demonstration =====");

        // Basic encapsulation example
        demonstrateBasicEncapsulation();

        // Example with data validation
        demonstrateValidationInEncapsulation();

        // Example with read-only properties
        demonstrateReadOnlyProperties();

        // Example with write-only properties
        demonstrateWriteOnlyProperties();

        // Encapsulation with immutable class
        demonstrateImmutableClass();
    }

    /**
     * Demonstrates basic encapsulation with private fields and public getters/setters
     */
    private static void demonstrateBasicEncapsulation() {
        System.out.println("\n1. Basic Encapsulation:\n");

        // Create an encapsulated class instance
        EmployeeEncapsulation employee = new EmployeeEncapsulation();

        // Using setters to assign values
        employee.setId(101);
        employee.setName("John Doe");
        employee.setDepartment("Engineering");
        employee.setSalary(75000.0);

        // Using getters to retrieve values
        System.out.println("Employee Details:");
        System.out.println("ID: " + employee.getId());
        System.out.println("Name: " + employee.getName());
        System.out.println("Department: " + employee.getDepartment());
        System.out.println("Salary: $" + employee.getSalary());

        // Cannot access private fields directly
        // System.out.println(employee.name); // Compilation error
        // employee.salary = 100000; // Compilation error
    }

    /**
     * Demonstrates encapsulation with data validation in setters
     */
    private static void demonstrateValidationInEncapsulation() {
        System.out.println("\n2. Encapsulation with Validation:\n");

        BankAccount account = new BankAccount();

        // Set valid values
        account.setAccountNumber("ACC12345");
        account.setBalance(1000.0);
        account.setInterestRate(2.5);

        System.out.println("Account Details:");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Balance: $" + account.getBalance());
        System.out.println("Interest Rate: " + account.getInterestRate() + "%");

        // Attempt to set invalid values
        System.out.println("\nAttempting to set invalid values:");

        account.setBalance(-500);  // Should fail validation
        System.out.println("Balance after invalid set: $" + account.getBalance());

        account.setInterestRate(15.0);  // Should fail validation
        System.out.println("Interest rate after invalid set: " + account.getInterestRate() + "%");

        // Using methods that encapsulate business logic
        System.out.println("\nPerforming transactions:");
        account.deposit(500);  // Valid operation
        System.out.println("Balance after deposit: $" + account.getBalance());

        account.withdraw(300);  // Valid operation
        System.out.println("Balance after withdrawal: $" + account.getBalance());

        account.withdraw(2000);  // Should fail (insufficient funds)
        System.out.println("Balance after invalid withdrawal: $" + account.getBalance());
    }

    /**
     * Demonstrates read-only properties (only getters, no setters)
     */
    private static void demonstrateReadOnlyProperties() {
        System.out.println("\n3. Read-Only Properties:\n");

        // Properties like UUID are set once and cannot be changed
        Product product = new Product("Laptop", 999.99);

        System.out.println("Product Details:");
        System.out.println("UUID: " + product.getUuid());  // Read-only property
        System.out.println("Name: " + product.getName());
        System.out.println("Price: $" + product.getPrice());
        System.out.println("Creation Date: " + product.getCreationDate());  // Read-only property

        // Can change mutable properties
        product.setName("High-End Laptop");
        product.setPrice(1299.99);

        System.out.println("\nUpdated Product Details:");
        System.out.println("UUID: " + product.getUuid());  // Still the same
        System.out.println("Name: " + product.getName());  // Changed
        System.out.println("Price: $" + product.getPrice());  // Changed
        System.out.println("Creation Date: " + product.getCreationDate());  // Still the same
    }

    /**
     * Demonstrates write-only properties (only setters, no getters)
     */
    private static void demonstrateWriteOnlyProperties() {
        System.out.println("\n4. Write-Only Properties:\n");

        // Password is a typical write-only property for security
        User user = new User("jsmith", "password123");
        System.out.println("User created with username: " + user.getUsername());

        // Can't read the password
        // System.out.println("Password: " + user.getPassword()); // No getter available

        // But we can change it
        user.setPassword("newSecurePassword");
        System.out.println("Password has been changed");

        // Can verify password without exposing it
        System.out.println("Password verification: " + 
                user.verifyPassword("wrongPassword"));  // Should be false
        System.out.println("Password verification: " + 
                user.verifyPassword("newSecurePassword"));  // Should be true
    }

    /**
     * Demonstrates immutable class (a special form of encapsulation)
     */
    private static void demonstrateImmutableClass() {
        System.out.println("\n5. Immutable Class:\n");

        // Immutable objects cannot be changed after creation
        Point point = new Point(10, 20);
        System.out.println("Point: " + point);  // toString() output

        // Cannot modify the point
        // point.setX(15); // No setter available

        // To "change" it, create a new instance
        Point newPoint = point.translate(5, 10);
        System.out.println("Original point: " + point);  // Unchanged
        System.out.println("New point: " + newPoint);  // New instance

        // This is thread-safe and has no side effects
    }
}

// Basic encapsulation example
class EmployeeEncapsulation {
    // Private fields - not accessible directly from outside the class
    private int id;
    private String name;
    private String department;
    private double salary;

    // Public getters and setters - the only way to access or modify the private fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

// Encapsulation with validation example
class BankAccount {
    private String accountNumber;
    private double balance;
    private double interestRate;  // percentage

    // Getters and setters with validation
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        // Validation could be added here
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        // Validation
        if (balance < 0) {
            System.out.println("Error: Cannot set negative balance");
            return;
        }
        this.balance = balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        // Validation
        if (interestRate < 0 || interestRate > 10) {
            System.out.println("Error: Interest rate must be between 0% and 10%");
            return;
        }
        this.interestRate = interestRate;
    }

    // Business methods that encapsulate operations on the data
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Deposit amount must be positive");
            return;
        }
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive");
            return;
        }
        if (amount > balance) {
            System.out.println("Error: Insufficient funds");
            return;
        }
        balance -= amount;
        System.out.println("Withdrawn: $" + amount);
    }
}

// Read-only properties example
class Product {
    private final String uuid;  // Read-only (immutable)
    private String name;  // Mutable
    private double price;  // Mutable
    private final java.time.LocalDateTime creationDate;  // Read-only (immutable)

    public Product(String name, double price) {
        this.uuid = java.util.UUID.randomUUID().toString();  // Generated once
        this.name = name;
        this.price = price;
        this.creationDate = java.time.LocalDateTime.now();  // Set once at creation
    }

    // Getter for read-only property
    public String getUuid() {
        return uuid;
    }

    // Getter for read-only property
    public java.time.LocalDateTime getCreationDate() {
        return creationDate;
    }

    // Getter and setter for mutable property
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for mutable property
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            System.out.println("Error: Price cannot be negative");
            return;
        }
        this.price = price;
    }
}

// Write-only properties example (for sensitive data)
class User {
    private String username;
    private String passwordHash;  // Stored as hash for security

    public User(String username, String password) {
        this.username = username;
        this.setPassword(password);  // Hash and store the password
    }

    // Getter for non-sensitive data
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // No getter for password - write-only property
    public void setPassword(String password) {
        // In real applications, use a proper hashing algorithm
        this.passwordHash = hashPassword(password);
    }

    // Helper method to verify password without exposing it
    public boolean verifyPassword(String passwordToCheck) {
        return hashPassword(passwordToCheck).equals(this.passwordHash);
    }

    // Simplified password hashing (for demonstration only)
    private String hashPassword(String password) {
        // In a real application, use a secure hashing algorithm like BCrypt
        return "hashed:" + password;
    }
}

// Immutable class example
final class Point {  // final class - cannot be extended
    private final int x;  // final fields - cannot be changed after initialization
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Only getters, no setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Instead of modifying, create a new instance
    public Point translate(int dx, int dy) {
        return new Point(x + dx, y + dy);
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }

    // In a complete implementation, override equals() and hashCode()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point other = (Point) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
