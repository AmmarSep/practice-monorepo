package JavaSimplePrograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class demonstrates the difference between Comparable and Comparator interfaces in Java
 */
public class ComparableVsComparatorDemo {

    public static void main(String[] args) {
        System.out.println("===== Comparable vs Comparator Demonstration =====\n");

        // 1. Comparable Interface Example
        System.out.println("1. Comparable Interface Example\n");
        demonstrateComparable();

        // 2. Comparator Interface Example
        System.out.println("\n2. Comparator Interface Example\n");
        demonstrateComparator();

        // 3. Combining Comparable and Comparator
        System.out.println("\n3. Combining Comparable and Comparator\n");
        demonstrateCombined();

        // 4. Real-world example
        System.out.println("\n4. Real-world Example: Sorting Employees\n");
        demonstrateRealWorldExample();

        // 5. Java 8 Comparator enhancements
        System.out.println("\n5. Java 8 Comparator Enhancements\n");
        demonstrateJava8Comparators();
    }

    /**
     * Demonstrates the Comparable interface
     * Comparable provides a single sorting sequence - the natural ordering
     */
    private static void demonstrateComparable() {
        System.out.println("Using Comparable (natural ordering):");

        // Example 1: String implements Comparable
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Eve", "Charlie");
        System.out.println("Original list of names: " + names);

        Collections.sort(names); // Uses String's compareTo method (lexicographical order)
        System.out.println("Sorted names (natural order): " + names);

        // Example 2: Integer implements Comparable
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
        System.out.println("\nOriginal list of numbers: " + numbers);

        Collections.sort(numbers); // Uses Integer's compareTo method (ascending order)
        System.out.println("Sorted numbers (natural order): " + numbers);

        // Example 3: Custom class implementing Comparable
        List<Person> people = new ArrayList<>();
        people.add(new Person("John", 25));
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 22));
        people.add(new Person("Eve", 28));

        System.out.println("\nOriginal list of people:");
        printList(people);

        Collections.sort(people); // Uses Person's compareTo method (sorts by name)
        System.out.println("\nSorted people (by name using Comparable):");
        printList(people);

        // Example 4: Sorting in reverse order using Collections.reverseOrder()
        Collections.sort(people, Collections.reverseOrder());
        System.out.println("\nReverse sorted people (by name using Comparable):");
        printList(people);
    }

    /**
     * Demonstrates the Comparator interface
     * Comparator allows multiple sorting sequences
     */
    private static void demonstrateComparator() {
        // Create a list of people
        List<Person> people = new ArrayList<>();
        people.add(new Person("John", 25));
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 22));
        people.add(new Person("Eve", 28));
        people.add(new Person("Charlie", 25)); // Same age as John

        System.out.println("Original list of people:");
        printList(people);

        // Sort by age using a Comparator (anonymous class)
        System.out.println("\nSorting by age using Comparator (anonymous class):");
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        });
        printList(people);

        // Sort by age using a Comparator (lambda expression)
        System.out.println("\nSorting by age using Comparator (lambda):");
        Collections.sort(people, (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
        printList(people);

        // Sort by name length using a Comparator (lambda expression)
        System.out.println("\nSorting by name length using Comparator (lambda):");
        Collections.sort(people, (p1, p2) -> Integer.compare(p1.getName().length(), p2.getName().length()));
        printList(people);

        // Sort by age in descending order
        System.out.println("\nSorting by age in descending order using Comparator (lambda):");
        Collections.sort(people, (p1, p2) -> Integer.compare(p2.getAge(), p1.getAge())); // Note p2, p1 order
        printList(people);

        // Sorting strings by length
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Elizabeth", "Charlie");
        System.out.println("\nOriginal list of names: " + names);

        // Sort strings by length
        Collections.sort(names, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println("Names sorted by length: " + names);

        // Creating a named Comparator class
        Comparator<Person> ageComparator = new AgeComparator();
        Collections.sort(people, ageComparator);
        System.out.println("\nSorting by age using named Comparator class:");
        printList(people);
    }

    /**
     * Demonstrates combining Comparable and Comparator
     */
    private static void demonstrateCombined() {
        // Create a list of products
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", "Electronics", 1200.0));
        products.add(new Product("Smartphone", "Electronics", 800.0));
        products.add(new Product("Headphones", "Electronics", 150.0));
        products.add(new Product("Book", "Books", 25.0));
        products.add(new Product("Shirt", "Clothing", 35.0));
        products.add(new Product("Tablet", "Electronics", 300.0));

        System.out.println("Original list of products:");
        printList(products);

        // Sort using Comparable (natural order - by name)
        Collections.sort(products);
        System.out.println("\nProducts sorted by name (Comparable):");
        printList(products);

        // Sort using Comparator (by price)
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
        System.out.println("\nProducts sorted by price (Comparator):");
        printList(products);

        // Sort using Comparator (by category, then by price)
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                // First compare by category
                int categoryComparison = p1.getCategory().compareTo(p2.getCategory());
                if (categoryComparison != 0) {
                    return categoryComparison;
                }
                // If categories are the same, compare by price
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
        System.out.println("\nProducts sorted by category, then by price (Comparator):");
        printList(products);
    }

    /**
     * Demonstrates a real-world example: sorting employees
     */
    private static void demonstrateRealWorldExample() {
        // Create a list of employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John Smith", 34,"Engineering", "Developer", 2015));
        employees.add(new Employee("Alice Johnson", 56,"HR", "Tester", 2018));
        employees.add(new Employee("Bob Brown", 43,"Engineering", "Program Manager", 2010));
        employees.add(new Employee("Eve Davis", 59,"Marketing", "FE Developer", 2016));
        employees.add(new Employee("Charlie Wilson",27, "Engineering", "BE Developer", 2017));

        System.out.println("Original list of employees:");
        printList(employees);

        // Sorting employees by name (natural ordering from Comparable)
        Collections.sort(employees);
        System.out.println("\nEmployees sorted by name (Comparable):");
        printList(employees);

        // Sorting employees by department
        Collections.sort(employees, new DepartmentComparator());
        System.out.println("\nEmployees sorted by department (Comparator):");
        printList(employees);

        // Sorting employees by salary
        Collections.sort(employees, new SalaryComparator());
        System.out.println("\nEmployees sorted by salary (Comparator):");
        printList(employees);

        // Sorting employees by join year (seniority)
        Collections.sort(employees, new JoinYearComparator());
        System.out.println("\nEmployees sorted by join year (Comparator):");
        printList(employees);

        // Multiple level sorting: first by department, then by salary in descending order
        Comparator<Employee> departmentThenSalaryComparator = new DepartmentComparator()
                .thenComparing(new SalaryComparator().reversed());

        Collections.sort(employees, departmentThenSalaryComparator);
        System.out.println("\nEmployees sorted by department, then by salary (descending):");
        printList(employees);
    }

    /**
     * Demonstrates Java 8 enhancements to the Comparator interface
     */
    private static void demonstrateJava8Comparators() {
        // Create a list of employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John Smith", "Engineering", 75000, 2015));
        employees.add(new Employee("Alice Johnson", "HR", 65000, 2018));
        employees.add(new Employee("Bob Brown", "Engineering", 85000, 2010));
        employees.add(new Employee("Eve Davis", "Marketing", 70000, 2016));
        employees.add(new Employee("Charlie Wilson", "Engineering", 80000, 2017));

        System.out.println("Original list of employees:");
        printList(employees);

        // 1. Using Comparator.comparing() with method reference
        System.out.println("\nSorting using Comparator.comparing() with method reference:");
        Collections.sort(employees, Comparator.comparing(Employee::getDepartment));
        printList(employees);

        // 2. Using Comparator.thenComparing() for multi-level sorting
        System.out.println("\nMulti-level sorting with thenComparing():");
        Collections.sort(employees, 
                Comparator.comparing(Employee::getDepartment)
                          .thenComparing(Employee::getSalary, Comparator.reverseOrder()));
        printList(employees);

        // 3. Using Comparator.reversed() to reverse the ordering
        System.out.println("\nReversed sorting:");
        Collections.sort(employees, Comparator.comparing(Employee::getName).reversed());
        printList(employees);

        // 4. Using Comparator.nullsFirst() or nullsLast()
        // Create a list with some null values
        List<Employee> employeesWithNull = new ArrayList<>(employees);
        employeesWithNull.add(null); // Add a null employee
        employeesWithNull.add(null); // Add another null employee

        System.out.println("\nList with null values:");
        printListWithNulls(employeesWithNull);

        // Sort with nulls first
        System.out.println("\nSorting with nulls first:");
        Collections.sort(employeesWithNull, Comparator.nullsFirst(
                Comparator.comparing(Employee::getName)));
        printListWithNulls(employeesWithNull);

        // 5. Using naturalOrder() and reverseOrder()
        System.out.println("\nUsing naturalOrder() and reverseOrder():");
        employees.sort(Comparator.naturalOrder()); // Same as Collections.sort(employees)
        System.out.println("Natural order (by name):");
        printList(employees);

        employees.sort(Comparator.reverseOrder()); // Reverse of natural order
        System.out.println("\nReverse natural order (by name):");
        printList(employees);
    }

    /**
     * Helper method to print a list of objects
     */
    private static <T> void printList(List<T> list) {
        for (T item : list) {
            System.out.println("  " + item);
        }
    }

    /**
     * Helper method to print a list that may contain null values
     */
    private static <T> void printListWithNulls(List<T> list) {
        for (T item : list) {
            System.out.println("  " + (item == null ? "(null)" : item));
        }
    }

    /**
     * Person class implementing Comparable
     */
    static class Person implements Comparable<Person> {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return name + " (" + age + " years)";
        }

        @Override
        public int compareTo(Person other) {
            // Natural ordering: alphabetical by name
            return this.name.compareTo(other.name);
        }
    }

    /**
     * AgeComparator: Compares Person objects by age
     */
    static class AgeComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return Integer.compare(p1.getAge(), p2.getAge());
        }
    }

    /**
     * Employee class implementing Comparable
     */
    static class Employee implements Comparable<Employee> {
        private String name;
        private int age;
        private String department;
        private String position;
        private int joinYear;
        private double salary;

        // Constructor for demonstrateRealWorldExample (5 parameters)
        public Employee(String name, int age, String department, String position, int joinYear) {
            this.name = name;
            this.age = age;
            this.department = department;
            this.position = position;
            this.joinYear = joinYear;
            // Default salary based on position
            this.salary = calculateSalary(position);
        }

        // Constructor for demonstrateJava8Comparators (4 parameters)
        public Employee(String name, String department, double salary, int joinYear) {
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.joinYear = joinYear;
            this.age = 30; // Default age
            this.position = "Employee"; // Default position
        }

        private double calculateSalary(String position) {
            // Simple salary calculation based on position
            switch (position) {
                case "Developer": return 75000;
                case "Tester": return 65000;
                case "Program Manager": return 85000;
                case "FE Developer": return 70000;
                case "BE Developer": return 80000;
                default: return 60000;
            }
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getDepartment() {
            return department;
        }

        public String getPosition() {
            return position;
        }

        public int getJoinYear() {
            return joinYear;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return name + " (" + department + ", " + position + ", $" + salary + ", joined " + joinYear + ")";
        }

        @Override
        public int compareTo(Employee other) {
            // Natural ordering: alphabetical by name
            return this.name.compareTo(other.name);
        }
    }

    /**
     * DepartmentComparator: Compares Employee objects by department
     */
    static class DepartmentComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee e1, Employee e2) {
            return e1.getDepartment().compareTo(e2.getDepartment());
        }
    }

    /**
     * SalaryComparator: Compares Employee objects by salary
     */
    static class SalaryComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee e1, Employee e2) {
            return Double.compare(e1.getSalary(), e2.getSalary());
        }
    }

    /**
     * JoinYearComparator: Compares Employee objects by join year (seniority)
     */
    static class JoinYearComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee e1, Employee e2) {
            // Earlier join year means more senior (ascending order)
            return Integer.compare(e1.getJoinYear(), e2.getJoinYear());
        }
    }

    /**
     * Product class implementing Comparable
     */
    static class Product implements Comparable<Product> {
        private String name;
        private String category;
        private double price;

        public Product(String name, String category, double price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return name + " (" + category + ", $" + price + ")";
        }

        @Override
        public int compareTo(Product other) {
            // Natural ordering: alphabetical by name
            return this.name.compareTo(other.name);
        }
    }
}
