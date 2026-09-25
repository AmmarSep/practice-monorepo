package Fresher;

import java.util.*;

/**
 * This class demonstrates various collection types in Java Collections Framework
 * 1. List - ArrayList, LinkedList
 * 2. Set - HashSet, TreeSet
 * 3. Map - HashMap, TreeMap
 * 4. Queue - PriorityQueue
 * 5. Stack
 */
public class CollectionsDemo {

    public static void main(String[] args) {
        System.out.println("=== Java Collections Framework Demo ===\n");

        // 1. List Examples
        demonstrateListCollections();

        // 2. Set Examples
        demonstrateSetCollections();

        // 3. Map Examples
        demonstrateMapCollections();

        // 4. Queue Examples
        demonstrateQueueCollections();

        // 5. Stack Example
        demonstrateStackCollection();

        // 6. Collections Utility Methods
        demonstrateCollectionsUtilities();
    }

    // Demonstrate List implementations: ArrayList and LinkedList
    private static void demonstrateListCollections() {
        System.out.println("1. List Collections:\n");

        // ArrayList - Backed by a dynamic array
        System.out.println("ArrayList:");
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Orange");
        arrayList.add("Apple"); // Lists allow duplicates

        System.out.println("Elements: " + arrayList);
        System.out.println("Size: " + arrayList.size());
        System.out.println("Contains 'Orange': " + arrayList.contains("Orange"));
        System.out.println("Element at index 1: " + arrayList.get(1));

        // Iterating using enhanced for loop
        System.out.println("\nIterating using enhanced for loop:");
        for (String fruit : arrayList) {
            System.out.println("- " + fruit);
        }

        // LinkedList - Implemented as a doubly linked list
        System.out.println("\nLinkedList:");
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Blue");

        // LinkedList specific operations
        linkedList.addFirst("First"); // Add at the beginning
        linkedList.addLast("Last");   // Add at the end

        System.out.println("Elements: " + linkedList);
        System.out.println("First element: " + linkedList.getFirst());
        System.out.println("Last element: " + linkedList.getLast());

        // ArrayList vs LinkedList performance comparison
        System.out.println("\nPerformance Comparison:");
        List<Integer> arrayListPerf = new ArrayList<>();
        List<Integer> linkedListPerf = new LinkedList<>();

        // Measure time for adding elements at the beginning
        int iterations = 50000;

        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            arrayListPerf.add(0, i); // Add at the beginning
        }
        long arrayListTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            linkedListPerf.add(0, i); // Add at the beginning
        }
        long linkedListTime = System.nanoTime() - startTime;

        System.out.println("Time to add " + iterations + " elements at the beginning:");
        System.out.println("ArrayList: " + arrayListTime + " ns");
        System.out.println("LinkedList: " + linkedListTime + " ns");
        System.out.println("Ratio: " + (double)arrayListTime/linkedListTime + "x");

        System.out.println();
    }

    // Demonstrate Set implementations: HashSet and TreeSet
    private static void demonstrateSetCollections() {
        System.out.println("2. Set Collections:\n");

        // HashSet - No guaranteed order, no duplicates
        System.out.println("HashSet:");
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Dog");
        hashSet.add("Cat");
        hashSet.add("Bird");
        hashSet.add("Dog"); // Duplicate - will be ignored

        System.out.println("Elements: " + hashSet);
        System.out.println("Size: " + hashSet.size());
        System.out.println("Contains 'Cat': " + hashSet.contains("Cat"));

        // TreeSet - Sorted set, no duplicates
        System.out.println("\nTreeSet:");
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Zebra");
        treeSet.add("Elephant");
        treeSet.add("Lion");
        treeSet.add("Zebra"); // Duplicate - will be ignored

        System.out.println("Elements (sorted): " + treeSet);
        System.out.println("Size: " + treeSet.size());
        System.out.println("First (lowest) element: " + ((TreeSet<String>) treeSet).first());
        System.out.println("Last (highest) element: " + ((TreeSet<String>) treeSet).last());

        // LinkedHashSet - Maintains insertion order, no duplicates
        System.out.println("\nLinkedHashSet:");
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("One");
        linkedHashSet.add("Two");
        linkedHashSet.add("Three");
        linkedHashSet.add("One"); // Duplicate - will be ignored

        System.out.println("Elements (insertion order): " + linkedHashSet);

        System.out.println();
    }

    // Demonstrate Map implementations: HashMap and TreeMap
    private static void demonstrateMapCollections() {
        System.out.println("3. Map Collections:\n");

        // HashMap - No guaranteed order
        System.out.println("HashMap:");
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("John", 28);
        hashMap.put("Sarah", 22);
        hashMap.put("Mark", 30);
        hashMap.put("John", 29); // Duplicate key - value will be updated

        System.out.println("Entries: " + hashMap);
        System.out.println("Size: " + hashMap.size());
        System.out.println("Age of Mark: " + hashMap.get("Mark"));
        System.out.println("Contains key 'Sarah': " + hashMap.containsKey("Sarah"));
        System.out.println("Contains value 25: " + hashMap.containsValue(25));

        // Iterating through a Map
        System.out.println("\nIterating through HashMap:");
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " is " + entry.getValue() + " years old");
        }

        // TreeMap - Sorted by keys
        System.out.println("\nTreeMap:");
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("USA", "Washington D.C.");
        treeMap.put("India", "New Delhi");
        treeMap.put("Japan", "Tokyo");
        treeMap.put("France", "Paris");

        System.out.println("Entries (sorted by key): " + treeMap);

        // LinkedHashMap - Maintains insertion order
        System.out.println("\nLinkedHashMap:");
        Map<String, Double> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("Pi", 3.14159);
        linkedHashMap.put("Euler's Number", 2.71828);
        linkedHashMap.put("Golden Ratio", 1.61803);

        System.out.println("Entries (insertion order): " + linkedHashMap);

        System.out.println();
    }

    // Demonstrate Queue implementations: PriorityQueue
    private static void demonstrateQueueCollections() {
        System.out.println("4. Queue Collections:\n");

        // PriorityQueue - Elements are processed according to priority
        System.out.println("PriorityQueue:");
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(10);
        priorityQueue.add(5);
        priorityQueue.add(15);
        priorityQueue.add(3);

        System.out.println("Elements: " + priorityQueue); // Order may not reflect priority
        System.out.println("Size: " + priorityQueue.size());

        // Poll retrieves and removes the head of the queue (smallest element)
        System.out.println("\nProcessing elements in priority order:");
        while (!priorityQueue.isEmpty()) {
            System.out.println("Processing: " + priorityQueue.poll());
        }

        // Deque - Double-ended queue using LinkedList
        System.out.println("\nDeque (LinkedList):");
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("First");
        deque.addLast("Last");
        deque.add("Middle"); // Adds to the end

        System.out.println("Elements: " + deque);
        System.out.println("First element: " + deque.peekFirst());
        System.out.println("Last element: " + deque.peekLast());

        System.out.println();
    }

    // Demonstrate Stack
    private static void demonstrateStackCollection() {
        System.out.println("5. Stack Collection:\n");

        Stack<String> stack = new Stack<>();
        stack.push("Bottom");
        stack.push("Middle");
        stack.push("Top");

        System.out.println("Stack: " + stack);
        System.out.println("Size: " + stack.size());
        System.out.println("Top element (peek): " + stack.peek());

        System.out.println("\nPopping elements:");
        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }

        System.out.println();
    }

    // Demonstrate Collections utility methods
    private static void demonstrateCollectionsUtilities() {
        System.out.println("6. Collections Utility Methods:\n");

        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(9);

        System.out.println("Original list: " + numbers);

        // Sorting
        Collections.sort(numbers);
        System.out.println("After sorting: " + numbers);

        // Reverse
        Collections.reverse(numbers);
        System.out.println("After reversing: " + numbers);

        // Shuffle
        Collections.shuffle(numbers);
        System.out.println("After shuffling: " + numbers);

        // Min and Max
        System.out.println("Minimum: " + Collections.min(numbers));
        System.out.println("Maximum: " + Collections.max(numbers));

        // Binary search (requires sorted list)
        Collections.sort(numbers);
        int index = Collections.binarySearch(numbers, 5);
        System.out.println("\nSorted list: " + numbers);
        System.out.println("Index of 5: " + index);

        // Frequency and disjoint
        List<String> list1 = Arrays.asList("A", "B", "C", "A");
        List<String> list2 = Arrays.asList("D", "E", "F");

        System.out.println("\nFrequency of 'A' in " + list1 + ": " + Collections.frequency(list1, "A"));
        System.out.println("Are " + list1 + " and " + list2 + " disjoint? " + Collections.disjoint(list1, list2));

        // Unmodifiable collections
        List<String> unmodifiableList = Collections.unmodifiableList(new ArrayList<>(list1));
        System.out.println("\nUnmodifiable list: " + unmodifiableList);
        System.out.println("Attempting to modify the unmodifiable list will throw UnsupportedOperationException");

        // Uncomment to see the exception
        // unmodifiableList.add("X"); // This will throw UnsupportedOperationException
    }
}
