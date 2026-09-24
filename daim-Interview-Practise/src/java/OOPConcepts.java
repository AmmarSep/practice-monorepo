package java;

/**
 * This class demonstrates Object-Oriented Programming (OOP) concepts in Java
 * 1. Classes and Objects
 * 2. Inheritance
 * 3. Polymorphism
 * 4. Encapsulation
 * 5. Abstraction
 */
public class OOPConcepts {

    public static void main(String[] args) {
        System.out.println("===== OOP Concepts Demonstration =====");
        
        // Demonstrate Classes and Objects
        demonstrateClassesAndObjects();
        
        // Demonstrate Inheritance
        demonstrateInheritance();
        
        // Demonstrate Polymorphism
        demonstratePolymorphism();
        
        // Demonstrate Encapsulation
        demonstrateEncapsulation();
        
        // Demonstrate Abstraction
        demonstrateAbstraction();
    }
    
    /**
     * Classes and Objects are the basic building blocks of OOP
     * - Class: A blueprint or template for creating objects
     * - Object: An instance of a class
     */
    private static void demonstrateClassesAndObjects() {
        System.out.println("\n1. Classes and Objects:\n");
        
        // Creating objects (instances) of the Person class
        Person person1 = new Person("John", 30);
        Person person2 = new Person("Jane", 25);
        
        // Using object methods
        System.out.println("Person 1: " + person1.getName() + ", " + person1.getAge() + " years old");
        System.out.println("Person 2: " + person2.getName() + ", " + person2.getAge() + " years old");
        
        // Modifying object state
        person1.setAge(31);
        System.out.println("Person 1 after birthday: " + person1.getName() + ", " + person1.getAge() + " years old");
        
        // Calling an instance method
        person1.greet();
        person2.greet();
    }
    
    /**
     * Inheritance allows a class to inherit properties and methods from another class
     * - Parent/Super/Base class: The class being inherited from
     * - Child/Sub/Derived class: The class that inherits from another class
     */
    private static void demonstrateInheritance() {
        System.out.println("\n2. Inheritance:\n");
        
        // Creating objects of derived classes
        Student student = new Student("Alice", 20, "CS101");
        Employee employee = new Employee("Bob", 35, "Developer");
        
        // Accessing inherited methods
        System.out.println("Student: " + student.getName() + ", " + student.getAge() + " years old");
        System.out.println("Employee: " + employee.getName() + ", " + employee.getAge() + " years old");
        
        // Accessing specific methods of derived classes
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Employee Job: " + employee.getJobTitle());
        
        // Overridden methods behave differently
        student.greet();  // Uses the overridden method in Student
        employee.greet(); // Uses the overridden method in Employee
    }
    
    /**
     * Polymorphism allows objects to be treated as instances of their parent class
     * - Method Overriding: Redefining a method in a subclass
     * - Method Overloading: Multiple methods with the same name but different parameters
     */
    private static void demonstratePolymorphism() {
        System.out.println("\n3. Polymorphism:\n");
        
        // Array of Person references, but holding different types of objects
        Person[] people = new Person[3];
        people[0] = new Person("John", 30);
        people[1] = new Student("Alice", 20, "CS101");
        people[2] = new Employee("Bob", 35, "Developer");
        
        // Polymorphic method calls - same method call, different behavior
        System.out.println("Demonstrating Method Overriding (Runtime Polymorphism):");
        for (Person person : people) {
            // The greet() method behaves differently depending on the actual object type
            person.greet();
        }
        
        // Method overloading demonstration
        System.out.println("\nDemonstrating Method Overloading (Compile-time Polymorphism):");
        Calculator1 calc = new Calculator1();
        System.out.println("Sum of 5 and 3: " + calc.add(5, 3));
        System.out.println("Sum of 5.2 and 3.8: " + calc.add(5.2, 3.8));
        System.out.println("Sum of 1, 2, and 3: " + calc.add(1, 2, 3));
    }
    
    /**
     * Encapsulation is the bundling of data and methods that operate on that data
     * - Data Hiding: Making fields private and accessible only through methods
     * - Getters and Setters: Methods to access and modify private fields
     */
    private static void demonstrateEncapsulation() {
        System.out.println("\n4. Encapsulation:\n");
        
        // Create a BankAccount object with encapsulated data
        BankAccount account = new BankAccount("123456", 1000.0);
        
        // Cannot access private fields directly
        // This would cause a compilation error: account.balance = -500;
        
        // Must use public methods to interact with the object
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Initial Balance: $" + account.getBalance());
        
        // Deposit money
        account.deposit(500.0);
        System.out.println("After deposit: $" + account.getBalance());
        
        // Withdraw money
        boolean withdrawalSuccess = account.withdraw(200.0);
        System.out.println("Withdrawal successful: " + withdrawalSuccess);
        System.out.println("After withdrawal: $" + account.getBalance());
        
        // Try to withdraw more than the balance
        withdrawalSuccess = account.withdraw(2000.0);
        System.out.println("Withdrawal successful: " + withdrawalSuccess);
        System.out.println("Final balance: $" + account.getBalance());
    }
    
    /**
     * Abstraction is hiding the implementation details and showing only functionality
     * - Abstract Classes: Cannot be instantiated, may contain abstract methods
     * - Interfaces: Define a contract for classes to implement
     */
    private static void demonstrateAbstraction() {
        System.out.println("\n5. Abstraction:\n");
        
        // Cannot instantiate abstract classes directly
        // This would cause a compilation error: Shape shape = new Shape();
        
        // Create objects of concrete subclasses
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);
        
        // Use abstract methods - implementation details are hidden
        System.out.println("Circle area: " + circle.calculateArea());
        System.out.println("Rectangle area: " + rectangle.calculateArea());
        
        // Using interfaces
        System.out.println("\nDemonstrating Interfaces:");
        
        Drawable[] drawables = new Drawable[2];
        drawables[0] = new Circle(3.0);  // Circle implements Drawable
        drawables[1] = new Rectangle(2.0, 4.0);  // Rectangle implements Drawable
        
        // Call interface methods
        for (Drawable drawable : drawables) {
            drawable.draw();
        }
    }
    
    // Basic class for demonstrating classes and objects
    static class Person {
        // Instance variables (fields)
        private String name;
        private int age;
        
        // Constructor
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        // Getters and setters
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public int getAge() {
            return age;
        }
        
        public void setAge(int age) {
            this.age = age;
        }
        
        // Instance method
        public void greet() {
            System.out.println("Hello, my name is " + name);
        }
    }
    
    // Derived class for demonstrating inheritance
    static class Student extends Person {
        private String studentId;
        
        public Student(String name, int age, String studentId) {
            super(name, age);  // Call the parent constructor
            this.studentId = studentId;
        }
        
        public String getStudentId() {
            return studentId;
        }
        
        // Method overriding
        @Override
        public void greet() {
            System.out.println("Hi, I'm " + getName() + ", a student with ID " + studentId);
        }
    }
    
    // Another derived class for demonstrating inheritance
    static class Employee extends Person {
        private String jobTitle;
        
        public Employee(String name, int age, String jobTitle) {
            super(name, age);
            this.jobTitle = jobTitle;
        }
        
        public String getJobTitle() {
            return jobTitle;
        }
        
        // Method overriding
        @Override
        public void greet() {
            System.out.println("Hello, I'm " + getName() + ", working as a " + jobTitle);
        }
    }
    
    // Class for demonstrating method overloading
    static class Calculator1 {
        // Method overloading - same name, different parameters
        public int add(int a, int b) {
            return a + b;
        }
        
        public double add(double a, double b) {
            return a + b;
        }
        
        public int add(int a, int b, int c) {
            return a + b + c;
        }
    }
    
    // Class for demonstrating encapsulation
    static class BankAccount {
        private String accountNumber;  // Private field
        private double balance;        // Private field
        
        public BankAccount(String accountNumber, double initialBalance) {
            this.accountNumber = accountNumber;
            this.balance = initialBalance;
        }
        
        // Getter methods
        public String getAccountNumber() {
            return accountNumber;
        }
        
        public double getBalance() {
            return balance;
        }
        
        // Methods to modify the private fields
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            }
        }
        
        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            }
            return false;
        }
    }
    
    // Abstract class for demonstrating abstraction
    static abstract class Shape {
        // Abstract method - no implementation
        public abstract double calculateArea();
        
        // Concrete method
        public void displayType() {
            System.out.println("This is a shape.");
        }
    }
    
    // Interface for demonstrating abstraction
    interface Drawable {
        void draw();  // Abstract method in interface
    }
    
    // Concrete class implementing abstract class and interface
    static class Circle extends Shape implements Drawable {
        private double radius;
        
        public Circle(double radius) {
            this.radius = radius;
        }
        
        // Implementing abstract method
        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }
        
        // Implementing interface method
        @Override
        public void draw() {
            System.out.println("Drawing a circle with radius " + radius);
        }
    }
    
    // Another concrete class implementing abstract class and interface
    static class Rectangle extends Shape implements Drawable {
        private double width;
        private double height;
        
        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        
        // Implementing abstract method
        @Override
        public double calculateArea() {
            return width * height;
        }
        
        // Implementing interface method
        @Override
        public void draw() {
            System.out.println("Drawing a rectangle with width " + width + " and height " + height);
        }
    }
}