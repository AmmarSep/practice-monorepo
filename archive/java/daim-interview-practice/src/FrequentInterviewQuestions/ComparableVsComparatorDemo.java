package FrequentInterviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class demonstrates the difference between Comparable and Comparator interfaces in Java
 * 
 * Comparable: Used for defining the natural ordering of a class
 * - Internal to the class being compared
 * - Has compareTo() method
 * - Class can have only one natural ordering
 * 
 * Comparator: Used for defining custom ordering
 * - External to the class being compared
 * - Has compare() method
 * - Multiple comparators can be created for different sorting criteria
 */
public class ComparableVsComparatorDemo {

    public static void main(String[] args) {
        System.out.println("===== Comparable vs Comparator Demonstration =====");

        // 1. Using Comparable (Natural Ordering)
        demonstrateComparable();

        // 2. Using Comparator (Custom Ordering)
        demonstrateComparator();

        // 3. When to use which interface
        demonstrateWhenToUseWhich();

        // 4. Multiple Comparators
        demonstrateMultipleComparators();

        // 5. Combining Comparators
        demonstrateCombiningComparators();
    }

    /**
     * Demonstrates the Comparable interface for natural ordering
     */
    private static void demonstrateComparable() {
        System.out.println("\n1. Using Comparable (Natural Ordering):\n");

        // Creating a list of ComparablePerson objects (which implement Comparable)
        List<ComparablePerson> people = new ArrayList<>();
        people.add(new ComparablePerson("John", 28));
        people.add(new ComparablePerson("Alice", 22));
        people.add(new ComparablePerson("Bob", 31));
        people.add(new ComparablePerson("Carol", 26));

        System.out.println("Original list of people:");
        printList(people);

        // Sorting using natural ordering (defined by compareTo method)
        Collections.sort(people);  // Uses Person's compareTo method

        System.out.println("\nSorted by natural ordering (age):");
        printList(people);

        // Another example with simple types
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Carol");

        System.out.println("\nOriginal list of names:");
        printList(names);

        Collections.sort(names);  // String implements Comparable

        System.out.println("\nSorted names (natural ordering):");
        printList(names);

        // Comparing ComparablePerson objects directly
        ComparablePerson person1 = new ComparablePerson("John", 28);
        ComparablePerson person2 = new ComparablePerson("Alice", 22);

        int comparisonResult = person1.compareTo(person2);
        System.out.println("\nComparing " + person1.getName() + " with " + person2.getName() + ": " + comparisonResult);
        System.out.println("Result > 0 means " + person1.getName() + " is 'greater than' " + person2.getName());
    }

    /**
     * Demonstrates the Comparator interface for custom ordering
     */
    private static void demonstrateComparator() {
        System.out.println("\n2. Using Comparator (Custom Ordering):\n");

        // Creating a list of Book objects (which don't implement Comparable)
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 180));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 281));
        books.add(new Book("1984", "George Orwell", 328));
        books.add(new Book("Pride and Prejudice", "Jane Austen", 432));

        System.out.println("Original list of books:");
        printBooks(books);

        // Sort by title using a comparator
        Collections.sort(books, new TitleComparator());

        System.out.println("\nBooks sorted by title:");
        printBooks(books);

        // Sort by author using a comparator
        Collections.sort(books, new AuthorComparator());

        System.out.println("\nBooks sorted by author:");
        printBooks(books);

        // Sort by page count using a comparator
        Collections.sort(books, new PageCountComparator());

        System.out.println("\nBooks sorted by page count:");
        printBooks(books);

        // Using anonymous inner class for comparator
        Comparator<Book> titleLengthComparator = new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getTitle().length() - b2.getTitle().length();
            }
        };

        Collections.sort(books, titleLengthComparator);

        System.out.println("\nBooks sorted by title length:");
        printBooks(books);

        // Using lambda expression for comparator (Java 8+)
        Collections.sort(books, (b1, b2) -> b1.getAuthor().length() - b2.getAuthor().length());

        System.out.println("\nBooks sorted by author name length:");
        printBooks(books);
    }

    /**
     * Demonstrates when to use Comparable vs Comparator
     */
    private static void demonstrateWhenToUseWhich() {
        System.out.println("\n3. When to Use Which Interface:\n");

        System.out.println("Use Comparable when:");
        System.out.println("- The class has a clear, natural ordering");
        System.out.println("- You are the owner of the class and can modify it");
        System.out.println("- You want to enable objects to be sorted or used in ordered collections like TreeMap");
        System.out.println("- There is only one sensible way to order the objects");

        System.out.println("\nExamples of classes that implement Comparable:");
        System.out.println("- String (alphabetical order)");
        System.out.println("- Integer, Double, etc. (numerical order)");
        System.out.println("- Date (chronological order)");

        System.out.println("\nUse Comparator when:");
        System.out.println("- You want multiple ways to sort objects");
        System.out.println("- You can't modify the class (third-party library)");
        System.out.println("- You want to provide sorting that's not the natural ordering");
        System.out.println("- You need context-specific or temporary ordering");

        // Example with Student class that implements Comparable by GPA
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", 3.8, 21));
        students.add(new Student("Alice", 3.9, 20));
        students.add(new Student("Bob", 3.5, 22));
        students.add(new Student("Carol", 4.0, 19));

        System.out.println("\nOriginal list of students:");
        printStudents(students);

        // Sort by natural ordering (GPA)
        Collections.sort(students);  // Using Comparable

        System.out.println("\nStudents sorted by natural ordering (GPA):");
        printStudents(students);

        // Sort by age using a Comparator
        Collections.sort(students, new AgeComparator());  // Using Comparator

        System.out.println("\nStudents sorted by age (using Comparator):");
        printStudents(students);
    }

    /**
     * Demonstrates using multiple comparators
     */
    private static void demonstrateMultipleComparators() {
        System.out.println("\n4. Multiple Comparators:\n");

        // Creating employees with different departments and salaries
        List<ComparableEmployee> employees = new ArrayList<>();
        employees.add(new ComparableEmployee("John", "Engineering", 75000));
        employees.add(new ComparableEmployee("Alice", "Marketing", 65000));
        employees.add(new ComparableEmployee("Bob", "Engineering", 85000));
        employees.add(new ComparableEmployee("Carol", "HR", 60000));
        employees.add(new ComparableEmployee("David", "Marketing", 70000));

        System.out.println("Original list of employees:");
        printEmployees(employees);

        // Sort by name
        Collections.sort(employees, new EmployeeNameComparator());

        System.out.println("\nEmployees sorted by name:");
        printEmployees(employees);

        // Sort by department
        Collections.sort(employees, new EmployeeDepartmentComparator());

        System.out.println("\nEmployees sorted by department:");
        printEmployees(employees);

        // Sort by salary (descending)
        Collections.sort(employees, new EmployeeSalaryComparator());

        System.out.println("\nEmployees sorted by salary (descending):");
        printEmployees(employees);

        // Using Java 8 Comparator methods
        System.out.println("\nUsing Java 8 Comparator methods:");

        // Sort by name using Comparator.comparing
        Collections.sort(employees, Comparator.comparing(ComparableEmployee::getName));
        System.out.println("\nEmployees sorted by name (using Comparator.comparing):");
        printEmployees(employees);

        // Sort by salary in reverse order
        Collections.sort(employees, Comparator.comparing(ComparableEmployee::getSalary).reversed());
        System.out.println("\nEmployees sorted by salary (descending, using reversed()):");
        printEmployees(employees);
    }

    /**
     * Demonstrates combining multiple comparators
     */
    private static void demonstrateCombiningComparators() {
        System.out.println("\n5. Combining Comparators:\n");

        // Creating employees with some in the same department
        List<ComparableEmployee> employees = new ArrayList<>();
        employees.add(new ComparableEmployee("John", "Engineering", 75000));
        employees.add(new ComparableEmployee("Alice", "Marketing", 65000));
        employees.add(new ComparableEmployee("Bob", "Engineering", 85000));
        employees.add(new ComparableEmployee("Carol", "HR", 60000));
        employees.add(new ComparableEmployee("David", "Marketing", 70000));
        employees.add(new ComparableEmployee("Eve", "Engineering", 75000));  // Same salary as John

        System.out.println("Original list of employees:");
        printEmployees(employees);

        // Create individual comparators
        Comparator<ComparableEmployee> byDepartment = new EmployeeDepartmentComparator();
        Comparator<ComparableEmployee> bySalary = new EmployeeSalaryComparator();  // Descending
        Comparator<ComparableEmployee> byName = new EmployeeNameComparator();

        // Combine comparators: first by department, then by salary (descending), then by name
        Comparator<ComparableEmployee> combinedComparator = byDepartment
                .thenComparing(bySalary)
                .thenComparing(byName);

        // Sort using the combined comparator
        Collections.sort(employees, combinedComparator);

        System.out.println("\nEmployees sorted by department, then by salary (desc), then by name:");
        printEmployees(employees);

        // Alternative using Java 8 syntax
        Collections.sort(employees, Comparator
                .comparing(ComparableEmployee::getDepartment)
                .thenComparing(ComparableEmployee::getSalary, Comparator.reverseOrder())
                .thenComparing(ComparableEmployee::getName));

        System.out.println("\nSame sorting using Java 8 methods:");
        printEmployees(employees);
    }

    /**
     * Helper method to print a list of generic objects
     */
    private static <T> void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }

    /**
     * Helper method to print a list of books
     */
    private static void printBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + 
                               " ("+book.getPageCount() + " pages)");
        }
    }

    /**
     * Helper method to print a list of students
     */
    private static void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student.getName() + " - GPA: " + student.getGpa() + ", Age: " + student.getAge());
        }
    }

    /**
     * Helper method to print a list of employees
     */
    private static void printEmployees(List<ComparableEmployee> employees) {
        for (ComparableEmployee emp : employees) {
            System.out.println(emp.getName() + " - Department: " + emp.getDepartment() + 
                               ", Salary: $" + emp.getSalary());
        }
    }
}

/**
 * ComparablePerson class implementing Comparable interface for natural ordering by age
 */
class ComparablePerson implements Comparable<ComparablePerson> {
    private String name;
    private int age;

    public ComparablePerson(String name, int age) {
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

    // Defining natural ordering by age
    @Override
    public int compareTo(ComparablePerson other) {
        // Compare this person's age with other person's age
        return this.age - other.age;

        // Alternatively, we could use:
        // return Integer.compare(this.age, other.age);
    }
}

/**
 * Book class that doesn't implement Comparable
 * We'll use external Comparators for different sorting criteria
 */
class Book {
    private String title;
    private String author;
    private int pageCount;

    public Book(String title, String author, int pageCount) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }
}

/**
 * Comparator to sort books by title
 */
class TitleComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getTitle().compareTo(b2.getTitle());
    }
}

/**
 * Comparator to sort books by author
 */
class AuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getAuthor().compareTo(b2.getAuthor());
    }
}

/**
 * Comparator to sort books by page count
 */
class PageCountComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getPageCount() - b2.getPageCount();
        // Alternatively: return Integer.compare(b1.getPageCount(), b2.getPageCount());
    }
}

/**
 * Student class with natural ordering by GPA
 */
class Student implements Comparable<Student> {
    private String name;
    private double gpa;
    private int age;

    public Student(String name, double gpa, int age) {
        this.name = name;
        this.gpa = gpa;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public int getAge() {
        return age;
    }

    // Natural ordering by GPA (descending - higher GPA first)
    @Override
    public int compareTo(Student other) {
        // Note: We use Double.compare and reverse the order for descending
        return Double.compare(other.gpa, this.gpa);  // Higher GPA comes first
    }
}

/**
 * Comparator to sort students by age
 */
class AgeComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getAge() - s2.getAge();
    }
}

/**
 * ComparableEmployee class for demonstrating multiple comparators
 */
class ComparableEmployee {
    private String name;
    private String department;
    private double salary;

    public ComparableEmployee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}

/**
 * Comparator to sort employees by name
 */
class EmployeeNameComparator implements Comparator<ComparableEmployee> {
    @Override
    public int compare(ComparableEmployee e1, ComparableEmployee e2) {
        return e1.getName().compareTo(e2.getName());
    }
}

/**
 * Comparator to sort employees by department
 */
class EmployeeDepartmentComparator implements Comparator<ComparableEmployee> {
    @Override
    public int compare(ComparableEmployee e1, ComparableEmployee e2) {
        return e1.getDepartment().compareTo(e2.getDepartment());
    }
}

/**
 * Comparator to sort employees by salary (descending - higher salary first)
 */
class EmployeeSalaryComparator implements Comparator<ComparableEmployee> {
    @Override
    public int compare(ComparableEmployee e1, ComparableEmployee e2) {
        // Note: We reverse the comparison for descending order
        return Double.compare(e2.getSalary(), e1.getSalary());
    }
}
