package Fresher;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class demonstrates Java 8 features including:
 * 1. Lambda Expressions
 * 2. Functional Interfaces
 * 3. Method References
 * 4. Stream API
 */
public class LambdaAndStreamDemo {

    public static void main(String[] args) {
        System.out.println("=== Java 8 Lambda and Stream API Demo ===\n");

        // 1. Lambda Expressions
        demonstrateLambdaExpressions();

        // 2. Built-in Functional Interfaces
        demonstrateFunctionalInterfaces();

        // 3. Method References
        demonstrateMethodReferences();

        // 4. Stream API
        demonstrateStreamAPI();

        // 5. Stream Operations
        demonstrateStreamOperations();

        // 6. Practical Examples
        demonstratePracticalExamples();
    }

    // 1. Demonstrate Lambda Expressions
    private static void demonstrateLambdaExpressions() {
        System.out.println("1. Lambda Expressions:\n");

        // Example 1: Lambda with no parameters
        Runnable noParams = () -> System.out.println("Lambda with no parameters");
        noParams.run();

        // Example 2: Lambda with one parameter
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        System.out.println("\nOriginal list: " + names);

        // Old way with anonymous class
        System.out.println("\nUsing anonymous class:");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        System.out.println("Sorted list: " + names);

        // New way with lambda expression
        System.out.println("\nUsing lambda expression:");
        Collections.sort(names, (s1, s2) -> s2.compareTo(s1)); // Reverse order
        System.out.println("Reverse sorted list: " + names);

        // Example 3: Lambda with multiple parameters and multiple statements
        Calculator addition = (a, b) -> {
            System.out.println("Adding " + a + " and " + b);
            return a + b;
        };

        Calculator subtraction = (a, b) -> a - b; // Single statement, no return needed

        System.out.println("\nCalculator with lambda:");
        System.out.println("10 + 5 = " + addition.calculate(10, 5));
        System.out.println("10 - 5 = " + subtraction.calculate(10, 5));

        System.out.println();
    }

    // 2. Demonstrate Built-in Functional Interfaces
    private static void demonstrateFunctionalInterfaces() {
        System.out.println("2. Built-in Functional Interfaces:\n");

        // Predicate - takes one argument, returns boolean
        System.out.println("Predicate examples:");
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 7 even? " + isEven.test(7));

        // Combining predicates
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isPositiveAndEven = isPositive.and(isEven);
        System.out.println("Is 6 positive and even? " + isPositiveAndEven.test(6));
        System.out.println("Is -2 positive and even? " + isPositiveAndEven.test(-2));

        // Function - takes one argument, returns a result
        System.out.println("\nFunction examples:");
        Function<String, Integer> stringLength = s -> s.length();
        System.out.println("Length of 'Hello': " + stringLength.apply("Hello"));

        // Function composition
        Function<Integer, Integer> doubled = n -> n * 2;
        Function<String, Integer> lengthThenDouble = doubled.compose(stringLength);
        System.out.println("Double the length of 'World': " + lengthThenDouble.apply("World"));

        // Consumer - takes one argument, returns nothing
        System.out.println("\nConsumer examples:");
        Consumer<String> printer = s -> System.out.println("Consuming: " + s);
        printer.accept("Hello Consumer");

        // BiConsumer - takes two arguments, returns nothing
        BiConsumer<String, Integer> nameAndAge = (name, age) -> 
            System.out.println(name + " is " + age + " years old");
        nameAndAge.accept("Alice", 30);

        // Supplier - takes no arguments, returns a result
        System.out.println("\nSupplier examples:");
        Supplier<Double> randomValue = () -> Math.random();
        System.out.println("Random value: " + randomValue.get());
        System.out.println("Another random value: " + randomValue.get());

        // BiFunction - takes two arguments, returns a result
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        System.out.println("\n6 * 7 = " + multiply.apply(6, 7));

        System.out.println();
    }

    // 3. Demonstrate Method References
    private static void demonstrateMethodReferences() {
        System.out.println("3. Method References:\n");

        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Mango", "Pineapple");
        System.out.println("Original list: " + fruits);

        // Static method reference
        System.out.println("\nUsing static method reference:");
        fruits.forEach(System.out::println);

        // Instance method reference of a particular object
        System.out.println("\nUsing instance method reference:");
        StringProcessor processor = new StringProcessor();
        fruits.stream()
              .map(processor::processString)
              .forEach(System.out::println);

        // Instance method reference of an arbitrary object of a particular type
        System.out.println("\nUsing arbitrary object method reference:");
        fruits.sort(String::compareToIgnoreCase);
        System.out.println("Sorted case-insensitive: " + fruits);

        // Constructor reference
        System.out.println("\nUsing constructor reference:");
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> newList = listSupplier.get();
        newList.addAll(fruits);
        System.out.println("New list created with constructor reference: " + newList);

        System.out.println();
    }

    // 4. Demonstrate Stream API basics
    private static void demonstrateStreamAPI() {
        System.out.println("4. Stream API Basics:\n");

        // Creating streams
        System.out.println("Different ways to create streams:");

        // From a collection
        List<String> animals = Arrays.asList("Dog", "Cat", "Elephant", "Fox", "Rabbit");
        List<String> animalList = animals.stream().collect(Collectors.toList());
        System.out.println("Stream from list: " + animalList);

        // From individual elements
        List<Integer> elementsList = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
        System.out.println("Stream of elements: " + elementsList);

        // From an array
        String[] colorsArray = {"Red", "Green", "Blue"};
        List<String> colorsList = Arrays.stream(colorsArray).collect(Collectors.toList());
        System.out.println("Stream from array: " + colorsList);

        // Generate infinite stream (limited to 5 elements)
        List<Double> randomList = Stream.generate(Math::random).limit(5).collect(Collectors.toList());
        System.out.println("Stream generated with Math.random(): " + randomList);

        // Iterate to create stream
        List<Integer> iteratedList = Stream.iterate(0, n -> n + 2).limit(5).collect(Collectors.toList());
        System.out.println("Stream of even numbers using iterate: " + iteratedList);

        // IntStream, LongStream, DoubleStream
        List<Integer> intList = IntStream.range(1, 6).boxed().collect(Collectors.toList()); // 1 to 5
        System.out.println("IntStream range: " + intList);

        System.out.println();
    }

    // 5. Demonstrate Stream Operations
    private static void demonstrateStreamOperations() {
        System.out.println("5. Stream Operations:\n");

        List<Person> people = Arrays.asList(
            new Person("John", 28),
            new Person("Sarah", 22),
            new Person("Mark", 35),
            new Person("Emily", 22),
            new Person("Daniel", 35),
            new Person("Lisa", 28)
        );

        System.out.println("Original people list:");
        people.forEach(System.out::println);

        // Intermediate operations

        // Filter operation
        System.out.println("\nPeople older than 30 (filter):");
        people.stream()
              .filter(p -> p.getAge() > 30)
              .forEach(System.out::println);

        // Map operation
        System.out.println("\nOnly names (map):");
        List<String> names = people.stream()
                                  .map(Person::getName)
                                  .collect(Collectors.toList());
        System.out.println(names);

        // Sorted operation
        System.out.println("\nPeople sorted by age (sorted):");
        people.stream()
              .sorted(Comparator.comparing(Person::getAge))
              .forEach(System.out::println);

        // Distinct operation
        System.out.println("\nDistinct ages (distinct):");
        List<Integer> distinctAges = people.stream()
                                         .map(Person::getAge)
                                         .distinct()
                                         .collect(Collectors.toList());
        System.out.println(distinctAges);

        // Terminal operations

        // Count operation
        long count = people.stream()
                          .filter(p -> p.getAge() < 30)
                          .count();
        System.out.println("\nNumber of people younger than 30 (count): " + count);

        // Collect operation with grouping
        System.out.println("\nPeople grouped by age (collect with groupingBy):");
        Map<Integer, List<Person>> peopleByAge = people.stream()
                                                     .collect(Collectors.groupingBy(Person::getAge));
        peopleByAge.forEach((age, personList) -> {
            System.out.println("Age " + age + ":");
            personList.forEach(p -> System.out.println("  " + p));
        });

        // Min and Max operations
        Optional<Person> youngest = people.stream()
                                        .min(Comparator.comparing(Person::getAge));
        Optional<Person> oldest = people.stream()
                                       .max(Comparator.comparing(Person::getAge));

        System.out.println("\nYoungest person (min): " + youngest.orElse(null));
        System.out.println("Oldest person (max): " + oldest.orElse(null));

        // Reduce operation
        int totalAge = people.stream()
                           .map(Person::getAge)
                           .reduce(0, Integer::sum);
        double averageAge = (double) totalAge / people.size();

        System.out.println("\nTotal age (reduce): " + totalAge);
        System.out.println("Average age: " + averageAge);

        System.out.println();
    }

    // 6. Demonstrate Practical Examples
    private static void demonstratePracticalExamples() {
        System.out.println("6. Practical Examples:\n");

        List<Product> products = Arrays.asList(
            new Product("Laptop", 1200.0, "Electronics"),
            new Product("Phone", 800.0, "Electronics"),
            new Product("Desk", 350.0, "Furniture"),
            new Product("Chair", 150.0, "Furniture"),
            new Product("Book", 25.0, "Books"),
            new Product("Tablet", 450.0, "Electronics")
        );

        System.out.println("Original products list:");
        products.forEach(System.out::println);

        // Example 1: Find all electronics products and sort by price
        System.out.println("\nElectronic products sorted by price:");
        List<Product> electronicsSorted = products.stream()
            .filter(p -> p.getCategory().equals("Electronics"))
            .sorted(Comparator.comparing(Product::getPrice))
            .collect(Collectors.toList());
        electronicsSorted.forEach(System.out::println);

        // Example 2: Calculate total price of all products by category
        System.out.println("\nTotal price by category:");
        Map<String, Double> totalPriceByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.summingDouble(Product::getPrice)
            ));
        totalPriceByCategory.forEach((category, price) -> 
            System.out.println(category + ": $" + price));

        // Example 3: Find the most expensive product in each category
        System.out.println("\nMost expensive product in each category:");
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.maxBy(Comparator.comparing(Product::getPrice))
            ));
        mostExpensiveByCategory.forEach((category, product) -> 
            System.out.println(category + ": " + product.orElse(null)));

        // Example 4: Products statistics
        System.out.println("\nProduct price statistics:");
        DoubleSummaryStatistics stats = products.stream()
            .mapToDouble(Product::getPrice)
            .summaryStatistics();
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: $" + stats.getSum());
        System.out.println("Min: $" + stats.getMin());
        System.out.println("Max: $" + stats.getMax());
        System.out.println("Average: $" + stats.getAverage());

        // Example 5: Create a comma-separated list of product names
        String productNames = products.stream()
            .map(Product::getName)
            .collect(Collectors.joining(", "));
        System.out.println("\nAll product names: " + productNames);

        // Example 6: Partition products by price
        Map<Boolean, List<Product>> partitionedByPrice = products.stream()
            .collect(Collectors.partitioningBy(p -> p.getPrice() > 300));

        System.out.println("\nExpensive products (>$300):");
        partitionedByPrice.get(true).forEach(System.out::println);

        System.out.println("\nAffordable products (≤$300):");
        partitionedByPrice.get(false).forEach(System.out::println);
    }

    // Helper classes

    // Custom functional interface
    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }

    // Class for method reference example
    static class StringProcessor {
        public String processString(String input) {
            return "Processed: " + input.toUpperCase();
        }
    }

    // Person class for Stream examples
    static class Person {
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
    }

    // Product class for practical examples
    static class Product {
        private String name;
        private double price;
        private String category;

        public Product(String name, double price, String category) {
            this.name = name;
            this.price = price;
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getCategory() {
            return category;
        }

        @Override
        public String toString() {
            return name + " ($" + price + ") - " + category;
        }
    }
}
