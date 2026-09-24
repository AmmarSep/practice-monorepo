package JavaSimplePrograms;

/**
 * This class demonstrates inheritance in Java
 * Inheritance is a mechanism where a new class is derived from an existing class
 */
public class InheritanceDemo {

    public static void main(String[] args) {
        System.out.println("===== Inheritance Demonstration =====\n");

        // Single Inheritance
        System.out.println("--- Single Inheritance ---\n");
        Employee employee = new Employee("John Doe", 30, "E12345", "Developer", 75000);
        employee.displayInfo();

        // Multilevel Inheritance
        System.out.println("\n--- Multilevel Inheritance ---\n");
        Manager manager = new Manager("Jane Smith", 40, "M98765", "Senior Manager", 120000, "Engineering");
        manager.displayInfo();

        // Hierarchical Inheritance
        System.out.println("\n--- Hierarchical Inheritance ---\n");
        // Create different vehicle types that inherit from Vehicle
        Car1 car = new Car1("Toyota", "Camry", 4);
        Motorcycle1 motorcycle = new Motorcycle1("Harley-Davidson", "Street 750", false);

        car.displayInfo();
        System.out.println();
        motorcycle.displayInfo();

        // Demonstrating method overriding
        System.out.println("\n--- Method Overriding in Inheritance ---\n");
        car.startEngine();
        motorcycle.startEngine();

        // Demonstrating the use of super keyword
        System.out.println("\n--- Using super Keyword ---\n");
        SubClass subClass = new SubClass();
        subClass.displayMethod();

        // Demonstrating instance of keyword
        System.out.println("\n--- Using instanceof Keyword ---\n");
        System.out.println("Is car an instance of Vehicle1? " + (car instanceof Vehicle1));
        System.out.println("Is motorcycle an instance of Motorcycle1? " + (motorcycle instanceof Motorcycle1));
        System.out.println("Is car an instance of Object? " + (car instanceof Object));

        // Demonstrating inheritance with constructors
        System.out.println("\n--- Inheritance with Constructors ---\n");
        Child child = new Child("Parent Value", "Child Value");
        child.display();

        // Demonstrating inheritance and type casting
        System.out.println("\n--- Inheritance and Type Casting ---\n");
        // Upcasting (implicit):
        Vehicle1 vehicle = new Car1("Honda", "Civic", 4); // Car1 reference to Vehicle1 variable
        vehicle.displayInfo();

        // Downcasting (explicit):
        if (vehicle instanceof Car1) {
            Car1 castedCar = (Car1) vehicle; // Explicit cast back to Car1
            System.out.println("Number of doors: " + castedCar.getNumberOfDoors());
        }
    }
}

/**
 * Base class for demonstrating single inheritance
 */
class Person1 {
    private String name;
    private int age;

    public Person1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
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
}

/**
 * Employee class inherits from Person1 (Single Inheritance)
 */
class Employee extends Person1 {
    private String employeeId;
    private String position;
    private double salary;

    public Employee(String name, int age, String employeeId, String position, double salary) {
        super(name, age);  // Call to parent constructor
        this.employeeId = employeeId;
        this.position = position;
        this.salary = salary;
    }

    // Override displayInfo method to include employee-specific information
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent's displayInfo method
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Position: " + position);
        System.out.println("Salary: $" + salary);
    }

    // Employee-specific methods
    public void work() {
        System.out.println(getName() + " is working as a " + position);
    }

    // Getters and setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

/**
 * Manager class inherits from Employee (Multilevel Inheritance)
 */
class Manager extends Employee {
    private String department;

    public Manager(String name, int age, String employeeId, String position, 
                 double salary, String department) {
        super(name, age, employeeId, position, salary);
        this.department = department;
    }

    // Override displayInfo method to include manager-specific information
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call Employee's displayInfo method
        System.out.println("Department: " + department);
    }

    // Manager-specific methods
    public void manage() {
        System.out.println(getName() + " is managing the " + department + " department");
    }

    // Getters and setters
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

/**
 * Base class for demonstrating hierarchical inheritance
 */
class Vehicle1 {
    private String brand;
    private String model;

    public Vehicle1(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
    }

    public void startEngine() {
        System.out.println("Vehicle engine started");
    }

    // Getters and setters
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}

/**
 * Car1 class inherits from Vehicle1 (Hierarchical Inheritance)
 */
class Car1 extends Vehicle1 {
    private int numberOfDoors;

    public Car1(String brand, String model, int numberOfDoors) {
        super(brand, model);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public void displayInfo() {
        System.out.println("Car Information:");
        super.displayInfo();
        System.out.println("Number of doors: " + numberOfDoors);
    }

    @Override
    public void startEngine() {
        System.out.println("Car engine started with key");
    }

    // Car-specific methods
    public void drive() {
        System.out.println("Driving the car");
    }

    // Getters and setters
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }
}

/**
 * Motorcycle1 class inherits from Vehicle1 (Hierarchical Inheritance)
 */
class Motorcycle1 extends Vehicle1 {
    private boolean hasSidecar;

    public Motorcycle1(String brand, String model, boolean hasSidecar) {
        super(brand, model);
        this.hasSidecar = hasSidecar;
    }

    @Override
    public void displayInfo() {
        System.out.println("Motorcycle Information:");
        super.displayInfo();
        System.out.println("Has sidecar: " + (hasSidecar ? "Yes" : "No"));
    }

    @Override
    public void startEngine() {
        System.out.println("Motorcycle engine started with kickstart");
    }

    // Motorcycle-specific methods
    public void wheelie() {
        System.out.println("Performing a wheelie!");
    }

    // Getters and setters
    public boolean isHasSidecar() {
        return hasSidecar;
    }

    public void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }
}

/**
 * SuperClass for demonstrating super keyword
 */
class SuperClass {
    int value = 10;

    public void displayMethod() {
        System.out.println("This is the SuperClass display method");
    }
}

/**
 * SubClass for demonstrating super keyword
 */
class SubClass extends SuperClass {
    int value = 20;

    @Override
    public void displayMethod() {
        System.out.println("This is the SubClass display method");
        System.out.println("SubClass value: " + value);
        System.out.println("SuperClass value: " + super.value);
        super.displayMethod();  // Call the parent's displayMethod
    }
}

/**
 * Parent class for demonstrating constructor inheritance
 */
class Parent {
    private String parentValue;

    public Parent() {
        System.out.println("Parent default constructor called");
    }

    public Parent(String parentValue) {
        this.parentValue = parentValue;
        System.out.println("Parent parameterized constructor called");
    }

    public String getParentValue() {
        return parentValue;
    }
}

/**
 * Child class for demonstrating constructor inheritance
 */
class Child extends Parent {
    private String childValue;

    public Child(String parentValue, String childValue) {
        super(parentValue);  // Call parent's parameterized constructor
        this.childValue = childValue;
        System.out.println("Child constructor called");
    }

    public void display() {
        System.out.println("Parent value: " + getParentValue());
        System.out.println("Child value: " + childValue);
    }
}
