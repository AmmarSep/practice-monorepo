package FrequentInterviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;

/**
 * This class demonstrates the built-in functional interfaces in Java 8:
 * Predicate, Consumer, and Supplier
 */
public class PredicateConsumerSupplierDemo {

    public static void main(String[] args) {
        System.out.println("===== Predicate, Consumer, and Supplier Demonstration =====");

        // 1. Predicate Interface
        demonstratePredicate();

        // 2. Consumer Interface
        demonstrateConsumer();

        // 3. Supplier Interface
        demonstrateSupplier();

        // 4. Combining the three interfaces in a real-world example
        demonstrateCombinedExample();
    }

    /**
     * Demonstrates the Predicate functional interface
     * Predicate<T> - Takes an object of type T and returns a boolean
     * Used for filtering or testing objects against a condition
     */
    private static void demonstratePredicate() {
        System.out.println("\n1. Predicate<T> Interface:\n");

        // Creating predicates
        Predicate<Integer> isEven = num -> num % 2 == 0;
        Predicate<Integer> isPositive = num -> num > 0;
        Predicate<Integer> isGreaterThan10 = num -> num > 10;

        // Basic usage
        System.out.println("Is 4 even? " + isEven.test(4));  // true
        System.out.println("Is 7 even? " + isEven.test(7));  // false

        // Combining predicates with logical operations
        // AND: Returns true only if both predicates return true
        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
        System.out.println("\nAND operation examples:");
        System.out.println("Is 4 even AND positive? " + isEvenAndPositive.test(4));  // true
        System.out.println("Is -2 even AND positive? " + isEvenAndPositive.test(-2));  // false

        // OR: Returns true if either predicate returns true
        Predicate<Integer> isEvenOrGreaterThan10 = isEven.or(isGreaterThan10);
        System.out.println("\nOR operation examples:");
        System.out.println("Is 4 even OR > 10? " + isEvenOrGreaterThan10.test(4));  // true
        System.out.println("Is 15 even OR > 10? " + isEvenOrGreaterThan10.test(15));  // true
        System.out.println("Is 7 even OR > 10? " + isEvenOrGreaterThan10.test(7));  // false

        // NEGATE: Reverses the result of the predicate
        Predicate<Integer> isOdd = isEven.negate();
        System.out.println("\nNEGATE operation examples:");
        System.out.println("Is 4 odd (not even)? " + isOdd.test(4));  // false
        System.out.println("Is 7 odd (not even)? " + isOdd.test(7));  // true

        // Predicate with complex objects
        List<Person> people = Arrays.asList(
            new Person("John", 25),
            new Person("Alice", 30),
            new Person("Bob", 17),
            new Person("Carol", 22)
        );

        // Filter adults (age >= 18)
        Predicate<Person> isAdult = person -> person.getAge() >= 18;

        System.out.println("\nFiltering people using predicate:");
        System.out.println("Adults:");
        List<Person> adults = filterList(people, isAdult);
        printPeople(adults);

        // Filter people with names starting with 'A'
        Predicate<Person> nameStartsWithA = person -> person.getName().startsWith("A");

        System.out.println("\nPeople with names starting with 'A':");
        List<Person> aNames = filterList(people, nameStartsWithA);
        printPeople(aNames);

        // Combining predicates - Adults with names starting with 'A'
        System.out.println("\nAdults with names starting with 'A':");
        List<Person> adultANames = filterList(people, isAdult.and(nameStartsWithA));
        printPeople(adultANames);

        // Using BiPredicate (takes two arguments)
        BiPredicate<Person, Integer> isOlderThan = (person, age) -> person.getAge() > age;

        System.out.println("\nUsing BiPredicate:");
        System.out.println("Is John older than 20? " + 
                isOlderThan.test(people.get(0), 20));  // true
        System.out.println("Is Bob older than 20? " + 
                isOlderThan.test(people.get(2), 20));  // false
    }

    /**
     * Demonstrates the Consumer functional interface
     * Consumer<T> - Takes an object of type T and returns void (performs an operation)
     * Used for performing operations on objects without returning a result
     */
    private static void demonstrateConsumer() {
        System.out.println("\n2. Consumer<T> Interface:\n");

        // Creating consumers
        Consumer<String> printer = message -> System.out.println("Printing: " + message);
        Consumer<String> logger = message -> System.out.println("Logging: " + message);

        // Basic usage
        printer.accept("Hello, Consumer!");  // Performs the operation
        logger.accept("This is a log message");  // Performs the operation

        // Chaining consumers with andThen()
        System.out.println("\nChaining consumers with andThen():");
        Consumer<String> printAndLog = printer.andThen(logger);
        printAndLog.accept("This message will be printed and logged");

        // Using Consumer with complex objects
        List<Person> people = Arrays.asList(
            new Person("John", 25),
            new Person("Alice", 30),
            new Person("Bob", 17)
        );

        // Consumer to print person details
        Consumer<Person> personPrinter = person -> 
            System.out.println(person.getName() + " is " + person.getAge() + " years old");

        System.out.println("\nUsing Consumer with forEach():");
        people.forEach(personPrinter);  // Apply consumer to each element

        // BiConsumer (takes two arguments)
        BiConsumer<String, Integer> nameAndAge = (name, age) -> 
            System.out.println(name + " is " + age + " years old");

        System.out.println("\nUsing BiConsumer:");
        nameAndAge.accept("David", 28);
        nameAndAge.accept("Emma", 35);

        // Real-world example: Applying multiple operations to an object
        System.out.println("\nApplying multiple operations to objects:");
        Consumer<Person> greet = person -> 
            System.out.println("Hello, " + person.getName() + "!");
        Consumer<Person> checkAge = person -> {
            if (person.getAge() >= 18) {
                System.out.println(person.getName() + " is an adult.");
            } else {
                System.out.println(person.getName() + " is a minor.");
            }
        };

        // Chain the consumers
        Consumer<Person> greetAndCheckAge = greet.andThen(checkAge);

        // Apply to all people
        System.out.println("\nGreeting and checking age for all people:");
        for (Person person : people) {
            greetAndCheckAge.accept(person);
        }
    }

    /**
     * Demonstrates the Supplier functional interface
     * Supplier<T> - Takes no arguments and returns an object of type T
     * Used for providing or generating objects without taking input
     */
    private static void demonstrateSupplier() {
        System.out.println("\n3. Supplier<T> Interface:\n");

        // Creating suppliers
        Supplier<String> greetingSupplier = () -> "Hello, World!";
        Supplier<Double> randomSupplier = () -> Math.random();
        Supplier<Integer> counterSupplier = new CounterSupplier();  // Using custom class

        // Basic usage
        System.out.println("Greeting: " + greetingSupplier.get());  // Gets the supplied value
        System.out.println("Random number: " + randomSupplier.get());  // Gets the supplied value
        System.out.println("Counter: " + counterSupplier.get());  // Gets the supplied value
        System.out.println("Counter: " + counterSupplier.get());  // Gets the supplied value again

        // Supplier for current time
        Supplier<java.time.LocalTime> timeSupplier = java.time.LocalTime::now;

        System.out.println("\nCurrent time: " + timeSupplier.get());

        // Supplier for creating objects
        Supplier<List<String>> listSupplier = ArrayList::new;  // Method reference to constructor

        List<String> newList = listSupplier.get();  // Creates a new ArrayList
        newList.add("Item 1");
        newList.add("Item 2");
        System.out.println("\nCreated list: " + newList);

        // Using Supplier to generate multiple values
        System.out.println("\nGenerating multiple random numbers:");
        List<Double> randomNumbers = generateList(5, randomSupplier);
        System.out.println(randomNumbers);

        // Supplier for creating configured objects
        Supplier<Person> personSupplier = () -> {
            String[] names = {"John", "Alice", "Bob", "Carol", "David"};
            int randomIndex = new Random().nextInt(names.length);
            int randomAge = 18 + new Random().nextInt(50);  // Random age between 18 and 67
            return new Person(names[randomIndex], randomAge);
        };

        System.out.println("\nGenerating random people:");
        List<Person> randomPeople = generateList(3, personSupplier);
        printPeople(randomPeople);

        // Specialized suppliers (BooleanSupplier, IntSupplier, LongSupplier, DoubleSupplier)
        IntSupplier diceRoll = () -> new Random().nextInt(6) + 1;  // 1-6

        System.out.println("\nRolling dice 5 times:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Roll #" + (i+1) + ": " + diceRoll.getAsInt());
        }
    }

    /**
     * Demonstrates combining Predicate, Consumer, and Supplier in a real-world example
     */
    private static void demonstrateCombinedExample() {
        System.out.println("\n4. Combining Predicate, Consumer, and Supplier:\n");

        // Supplier to generate random people
        Supplier<Person> personSupplier = () -> {
            String[] names = {"John", "Alice", "Bob", "Carol", "David", "Emma", "Frank"};
            int randomIndex = new Random().nextInt(names.length);
            int randomAge = 15 + new Random().nextInt(30);  // Random age between 15 and 44
            return new Person(names[randomIndex], randomAge);
        };

        // Generate 10 random people
        List<Person> people = generateList(10, personSupplier);

        System.out.println("Generated people:");
        printPeople(people);

        // Predicates for filtering
        Predicate<Person> isAdult = person -> person.getAge() >= 18;
        Predicate<Person> isSenior = person -> person.getAge() >= 35;

        // Consumers for different actions
        Consumer<Person> sendWelcomeEmail = person -> 
            System.out.println("Sending welcome email to " + person.getName());
        Consumer<Person> addToNewsletterList = person -> 
            System.out.println("Adding " + person.getName() + " to newsletter list");
        Consumer<Person> offerSeniorDiscount = person -> 
            System.out.println("Offering senior discount to " + person.getName());

        // Processing pipeline: filter adults and perform actions
        System.out.println("\nProcessing adults:");
        processElements(
            people,                                   // Source list
            isAdult,                                 // Filter
            sendWelcomeEmail.andThen(addToNewsletterList)  // Combined actions
        );

        // Processing pipeline: filter seniors and offer discount
        System.out.println("\nProcessing seniors:");
        processElements(
            people,              // Source list
            isSenior,            // Filter
            offerSeniorDiscount  // Action
        );

        // Advanced example: Custom data processing workflow
        System.out.println("\nAdvanced data processing workflow:");
        dataProcessingWorkflow();
    }

    /**
     * Helper method to filter a list using a predicate
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
     * Helper method to print a list of people
     */
    private static void printPeople(List<Person> people) {
        for (Person person : people) {
            System.out.println(person.getName() + ", " + person.getAge());
        }
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
     * Helper method to process elements with predicate and consumer
     */
    private static <T> void processElements(List<T> list, Predicate<T> predicate, Consumer<T> consumer) {
        for (T item : list) {
            if (predicate.test(item)) {
                consumer.accept(item);
            }
        }
    }

    /**
     * More complex example showing a data processing workflow
     */
    private static void dataProcessingWorkflow() {
        // Custom data class for this example
        class DataRecord {
            private String id;
            private double value;
            private boolean valid;

            public DataRecord(String id, double value, boolean valid) {
                this.id = id;
                this.value = value;
                this.valid = valid;
            }

            public String getId() { return id; }
            public double getValue() { return value; }
            public boolean isValid() { return valid; }

            @Override
            public String toString() {
                return "Record [id=" + id + ", value=" + value + ", valid=" + valid + "]";
            }
        }

        // Supplier to generate random data records
        Supplier<DataRecord> recordSupplier = () -> {
            String id = "REC" + (100 + new Random().nextInt(900));  // REC100-REC999
            double value = Math.round(new Random().nextDouble() * 1000) / 10.0;  // 0.0-100.0
            boolean valid = new Random().nextBoolean();  // 50% valid
            return new DataRecord(id, value, valid);
        };

        // Generate sample data
        List<DataRecord> records = generateList(10, recordSupplier);

        System.out.println("Generated data records:");
        records.forEach(System.out::println);

        // Predicates for filtering
        Predicate<DataRecord> isValid = DataRecord::isValid;
        Predicate<DataRecord> isHighValue = record -> record.getValue() > 50.0;

        // Consumers for processing
        Consumer<DataRecord> logRecord = record -> 
            System.out.println("Logging: " + record);
        Consumer<DataRecord> processRecord = record -> 
            System.out.println("Processing record " + record.getId() + 
                               " with value " + record.getValue());
        Consumer<DataRecord> flagHighValue = record -> 
            System.out.println("High value alert for " + record.getId() + ": " + record.getValue());

        // Data processing pipeline
        System.out.println("\nProcessing valid records:");
        processElements(records, isValid, logRecord.andThen(processRecord));

        System.out.println("\nFlagging high value records:");
        processElements(records, isValid.and(isHighValue), flagHighValue);

        // Summary statistics
        System.out.println("\nSummary statistics:");
        int validCount = filterList(records, isValid).size();
        int highValueCount = filterList(records, isValid.and(isHighValue)).size();

        System.out.println("Total records: " + records.size());
        System.out.println("Valid records: " + validCount);
        System.out.println("High value records: " + highValueCount);
    }
}

/**
 * Custom Supplier implementation with state
 */
class CounterSupplier implements Supplier<Integer> {
    private int count = 0;

    @Override
    public Integer get() {
        return ++count;  // Increment and return
    }
}
