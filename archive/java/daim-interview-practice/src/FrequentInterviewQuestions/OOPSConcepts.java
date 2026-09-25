package FrequentInterviewQuestions;

/**
 * This class demonstrates the core Object-Oriented Programming (OOP) concepts in Java
 * 1. Class and Object
 * 2. Encapsulation
 * 3. Inheritance
 * 4. Polymorphism
 * 5. Abstraction
 */
public class OOPSConcepts {

    public static void main(String[] args) {
        System.out.println("===== Object-Oriented Programming Concepts =====");

        // 1. Class and Object
        demonstrateClassAndObject();

        // 2. Encapsulation
        demonstrateEncapsulation();

        // 3. Inheritance
        demonstrateInheritance();

        // 4. Polymorphism
        demonstratePolymorphism();

        // 5. Abstraction
        demonstrateAbstraction();
    }

    /**
     * Demonstrates Class and Object concept
     * Class: A blueprint/template for creating objects
     * Object: An instance of a class
     */
    private static void demonstrateClassAndObject() {
        System.out.println("\n1. Class and Object:");

        // Creating objects of the Person class
        Persons person1 = new Persons("John", 25);
        Persons person2 = new Persons("Alice", 30);

        // Accessing object properties and methods
        System.out.println("Person 1: " + person1.getName() + ", " + person1.getAge());
        System.out.println("Person 2: " + person2.getName() + ", " + person2.getAge());

        person1.introduce();
        person2.introduce();
    }

    /**
     * Demonstrates Encapsulation
     * Encapsulation: Binding data (attributes) and code (methods) together as a single unit
     * and hiding the implementation details from the outside world
     */
    private static void demonstrateEncapsulation() {
        System.out.println("\n2. Encapsulation:");

        BankAccounts account = new BankAccounts("AC12345", 1000.0);

        // Can't access private members directly
        // System.out.println(account.accountNumber); // Compilation error
        // account.balance = 2000.0; // Compilation error

        // Access via public methods (getters and setters)
        System.out.println("Account: " + account.getAccountNumber());
        System.out.println("Balance: $" + account.getBalance());

        // Deposit and withdraw through methods that encapsulate the logic
        account.deposit(500.0);
        System.out.println("After deposit: $" + account.getBalance());

        account.withdraw(200.0);
        System.out.println("After withdrawal: $" + account.getBalance());

        // Method enforces rules (can't withdraw more than balance)
        account.withdraw(2000.0);
        System.out.println("After attempted excessive withdrawal: $" + account.getBalance());
    }

    /**
     * Demonstrates Inheritance
     * Inheritance: Mechanism where a new class inherits properties and behaviors from an existing class
     */
    private static void demonstrateInheritance() {
        System.out.println("\n3. Inheritance:");

        // Create instances of parent and child classes
        Animals animal = new Animals("Generic Animal", 5);
        Dogs dogs = new Dogs("Buddy", 3, "Golden Retriever");
        Cats cats = new Cats("Whiskers", 2, true);

        // Calling methods from the parent class
        animal.eat();
        animal.sleep();

        // Child class inherits parent methods
        dogs.eat();
        dogs.sleep();

        // Child class has its own methods
        dogs.bark();
        dogs.fetch();

        cats.eat();
        cats.sleep();
        cats.meow();
        System.out.println("Is indoor cat? " + (cats.isIndoor() ? "Yes" : "No"));
    }

    /**
     * Demonstrates Polymorphism
     * Polymorphism: Ability of an object to take on many forms
     * - Method Overloading: Same method name with different parameters in the same class
     * - Method Overriding: Same method name and parameters in different classes (inheritance)
     */
    private static void demonstratePolymorphism() {
        System.out.println("\n4. Polymorphism:");

        System.out.println("Method Overloading:");
        Calculators calc = new Calculators();
        System.out.println("Sum of 5 + 10 = " + calc.add(5, 10));
        System.out.println("Sum of 5.5 + 10.3 = " + calc.add(5.5, 10.3));
        System.out.println("Sum of 5 + 10 + 15 = " + calc.add(5, 10, 15));

        System.out.println("\nMethod Overriding:");
        // Creating an array of Animal references
        Animals[] animals = new Animals[3];
        animals[0] = new Animals("Generic Animal", 1);  // Parent class
        animals[1] = new Dogs("Rex", 2, "German Shepherd");  // Child class 1
        animals[2] = new Cats("Tom", 3, false);  // Child class 2

        // Polymorphic behavior - the makeSound method behaves differently
        // depending on the actual object type
        for (Animals a : animals) {
            System.out.print(a.getName() + " says: ");
            a.makeSound();  // This calls the appropriate overridden method
        }
    }

    /**
     * Demonstrates Abstraction
     * Abstraction: Hiding complex implementation details and showing only the necessary features
     * Achieved through abstract classes and interfaces
     */
    private static void demonstrateAbstraction() {
        System.out.println("\n5. Abstraction:");

        // Can't instantiate abstract class
        // Shape shape = new Shape(); // Compilation error

        // Using concrete implementations of abstract class
        Shapes circle = new Circles(5.0);
        Shapes rectangle = new Rectangles(4.0, 6.0);

        System.out.println("Circle area: " + circle.calculateArea());
        System.out.println("Rectangle area: " + rectangle.calculateArea());

        circle.draw();
        rectangle.draw();

        // Using interface
        Drawables[] drawables = new Drawables[3];
        drawables[0] = new Circles(3.0);  // Circle implements Drawable
        drawables[1] = new Rectangles(2.0, 3.0);  // Rectangle implements Drawable
        drawables[2] = new Triangles();  // Triangle only implements Drawable

        System.out.println("\nDrawing all shapes:");
        for (Drawables d : drawables) {
            d.draw();  // Polymorphic call to the draw method
        }
    }
}

// Classes for Class and Object demonstration
class Persons {
    private String name;
    private int age;

    public Persons(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void introduce() {
        System.out.println("Hello, my name is " + name + " and I am " + age + " years old.");
    }
}

// Classes for Encapsulation demonstration
class BankAccounts {
    private String accountNumber;  // private data members
    private double balance;        // encapsulated (hidden) from outside

    public BankAccounts(String accountNumber, double initialBalance) {
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

    // Methods that encapsulate the operations on data
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance");
        }
    }
}

// Classes for Inheritance demonstration
class Animals {
    private String name;
    private int age;

    public Animals(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }

    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

class Dogs extends Animals {  // Dog inherits from Animal
    private String breed;

    public Dogs(String name, int age, String breed) {
        super(name, age);  // Call to parent constructor
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void bark() {
        System.out.println(getName() + " is barking.");
    }

    public void fetch() {
        System.out.println(getName() + " is fetching.");
    }

    @Override
    public void makeSound() {  // Overriding parent method
        System.out.println("Woof! Woof!");
    }
}

class Cats extends Animals {  // Cat inherits from Animal
    private boolean indoor;

    public Cats(String name, int age, boolean indoor) {
        super(name, age);
        this.indoor = indoor;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public void meow() {
        System.out.println(getName() + " says meow.");
    }

    @Override
    public void makeSound() {  // Overriding parent method
        System.out.println("Meow! Meow!");
    }
}

// Classes for Polymorphism demonstration (Method Overloading)
class Calculators {
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

// Classes for Abstraction demonstration
abstract class Shapes {
    // Abstract method - no implementation
    public abstract double calculateArea();

    // Concrete method
    public void draw() {
        System.out.println("Drawing a shape");
    }
}

interface Drawables {
    void draw();  // Abstract method in interface
}

class Circles extends Shapes implements Drawables {
    private double radius;

    public Circles(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }
}

class Rectangles extends Shapes implements Drawables {
    private double width;
    private double height;

    public Rectangles(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a rectangle with width " + width + " and height " + height);
    }
}

class Triangles implements Drawables {
    // Only implements the interface, not the abstract class

    @Override
    public void draw() {
        System.out.println("Drawing a triangle");
    }
}
