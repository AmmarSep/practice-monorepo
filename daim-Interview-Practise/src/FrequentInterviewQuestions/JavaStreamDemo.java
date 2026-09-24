package FrequentInterviewQuestions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class demonstrates the Stream API introduced in Java 8
 * 
 * Streams provide a high-level abstraction for processing sequences of elements.
 * They allow for functional-style operations on collections and other data sources.
 * 
 * Key characteristics:
 * - Not a data structure, but a view of the data
 * - Functional in nature (doesn't modify the source)
 * - Lazily evaluated (operations are only performed when needed)
 * - May be parallel with minimal effort
 * - Designed for lambdas
 */
public class JavaStreamDemo {

    public static void main(String[] args) {
        System.out.println("===== Java Stream API Demonstration =====");

        // 1. Creating Streams
        demonstrateStreamCreation();

        // 2. Intermediate Operations
        demonstrateIntermediateOperations();

        // 3. Terminal Operations
        demonstrateTerminalOperations();

        // 4. Specialized Stream Types
        demonstrateSpecializedStreams();

        // 5. Advanced Stream Operations
        demonstrateAdvancedOperations();

        // 6. Parallel Streams
        demonstrateParallelStreams();
    }

    /**
     * Demonstrates various ways to create streams
     */
    private static void demonstrateStreamCreation() {
        System.out.println("\n1. Creating Streams:\n");

        // From a collection
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> streamFromList = list.stream();
        System.out.println("Stream from list: " + 
                streamFromList.collect(Collectors.joining(", ")));

        // From individual elements
        Stream<String> streamFromValues = Stream.of("x", "y", "z");
        System.out.println("Stream from values: " + 
                streamFromValues.collect(Collectors.joining(", ")));

        // From an array
        String[] array = {"one", "two", "three"};
        Stream<String> streamFromArray = Arrays.stream(array);
        System.out.println("Stream from array: " + 
                streamFromArray.collect(Collectors.joining(", ")));

        // From a range of numbers
        IntStream streamFromRange = IntStream.rangeClosed(1, 5);  // 1 to 5 inclusive
        System.out.println("Stream from range: " + 
                streamFromRange.boxed().collect(Collectors.toList()));

        // Generate an infinite stream (limiting it)
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2);
        System.out.println("Even numbers (infinite stream limited to 5): " + 
                infiniteStream.limit(5).collect(Collectors.toList()));

        // Generate random numbers
        Stream<Double> randomStream = Stream.generate(Math::random);
        System.out.println("5 random numbers: " + 
                randomStream.limit(5).collect(Collectors.toList()));

        // Empty stream
        Stream<String> emptyStream = Stream.empty();
        System.out.println("Empty stream count: " + emptyStream.count());
    }

    /**
     * Demonstrates intermediate operations on streams
     * (operations that transform a stream into another stream)
     */
    private static void demonstrateIntermediateOperations() {
        System.out.println("\n2. Intermediate Operations:\n");

        List<String> words = Arrays.asList(
                "Java", "Stream", "API", "programming", "functional", 
                "lambda", "collection", "parallel", "Java", "stream");

        // filter - keeps elements that match the predicate
        System.out.println("\nfilter operation - words with length > 5:");
        words.stream()
             .filter(word -> word.length() > 5)
             .forEach(System.out::println);

        // map - transforms each element
        System.out.println("\nmap operation - convert to uppercase:");
        words.stream()
             .map(String::toUpperCase)
             .forEach(System.out::println);

        // distinct - removes duplicates
        System.out.println("\ndistinct operation - unique words:");
        words.stream()
             .distinct()
             .forEach(System.out::println);

        // sorted - sorts elements
        System.out.println("\nsorted operation - alphabetical order:");
        words.stream()
             .sorted()
             .forEach(System.out::println);

        // sorted with custom comparator - by length
        System.out.println("\nsorted with comparator - by word length:");
        words.stream()
             .sorted(Comparator.comparing(String::length))
             .forEach(word -> System.out.println(word + " (" + word.length() + ")"));

        // limit - restricts size
        System.out.println("\nlimit operation - first 3 words:");
        words.stream()
             .limit(3)
             .forEach(System.out::println);

        // skip - discards first n elements
        System.out.println("\nskip operation - skip first 3 words:");
        words.stream()
             .skip(3)
             .forEach(System.out::println);

        // peek - performs action on each element without modifying stream
        System.out.println("\npeek operation - logging before processing:");
        words.stream()
             .peek(word -> System.out.println("Processing: " + word))
             .map(String::toUpperCase)
             .limit(3)
             .forEach(System.out::println);

        // Chaining multiple operations
        System.out.println("\nChaining operations - first 3 unique words of length > 4 in uppercase:");
        words.stream()
             .filter(word -> word.length() > 4)  // Keep words longer than 4 chars
             .distinct()                          // Remove duplicates
             .limit(3)                            // Take only first 3
             .map(String::toUpperCase)            // Convert to uppercase
             .forEach(System.out::println);       // Print each word
    }

    /**
     * Demonstrates terminal operations on streams
     * (operations that produce a result or side-effect and terminate the stream)
     */
    private static void demonstrateTerminalOperations() {
        System.out.println("\n3. Terminal Operations:\n");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // forEach - performs an action on each element
        System.out.println("forEach operation - printing each number:");
        numbers.stream()
               .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // count - returns the number of elements
        long count = numbers.stream().count();
        System.out.println("\ncount operation - number of elements: " + count);

        // anyMatch, allMatch, noneMatch - testing elements
        boolean anyMatch = numbers.stream().anyMatch(n -> n > 5);
        boolean allMatch = numbers.stream().allMatch(n -> n > 0);
        boolean noneMatch = numbers.stream().noneMatch(n -> n < 0);

        System.out.println("\nMatching operations:");
        System.out.println("anyMatch (any > 5): " + anyMatch);
        System.out.println("allMatch (all > 0): " + allMatch);
        System.out.println("noneMatch (none < 0): " + noneMatch);

        // findFirst, findAny - finding elements
        Optional<Integer> first = numbers.stream().findFirst();
        Optional<Integer> any = numbers.stream().findAny();

        System.out.println("\nFinding operations:");
        System.out.println("findFirst: " + first.orElse(0));
        System.out.println("findAny: " + any.orElse(0));

        // min, max - finding extremes
        Optional<Integer> min = numbers.stream().min(Integer::compareTo);
        Optional<Integer> max = numbers.stream().max(Integer::compareTo);

        System.out.println("\nMin/Max operations:");
        System.out.println("min: " + min.orElse(0));
        System.out.println("max: " + max.orElse(0));

        // reduce - combining elements
        Optional<Integer> sum = numbers.stream().reduce((a, b) -> a + b);
        Optional<Integer> product = numbers.stream().reduce((a, b) -> a * b);

        System.out.println("\nReduce operations:");
        System.out.println("sum: " + sum.orElse(0));
        System.out.println("product: " + product.orElse(0));

        // reduce with identity value
        int sumWithIdentity = numbers.stream().reduce(0, Integer::sum);
        System.out.println("sum with identity: " + sumWithIdentity);

        // collect - gathering results into a collection
        List<Integer> collectedList = numbers.stream()
                                           .filter(n -> n % 2 == 0)  // even numbers
                                           .collect(Collectors.toList());

        Set<Integer> collectedSet = numbers.stream()
                                         .filter(n -> n % 2 == 0)  // even numbers
                                         .collect(Collectors.toSet());

        String joined = numbers.stream()
                             .map(String::valueOf)
                             .collect(Collectors.joining(", "));

        System.out.println("\nCollect operations:");
        System.out.println("collected list of even numbers: " + collectedList);
        System.out.println("collected set of even numbers: " + collectedSet);
        System.out.println("joined numbers: " + joined);
    }

    /**
     * Demonstrates specialized stream types for primitives
     */
    private static void demonstrateSpecializedStreams() {
        System.out.println("\n4. Specialized Stream Types:\n");

        // IntStream - stream of primitive ints
        IntStream intStream = IntStream.rangeClosed(1, 10);
        System.out.println("IntStream sum: " + intStream.sum());

        // Using boxed to convert to Stream<Integer>
        List<Integer> boxedInts = IntStream.range(1, 6)
                                          .boxed()
                                          .collect(Collectors.toList());
        System.out.println("Boxed integers: " + boxedInts);

        // Generate prime numbers (for demonstration)
        List<Integer> primes = IntStream.rangeClosed(2, 30)
                                      .filter(JavaStreamDemo::isPrime)
                                      .boxed()
                                      .collect(Collectors.toList());
        System.out.println("Prime numbers from 2 to 30: " + primes);

        // IntStream operations
        IntSummaryStatistics stats = IntStream.rangeClosed(1, 100).summaryStatistics();
        System.out.println("\nIntStream statistics:");
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Average: " + stats.getAverage());

        // Converting between object and primitive streams
        System.out.println("\nConverting between object and primitive streams:");

        // Stream<Integer> to IntStream
        IntStream intStreamFromObjects = Arrays.asList(1, 2, 3, 4, 5).stream()
                                             .mapToInt(Integer::intValue);
        System.out.println("Sum from Integer stream: " + intStreamFromObjects.sum());

        // IntStream to Stream<Integer>
        Stream<Integer> objectStreamFromInts = IntStream.range(1, 6).boxed();
        System.out.println("Objects from IntStream: " + 
                objectStreamFromInts.collect(Collectors.toList()));
    }

    /**
     * Demonstrates advanced stream operations like grouping, partitioning, mapping
     */
    private static void demonstrateAdvancedOperations() {
        System.out.println("\n5. Advanced Stream Operations:\n");

        // Sample data for demonstrations
        List<Employee> employees = Arrays.asList(
            new Employee("John", "IT", 5000),
            new Employee("Alice", "HR", 4500),
            new Employee("Bob", "IT", 6000),
            new Employee("Carol", "Finance", 5500),
            new Employee("David", "HR", 4000),
            new Employee("Eve", "IT", 7000),
            new Employee("Frank", "Finance", 6500)
        );

        // Grouping by department
        Map<String, List<Employee>> byDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("Grouping employees by department:");
        byDepartment.forEach((dept, emps) -> {
            System.out.println(dept + " department employees:");
            emps.forEach(e -> System.out.println("  " + e.getName() + " - $" + e.getSalary()));
        });

        // Partitioning into high earners and others
        Map<Boolean, List<Employee>> partitionedBySalary = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 5000));

        System.out.println("\nPartitioning employees by salary (> $5000):");
        System.out.println("High earners:");
        partitionedBySalary.get(true).forEach(e -> 
            System.out.println("  " + e.getName() + " - $" + e.getSalary()));

        System.out.println("Others:");
        partitionedBySalary.get(false).forEach(e -> 
            System.out.println("  " + e.getName() + " - $" + e.getSalary()));

        // Complex grouping and aggregation
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));

        System.out.println("\nAverage salary by department:");
        avgSalaryByDept.forEach((dept, avg) -> 
            System.out.println(dept + " department average: $" + Math.round(avg)));

        // Getting the highest paid employee in each department
        Map<String, Optional<Employee>> topEarnerByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary))));

        System.out.println("\nHighest paid employee by department:");
        topEarnerByDept.forEach((dept, empOpt) -> {
            empOpt.ifPresent(emp -> 
                System.out.println(dept + " department: " + emp.getName() + 
                                   " - $" + emp.getSalary()));
        });

        // Mapping to get just names by department
        Map<String, List<String>> namesByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(
                                Employee::getName,
                                Collectors.toList())));

        System.out.println("\nEmployee names by department:");
        namesByDept.forEach((dept, names) -> 
            System.out.println(dept + ": " + String.join(", ", names)));

        // Total salary by department
        Map<String, Double> totalSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)));

        System.out.println("\nTotal salary by department:");
        totalSalaryByDept.forEach((dept, total) -> 
            System.out.println(dept + ": $" + total));

        // Creating a frequency map of departments
        Map<String, Long> deptCount = employees.stream()
                .map(Employee::getDepartment)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("\nDepartment frequencies:");
        deptCount.forEach((dept, count) -> 
            System.out.println(dept + ": " + count + " employees"));
    }

    /**
     * Demonstrates parallel streams for concurrent processing
     */
    private static void demonstrateParallelStreams() {
        System.out.println("\n6. Parallel Streams:\n");

        // Creating a large list for demonstration
        List<Integer> largeList = IntStream.rangeClosed(1, 10_000_000)
                                          .boxed()
                                          .collect(Collectors.toList());

        System.out.println("Processing a large list of " + largeList.size() + " elements");

        // Sequential sum - single thread
        long startSeq = System.currentTimeMillis();
        long sequentialSum = largeList.stream().reduce(0, Integer::sum);
        long endSeq = System.currentTimeMillis();

        // Parallel sum - multiple threads
        long startPar = System.currentTimeMillis();
        long parallelSum = largeList.parallelStream().reduce(0, Integer::sum);
        long endPar = System.currentTimeMillis();

        System.out.println("\nPerformance comparison:");
        System.out.println("Sequential sum: " + sequentialSum + ", Time: " + (endSeq - startSeq) + "ms");
        System.out.println("Parallel sum: " + parallelSum + ", Time: " + (endPar - startPar) + "ms");

        // Note: Results will vary based on hardware and JVM conditions
        System.out.println("\nParallel stream considerations:");
        System.out.println("- Beneficial for large data sets and computationally intensive operations");
        System.out.println("- May introduce overhead for small data sets");
        System.out.println("- Requires thread-safe operations");
        System.out.println("- Order is not guaranteed unless specifically enforced");
        System.out.println("- Performance varies based on hardware (CPU cores) and workload");

        // Demonstrating parallel sorting
        System.out.println("\nParallel sorting example:");
        List<Integer> shuffledList = IntStream.rangeClosed(1, 1_000_000)
                                           .mapToObj(i -> (int)(Math.random() * 1_000_000))
                                           .collect(Collectors.toList());

        long startSeqSort = System.currentTimeMillis();
        List<Integer> sequentiallySorted = shuffledList.stream()
                                                     .sorted()
                                                     .collect(Collectors.toList());
        long endSeqSort = System.currentTimeMillis();

        long startParSort = System.currentTimeMillis();
        List<Integer> parallellySorted = shuffledList.parallelStream()
                                                   .sorted()
                                                   .collect(Collectors.toList());
        long endParSort = System.currentTimeMillis();

        System.out.println("Sequential sort time: " + (endSeqSort - startSeqSort) + "ms");
        System.out.println("Parallel sort time: " + (endParSort - startParSort) + "ms");
        System.out.println("Are results equal? " + sequentiallySorted.equals(parallellySorted));
    }

    /**
     * Helper method to check if a number is prime
     */
    private static boolean isPrime(int number) {
        if (number < 2) return false;
        if (number == 2 || number == 3) return true;
        if (number % 2 == 0) return false;

        int sqrtNum = (int) Math.sqrt(number) + 1;
        for (int i = 3; i < sqrtNum; i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }
}

class Employee {
    private String name;
    private String department;
    private double salary;

    public Employee(String name, String department, double salary) {
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
