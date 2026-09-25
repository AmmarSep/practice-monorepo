package JavaSimplePrograms;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This class demonstrates the Predicate, Consumer, and Supplier functional interfaces in Java
 */
public class PredicateConsumerSupplierDemo {

    public static void main(String[] args) {
        System.out.println("===== Predicate, Consumer, and Supplier Demonstration =====\n");

        // 1. Predicate Functional Interface
        System.out.println("1. Predicate<T> Interface Examples\n");
        demonstratePredicate();

        // 2. Consumer Functional Interface
        System.out.println("\n2. Consumer<T> Interface Examples\n");
        demonstrateConsumer();

        // 3. Supplier Functional Interface
        System.out.println("\n3. Supplier<T> Interface Examples\n");
        demonstrateSupplier();

        // 4. Real-world scenario combining all three
        System.out.println("\n4. Real-world Scenario: Product Filtering, Processing, and Generation\n");
        demonstrateRealWorldScenario();
    }

    /**
     * Demonstrates the Predicate functional interface
     * Predicate<T> - Takes a parameter and returns a boolean value
     */
    private static void demonstratePredicate() {
        // Basic Predicate examples
        System.out.println("Basic Predicate examples:");

        // Predicate to check if a number is even
        Predicate<Integer> isEven = n -> n % 2 == 0;

        // Predicate to check if a number is positive
        Predicate<Integer> isPositive = n -> n > 0;

        // Predicate to check if a number is greater than 10
        Predicate<Integer> isGreaterThan10 = n -> n > 10;

        // Testing the predicates
        int[] numbers = {-20, -5, 0, 5, 10, 15, 20};

        System.out.println("Testing individual predicates:");
        for (int num : numbers) {
            System.out.printf("Number: %d, isEven: %b, isPositive: %b, isGreaterThan10: %b%n",
                            num, isEven.test(num), isPositive.test(num), isGreaterThan10.test(num));
        }

        // Combining predicates with logical operations
        System.out.println("\nCombining predicates with logical operations:");

        // Predicate for even AND positive numbers
        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);

        // Predicate for positive OR greater than 10
        Predicate<Integer> isPositiveOrGreaterThan10 = isPositive.or(isGreaterThan10);

        // Predicate for numbers that are not even (using negate())
        Predicate<Integer> isNotEven = isEven.negate();

        for (int num : numbers) {
            System.out.printf("Number: %d, isEvenAndPositive: %b, isPositiveOrGreaterThan10: %b, isNotEven: %b%n",
                            num, isEvenAndPositive.test(num), isPositiveOrGreaterThan10.test(num), isNotEven.test(num));
        }

        // Using Predicate with collections
        System.out.println("\nFiltering collections with Predicate:");
        List<Integer> numbersList = Arrays.asList(-20, -5, 0, 5, 10, 15, 20);

        List<Integer> evenNumbers = filterList(numbersList, isEven);
        List<Integer> positiveNumbers = filterList(numbersList, isPositive);
        List<Integer> evenPositiveNumbers = filterList(numbersList, isEvenAndPositive);

        System.out.println("Original list: " + numbersList);
        System.out.println("Even numbers: " + evenNumbers);
        System.out.println("Positive numbers: " + positiveNumbers);
        System.out.println("Even and positive numbers: " + evenPositiveNumbers);

        // BiPredicate example - tests two input parameters
        System.out.println("\nBiPredicate example:");
        BiPredicate<Integer, Integer> isSumEven = (a, b) -> (a + b) % 2 == 0;
        BiPredicate<String, String> areEqualIgnoreCase = (s1, s2) -> s1.equalsIgnoreCase(s2);

        System.out.println("Is sum of 5 and 7 even? " + isSumEven.test(5, 7));
        System.out.println("Is sum of 5 and 5 even? " + isSumEven.test(5, 5));
        System.out.println("Are 'Java' and 'java' equal ignoring case? " + areEqualIgnoreCase.test("Java", "java"));
        System.out.println("Are 'Java' and 'Python' equal ignoring case? " + areEqualIgnoreCase.test("Java", "Python"));

        // Predicate with custom objects
        System.out.println("\nPredicate with custom objects:");
        List<Person> people = Arrays.asList(
            new Person("John", 25),
            new Person("Alice", 30),
            new Person("Bob", 17),
            new Person("Carol", 22),
            new Person("David", 35)
        );

        // Predicate to check if a person is an adult (age >= 18)
        Predicate<Person> isAdult = person -> person.getAge() >= 18;

        // Predicate to check if a person's name starts with 'A'
        Predicate<Person> nameStartsWithA = person -> person.getName().startsWith("A");

        List<Person> adults = filterList(people, isAdult);
        List<Person> adultsWithNameA = filterList(people, isAdult.and(nameStartsWithA));

        System.out.println("All people: " + people);
        System.out.println("Adults: " + adults);
        System.out.println("Adults with name starting with 'A': " + adultsWithNameA);
    }

    /**
     * Demonstrates the Consumer functional interface
     * Consumer<T> - Takes a parameter and returns nothing (void)
     */
    private static void demonstrateConsumer() {
        // Basic Consumer examples
        System.out.println("Basic Consumer examples:");

        // Consumer that prints a message
        Consumer<String> printMessage = message -> System.out.println("Message: " + message);

        // Consumer that prints a string in uppercase
        Consumer<String> printUpperCase = message -> System.out.println("Uppercase: " + message.toUpperCase());

        // Consumer that prints the length of a string
        Consumer<String> printLength = message -> System.out.println("Length: " + message.length());

        // Using the consumers
        String text = "Hello, Functional Programming";
        printMessage.accept(text);
        printUpperCase.accept(text);
        printLength.accept(text);

        // Chaining consumers with andThen()
        System.out.println("\nChaining consumers with andThen():");

        // Chain multiple consumers together
        Consumer<String> printAll = printMessage
                                   .andThen(printUpperCase)
                                   .andThen(printLength);

        // Apply the chained consumer
        printAll.accept("Java 8 Features");

        // BiConsumer example - accepts two parameters
        System.out.println("\nBiConsumer example:");
        BiConsumer<String, Integer> printNameAndAge = (name, age) -> 
            System.out.println("Name: " + name + ", Age: " + age);

        printNameAndAge.accept("John", 30);
        printNameAndAge.accept("Alice", 25);

        // Using Consumer with collections
        System.out.println("\nUsing Consumer with collections:");
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Carol", "David");

        // Print all names
        System.out.println("All names:");
        processStrings(names, name -> System.out.println("  " + name));

        // Print all names in uppercase
        System.out.println("\nAll names in uppercase:");
        processStrings(names, name -> System.out.println("  " + name.toUpperCase()));

        // Print names with greeting
        System.out.println("\nGreeting all names:");
        processStrings(names, name -> System.out.println("  Hello, " + name + "!"));

        // Consumer with custom objects
        System.out.println("\nConsumer with custom objects:");
        List<Person> people = Arrays.asList(
            new Person("John", 25),
            new Person("Alice", 30),
            new Person("Bob", 17)
        );

        // Consumer that prints person details
        Consumer<Person> printPerson = person -> 
            System.out.println("Person: " + person.getName() + ", Age: " + person.getAge());

        // Consumer that checks and prints if a person is an adult
        Consumer<Person> checkAdult = person -> {
            if (person.getAge() >= 18) {
                System.out.println(person.getName() + " is an adult");
            } else {
                System.out.println(person.getName() + " is a minor");
            }
        };

        // Process all people with both consumers
        System.out.println("Processing people:");
        processList(people, printPerson.andThen(checkAdult));
    }

    /**
     * Demonstrates the Supplier functional interface
     * Supplier<T> - Takes no parameters and returns a value
     */
    private static void demonstrateSupplier() {
        // Basic Supplier examples
        System.out.println("Basic Supplier examples:");

        // Supplier that returns a random integer (1-100)
        Supplier<Integer> randomIntSupplier = () -> new Random().nextInt(100) + 1;

        // Supplier that returns the current date
        Supplier<LocalDate> dateSupplier = () -> LocalDate.now();

        // Supplier that returns the current date and time
        Supplier<LocalDateTime> dateTimeSupplier = () -> LocalDateTime.now();

        // Using the suppliers
        System.out.println("Random integer: " + randomIntSupplier.get());
        System.out.println("Current date: " + dateSupplier.get());
        System.out.println("Current date and time: " + dateTimeSupplier.get());

        // Generating values with Supplier
        System.out.println("\nGenerating values with Supplier:");

        // Generate 5 random integers
        System.out.println("Generating 5 random integers:");
        for (int i = 0; i < 5; i++) {
            System.out.println("  Random #" + (i + 1) + ": " + randomIntSupplier.get());
        }

        // Generate a list of random integers
        List<Integer> randomNumbers = generateList(10, randomIntSupplier);
        System.out.println("\nList of 10 random numbers: " + randomNumbers);

        // Supplier that returns a random name
        Supplier<String> randomNameSupplier = () -> {
            String[] names = {"John", "Alice", "Bob", "Carol", "David", "Eva", "Frank", "Grace"};
            return names[new Random().nextInt(names.length)];
        };

        // Generate a list of random names
        List<String> randomNames = generateList(5, randomNameSupplier);
        System.out.println("\nList of 5 random names: " + randomNames);

        // Supplier with custom objects
        System.out.println("\nSupplier with custom objects:");

        // Supplier that generates a random person
        Supplier<Person> randomPersonSupplier = () -> {
            String[] names = {"John", "Alice", "Bob", "Carol", "David"};
            String randomName = names[new Random().nextInt(names.length)];
            int randomAge = new Random().nextInt(50) + 18; // Random age between 18 and 67
            return new Person(randomName, randomAge);
        };

        // Generate a random person
        Person randomPerson = randomPersonSupplier.get();
        System.out.println("Random person: " + randomPerson);

        // Generate a list of random people
        List<Person> randomPeople = generateList(5, randomPersonSupplier);
        System.out.println("\nList of 5 random people:");
        randomPeople.forEach(person -> System.out.println("  " + person));

        // Practical example: Creating a lazy-initialized object
        System.out.println("\nLazy initialization example:");
        Supplier<ExpensiveObject> expensiveObjectSupplier = () -> {
            System.out.println("Creating expensive object...");
            return new ExpensiveObject();
        };

        // The expensive object is not created until get() is called
        System.out.println("Before calling get()");
        ExpensiveObject expensiveObject = expensiveObjectSupplier.get();
        System.out.println("After calling get()");
    }

    /**
     * Demonstrates a real-world scenario combining Predicate, Consumer, and Supplier
     */
    private static void demonstrateRealWorldScenario() {
        // Create a list of products
        List<Product> inventory = Arrays.asList(
            new Product("Laptop", 1200.0, "Electronics", 10),
            new Product("Smartphone", 800.0, "Electronics", 15),
            new Product("Headphones", 150.0, "Electronics", 20),
            new Product("Shirt", 35.0, "Clothing", 30),
            new Product("Jeans", 60.0, "Clothing", 25),
            new Product("Book", 20.0, "Books", 50),
            new Product("Tablet", 300.0, "Electronics", 5)
        );

        System.out.println("Original inventory:");
        inventory.forEach(product -> System.out.println("  " + product));

        // PREDICATES - Filtering products
        System.out.println("\nFiltering products using Predicates:");

        // Predicate: Electronics products
        Predicate<Product> isElectronics = product -> product.getCategory().equals("Electronics");

        // Predicate: Expensive products (price > 500)
        Predicate<Product> isExpensive = product -> product.getPrice() > 500;

        // Predicate: Low stock products (quantity < 10)
        Predicate<Product> isLowStock = product -> product.getQuantity() < 10;

        // Get electronic products
        List<Product> electronicsProducts = filterList(inventory, isElectronics);
        System.out.println("\nElectronics products:");
        electronicsProducts.forEach(product -> System.out.println("  " + product));

        // Get expensive electronic products
        List<Product> expensiveElectronics = filterList(inventory, isElectronics.and(isExpensive));
        System.out.println("\nExpensive electronics products:");
        expensiveElectronics.forEach(product -> System.out.println("  " + product));

        // Get low stock products
        List<Product> lowStockProducts = filterList(inventory, isLowStock);
        System.out.println("\nLow stock products:");
        lowStockProducts.forEach(product -> System.out.println("  " + product));

        // CONSUMERS - Processing products
        System.out.println("\nProcessing products using Consumers:");

        // Consumer: Apply 10% discount
        Consumer<Product> applyDiscount = product -> {
            double discountedPrice = product.getPrice() * 0.9; // 10% discount
            product.setPrice(discountedPrice);
            System.out.println("  Applied 10% discount to " + product.getName() + 
                             ", new price: $" + String.format("%.2f", product.getPrice()));
        };

        // Consumer: Restock product (add 5 to quantity)
        Consumer<Product> restockProduct = product -> {
            product.setQuantity(product.getQuantity() + 5);
            System.out.println("  Restocked " + product.getName() + 
                             ", new quantity: " + product.getQuantity());
        };

        // Apply discount to expensive electronics
        System.out.println("\nApplying discount to expensive electronics:");
        processList(expensiveElectronics, applyDiscount);

        // Restock low stock products
        System.out.println("\nRestocking low stock products:");
        processList(lowStockProducts, restockProduct);

        // SUPPLIERS - Generating new products
        System.out.println("\nGenerating new products using Suppliers:");

        // Supplier: Create a new random product
        Supplier<Product> randomProductSupplier = () -> {
            String[] names = {"Monitor", "Keyboard", "Mouse", "Speaker", "Camera", "Printer"};
            String[] categories = {"Electronics", "Accessories", "Peripherals"};
            String randomName = names[new Random().nextInt(names.length)];
            String randomCategory = categories[new Random().nextInt(categories.length)];
            double randomPrice = 50 + new Random().nextDouble() * 450; // Random price between 50 and 500
            int randomQuantity = new Random().nextInt(30) + 1; // Random quantity between 1 and 30
            return new Product(randomName, randomPrice, randomCategory, randomQuantity);
        };

        // Generate 3 new random products
        List<Product> newProducts = generateList(3, randomProductSupplier);

        System.out.println("Generated new products:");
        newProducts.forEach(product -> System.out.println("  " + product));

        // Combine all three functional interfaces in a single workflow
        System.out.println("\nComplete workflow: Generate, Filter, and Process:");

        // Generate 5 random products
        List<Product> generatedProducts = generateList(5, randomProductSupplier);

        // Filter for low stock generated products
        List<Product> lowStockGeneratedProducts = filterList(generatedProducts, isLowStock);

        // Apply restock to the filtered products
        processList(lowStockGeneratedProducts, restockProduct);

        // Show final result
        System.out.println("\nAll generated products:");
        generatedProducts.forEach(product -> System.out.println("  " + product));
    }

    /**
     * Helper method to filter a list using a predicate
     */
    private static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        List<T> filteredList = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                filteredList.add(item);
            }
        }
        return filteredList;

        // Java 8 streams equivalent:
        // return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * Helper method to process a list using a consumer
     */
    private static <T> void processList(List<T> list, Consumer<T> consumer) {
        for (T item : list) {
            consumer.accept(item);
        }

        // Java 8 streams equivalent:
        // list.forEach(consumer);
    }

    /**
     * Helper method to process a list of strings using a consumer
     */
    private static void processStrings(List<String> list, Consumer<String> consumer) {
        for (String item : list) {
            consumer.accept(item);
        }
    }

    /**
     * Helper method to generate a list using a supplier
     */
    private static <T> List<T> generateList(int size, Supplier<T> supplier) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(supplier.get());
        }
        return list;

        // Java 8 streams equivalent:
        // return Stream.generate(supplier).limit(size).collect(Collectors.toList());
    }

    /**
     * ExpensiveObject class for the supplier example
     */
    static class ExpensiveObject {
        public ExpensiveObject() {
            // Simulate expensive initialization
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        @Override
        public String toString() {
            return "ExpensiveObject instance";
        }
    }

    /**
     * Person class for examples
     */
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
            return name + " (" + age + ")";
        }
    }

    /**
     * Product class for the real-world scenario
     */
    static class Product {
        private String name;
        private double price;
        private String category;
        private int quantity;

        public Product(String name, double price, String category, int quantity) {
            this.name = name;
            this.price = price;
            this.category = category;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCategory() {
            return category;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return name + " - $" + String.format("%.2f", price) + 
                   " - " + category;
        }
    }
}