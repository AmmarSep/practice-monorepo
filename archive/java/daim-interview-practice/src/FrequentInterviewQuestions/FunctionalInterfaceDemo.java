package FrequentInterviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * This class demonstrates Functional Interfaces in Java
 * 
 * A functional interface is an interface that contains exactly one abstract method.
 * It can contain any number of default, static methods but only one abstract method.
 * 
 * Java 8 introduced the @FunctionalInterface annotation to indicate that an interface
 * is intended to be a functional interface.
 */
public class FunctionalInterfaceDemo {

    public static void main(String[] args) {
        System.out.println("===== Functional Interface Demonstration =====");

        // 1. Creating Custom Functional Interfaces
        demonstrateCustomFunctionalInterface();

        // 2. Built-in Functional Interfaces - Predicate
        demonstratePredicate();

        // 3. Built-in Functional Interfaces - Consumer
        demonstrateConsumer();

        // 4. Built-in Functional Interfaces - Supplier
        demonstrateSupplier();

        // 5. Built-in Functional Interfaces - Function
        demonstrateFunction();

        // 6. Built-in Functional Interfaces - UnaryOperator & BinaryOperator
        demonstrateOperators();
    }

    /**
     * Demonstrates creating and using custom functional interfaces
     */
    private static void demonstrateCustomFunctionalInterface() {
        System.out.println("\n1. Custom Functional Interface:\n");

        // Implementation using anonymous class
        Calculator addition = new Calculator() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };

        // Implementation using lambda expression (more concise)
        Calculator subtraction = (a, b) -> a - b;
        Calculator multiplication = (a, b) -> a * b;
        Calculator division = (a, b) -> b != 0 ? a / b : 0;

        // Using the functional interface
        System.out.println("10 + 5 = " + addition.calculate(10, 5));
        System.out.println("10 - 5 = " + subtraction.calculate(10, 5));
        System.out.println("10 * 5 = " + multiplication.calculate(10, 5));
        System.out.println("10 / 5 = " + division.calculate(10, 5));
        System.out.println("10 / 0 = " + division.calculate(10, 0)); // Handled with default value

        // Using Greeting functional interface
        Greeting englishGreeting = name -> "Hello, " + name + "!";
        Greeting spanishGreeting = name -> "¡Hola, " + name + "!";

        System.out.println(englishGreeting.greet("John"));
        System.out.println(spanishGreeting.greet("Juan"));

        // Using static and default methods of functional interface
        System.out.println(Greeting.getStandardGreeting());
        System.out.println(englishGreeting.greetWithTime("Alice"));
    }

    /**
     * Demonstrates the Predicate functional interface
     * Predicate<T> - Takes an object of type T and returns boolean
     */
    private static void demonstratePredicate() {
        System.out.println("\n2. Predicate<T> Interface:\n");

        // Simple predicate that checks if a number is even
        Predicate<Integer> isEven = num -> num % 2 == 0;

        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 7 even? " + isEven.test(7));

        // Predicate that checks if a string is longer than 5 characters
        Predicate<String> isLongString = str -> str.length() > 5;

        System.out.println("Is 'Programming' a long string? " + isLongString.test("Programming"));
        System.out.println("Is 'Java' a long string? " + isLongString.test("Java"));

        // Combining predicates using default methods
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Greater than 5
        Predicate<Integer> greaterThan5 = num -> num > 5;

        // Less than 8
        Predicate<Integer> lessThan8 = num -> num < 8;

        System.out.println("\nFiltering numbers with combined predicates:");
        System.out.println("Original list: " + numbers);

        // AND operation
        List<Integer> between5And8 = filterList(numbers, greaterThan5.and(lessThan8));
        System.out.println("Numbers between 5 and 8: " + between5And8);

        // OR operation
        List<Integer> lessThan5OrGreaterThan8 = filterList(numbers, 
                greaterThan5.negate().or(lessThan8.negate()));
        System.out.println("Numbers less than 5 or greater than 8: " + lessThan5OrGreaterThan8);

        // NOT operation
        List<Integer> notEven = filterList(numbers, isEven.negate());
        System.out.println("Odd numbers: " + notEven);

        // Using BiPredicate (takes two arguments)
        BiPredicate<String, Integer> isLongerThan = (str, length) -> str.length() > length;
        System.out.println("\nIs 'Java' longer than 3 characters? " + 
                        isLongerThan.test("Java", 3));
        System.out.println("Is 'Java' longer than 5 characters? " + 
                        isLongerThan.test("Java", 5));
    }

    /**
     * Helper method to filter list using a predicate
     */
    private static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Demonstrates the Consumer functional interface
     * Consumer<T> - Takes an object of type T and returns nothing (void)
     */
    private static void demonstrateConsumer() {
        System.out.println("\n3. Consumer<T> Interface:\n");

        // Simple consumer that prints a string
        Consumer<String> printer = str -> System.out.println("Consuming: " + str);

        printer.accept("Hello, World!");

        // Consumer that prints details of a person
        Consumer<Person> personPrinter = person -> 
            System.out.println("Person: " + person.getName() + ", " + person.getAge() + " years old");

        Person john = new Person("John", 30);
        Person alice = new Person("Alice", 25);

        personPrinter.accept(john);
        personPrinter.accept(alice);

        // Chaining consumers using andThen()
        Consumer<Person> greetPerson = person -> 
            System.out.println("Hello, " + person.getName() + "!");

        System.out.println("\nChaining consumers:");
        Consumer<Person> greetAndPrintPerson = greetPerson.andThen(personPrinter);
        greetAndPrintPerson.accept(john);

        // BiConsumer - takes two arguments and returns nothing
        System.out.println("\nUsing BiConsumer:");
        BiConsumer<String, Integer> nameAndAge = (name, age) -> 
            System.out.println(name + " is " + age + " years old");

        nameAndAge.accept("Bob", 40);

        // Using forEach with a consumer
        System.out.println("\nUsing forEach with Consumer:");
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Jill");
        names.forEach(name -> System.out.println("Name: " + name));

        // More concise with method reference
        System.out.println("\nUsing method reference:");
        names.forEach(System.out::println);
    }

    /**
     * Demonstrates the Supplier functional interface
     * Supplier<T> - Takes no arguments and returns an object of type T
     */
    private static void demonstrateSupplier() {
        System.out.println("\n4. Supplier<T> Interface:\n");

        // Supplier that returns a greeting message
        Supplier<String> greeting = () -> "Hello, Supplier!";

        System.out.println(greeting.get());

        // Supplier that returns current date and time
        Supplier<java.time.LocalDateTime> currentTime = java.time.LocalDateTime::now;

        System.out.println("Current time: " + currentTime.get());

        // Supplier that returns a random value
        Supplier<Double> randomValue = Math::random;

        System.out.println("Random value: " + randomValue.get());
        System.out.println("Another random value: " + randomValue.get());

        // Supplier that creates a new instance
        Supplier<Person> personSupplier = () -> new Person("Guest", 0);

        Person guest = personSupplier.get();
        System.out.println("Created person: " + guest.getName());

        // Creating a list of 5 random numbers using a supplier
        List<Double> randomNumbers = generateList(5, randomValue);
        System.out.println("\nList of 5 random numbers: " + randomNumbers);
    }

    /**
     * Helper method to generate a list using a supplier
     */
    private static <T> List<T> generateList(int size, Supplier<T> supplier) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(supplier.get());
        }
        return result;
    }

    /**
     * Demonstrates the Function functional interface
     * Function<T, R> - Takes an object of type T and returns an object of type R
     */
    private static void demonstrateFunction() {
        System.out.println("\n5. Function<T, R> Interface:\n");

        // Function that returns the length of a string
        Function<String, Integer> stringLength = str -> str.length();

        System.out.println("Length of 'Hello': " + stringLength.apply("Hello"));
        System.out.println("Length of 'Functional Interface': " + 
                stringLength.apply("Functional Interface"));

        // Function that converts a string to uppercase
        Function<String, String> toUpperCase = String::toUpperCase;

        System.out.println("Uppercase of 'hello': " + toUpperCase.apply("hello"));

        // Combining functions using andThen()
        Function<String, String> exclaim = str -> str + "!";
        Function<String, String> toUpperCaseThenExclaim = toUpperCase.andThen(exclaim);

        System.out.println("\nCombining functions with andThen():");
        System.out.println("Result: " + toUpperCaseThenExclaim.apply("hello"));

        // Combining functions using compose()
        Function<String, String> composedFunction = exclaim.compose(toUpperCase);

        System.out.println("\nCombining functions with compose():");
        System.out.println("Result: " + composedFunction.apply("hello"));

        // BiFunction - takes two arguments and returns a result
        BiFunction<String, String, String> concat = (s1, s2) -> s1 + s2;

        System.out.println("\nUsing BiFunction:");
        System.out.println("Concat 'Hello' and 'World': " + concat.apply("Hello", "World"));

        // BiFunction with different types
        BiFunction<String, Integer, String> repeat = (str, count) -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append(str);
            }
            return sb.toString();
        };

        System.out.println("Repeat 'Ha' 3 times: " + repeat.apply("Ha", 3));
    }

    /**
     * Demonstrates the UnaryOperator and BinaryOperator functional interfaces
     * UnaryOperator<T> - Takes an object of type T and returns an object of the same type
     * BinaryOperator<T> - Takes two objects of type T and returns an object of the same type
     */
    private static void demonstrateOperators() {
        System.out.println("\n6. UnaryOperator<T> and BinaryOperator<T> Interfaces:\n");

        // UnaryOperator - special case of Function where input and output types are the same
        UnaryOperator<String> toUpperCase = str -> str.toUpperCase();
        UnaryOperator<Integer> square = num -> num * num;

        System.out.println("Uppercase of 'hello': " + toUpperCase.apply("hello"));
        System.out.println("Square of 5: " + square.apply(5));

        // BinaryOperator - special case of BiFunction where all types are the same
        BinaryOperator<Integer> add = (a, b) -> a + b;
        BinaryOperator<String> concat = (s1, s2) -> s1 + s2;

        System.out.println("\nAdd 10 + 25: " + add.apply(10, 25));
        System.out.println("Concat 'Hello' + 'World': " + concat.apply("Hello", "World"));

        // Using BinaryOperator with static methods
        BinaryOperator<Integer> max = BinaryOperator.maxBy(Integer::compare);
        BinaryOperator<Integer> min = BinaryOperator.minBy(Integer::compare);

        System.out.println("Max of 10 and 25: " + max.apply(10, 25));
        System.out.println("Min of 10 and 25: " + min.apply(10, 25));

        // Applying UnaryOperator to a list
        List<String> names = Arrays.asList("john", "alice", "bob", "carol");
        List<String> upperCaseNames = transformList(names, toUpperCase);

        System.out.println("\nOriginal names: " + names);
        System.out.println("Uppercase names: " + upperCaseNames);

        // Applying BinaryOperator to reduce a list to a single value
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = reduceList(numbers, 0, add);

        System.out.println("\nSum of " + numbers + ": " + sum);
    }

    /**
     * Helper method to transform a list using a UnaryOperator
     */
    private static <T> List<T> transformList(List<T> list, UnaryOperator<T> operator) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            result.add(operator.apply(item));
        }
        return result;
    }

    /**
     * Helper method to reduce a list to a single value using a BinaryOperator
     */
    private static <T> T reduceList(List<T> list, T identity, BinaryOperator<T> operator) {
        T result = identity;
        for (T item : list) {
            result = operator.apply(result, item);
        }
        return result;
    }
}

// Simple class for demonstration
class Person {
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
}

// Custom functional interface with a single abstract method
@FunctionalInterface
interface Calculator {
    // Single abstract method
    int calculate(int a, int b);

    // Default method (doesn't count as abstract)
    default String getDescription() {
        return "A calculator interface";
    }

    // Static method (doesn't count as abstract)
    static Calculator getAdditionCalculator() {
        return (a, b) -> a + b;
    }
}

// Another custom functional interface
@FunctionalInterface
interface Greeting {
    // Single abstract method
    String greet(String name);

    // Default method
    default String greetWithTime(String name) {
        return java.time.LocalTime.now() + " - " + greet(name);
    }

    // Static method
    static String getStandardGreeting() {
        return "Welcome!";
    }
}
