package java;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * This class demonstrates various Collection-related concepts in Java
 * 1. HashMap and its internal working
 * 2. Array vs ArrayList
 * 3. List vs Set
 * 4. HashMap vs ConcurrentHashMap vs Synchronized HashMap
 * 5. Fail-Fast vs Fail-Safe Iterators
 */
public class CollectionConcepts {

    public static void main(String[] args) {
        System.out.println("===== Collection Concepts Demonstration =====");
        
        // Demonstrate HashMap and its internal working
        demonstrateHashMap();
        
        // Demonstrate Array vs ArrayList
        demonstrateArrayVsArrayList();
        
        // Demonstrate List vs Set
        demonstrateListVsSet();
        
        // Demonstrate HashMap vs ConcurrentHashMap vs Synchronized HashMap
        demonstrateHashMapVariants();
        
        // Demonstrate Fail-Fast vs Fail-Safe Iterators
        demonstrateFailFastVsFailSafe();
    }
    
    /**
     * HashMap is a data structure that stores key-value pairs.
     * 
     * Internal Working of HashMap:
     * 1. Uses an array of Node<K,V> (called table) to store entries
     * 2. Each Node contains key, value, hash, and next pointer (for linked list)
     * 3. Uses hashCode() of key to determine the bucket (index in array)
     * 4. In case of hash collision, entries form a linked list (or a tree in Java 8+)
     * 5. Load factor determines when to resize the table (default 0.75)
     * 6. When number of entries > capacity * load factor, the table is resized
     */
    private static void demonstrateHashMap() {
        System.out.println("\n1. HashMap and Its Internal Working:\n");
        
        // Creating a HashMap
        HashMap<String, Integer> map = new HashMap<>();
        
        // Adding key-value pairs
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("Four", 4);
        
        System.out.println("HashMap: " + map);
        
        // Accessing values
        System.out.println("Value for key 'Two': " + map.get("Two"));
        
        // Checking if key exists
        System.out.println("Contains key 'Five': " + map.containsKey("Five"));
        
        // Checking if value exists
        System.out.println("Contains value 3: " + map.containsValue(3));
        
        // Removing a key-value pair
        map.remove("Three");
        System.out.println("After removing 'Three': " + map);
        
        // Iterating over a HashMap
        System.out.println("\nIterating over HashMap:");
        
        // Using entrySet
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        
        // Using keySet and get
        for (String key : map.keySet()) {
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }
        
        // Using forEach (Java 8)
        map.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
        
        // Explaining hash collisions
        System.out.println("\nHash Collision Explanation:");
        System.out.println("When two different keys have the same hash code, a collision occurs.");
        System.out.println("HashMap handles collisions by using a linked list (or tree) of entries.");
        System.out.println("In Java 8+, if a bucket has more than 8 entries, the linked list is converted to a balanced tree.");
        
        // Demonstrating a hash collision (for educational purposes)
        // Note: This is a contrived example using a custom class with a poor hashCode implementation
        class BadHashKey {
            private String value;
            
            public BadHashKey(String value) {
                this.value = value;
            }
            
            @Override
            public int hashCode() {
                return 1;  // All instances return the same hash code (very bad practice!)
            }
            
            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                BadHashKey that = (BadHashKey) obj;
                return Objects.equals(value, that.value);
            }
            
            @Override
            public String toString() {
                return value;
            }
        }
        
        HashMap<BadHashKey, String> collisionMap = new HashMap<>();
        collisionMap.put(new BadHashKey("Key1"), "Value1");
        collisionMap.put(new BadHashKey("Key2"), "Value2");
        collisionMap.put(new BadHashKey("Key3"), "Value3");
        
        System.out.println("\nMap with hash collisions: " + collisionMap);
        System.out.println("Even though all keys have the same hash code, HashMap still works correctly");
        System.out.println("But performance degrades from O(1) to O(n) for lookups in the worst case");
    }
    
    /**
     * Comparison between Array and ArrayList:
     * 
     * Array:
     * 1. Fixed size (cannot be resized after creation)
     * 2. Can store primitives or objects
     * 3. Faster access time (slightly)
     * 4. Less memory overhead
     * 5. No built-in methods for manipulation
     * 
     * ArrayList:
     * 1. Dynamic size (can grow or shrink)
     * 2. Can only store objects (uses autoboxing for primitives)
     * 3. Slightly slower access time
     * 4. More memory overhead
     * 5. Many built-in methods for manipulation (add, remove, etc.)
     */
    private static void demonstrateArrayVsArrayList() {
        System.out.println("\n2. Array vs ArrayList:\n");
        
        // Array declaration and initialization
        int[] intArray = new int[5];  // Fixed size
        intArray[0] = 10;
        intArray[1] = 20;
        intArray[2] = 30;
        intArray[3] = 40;
        intArray[4] = 50;
        
        // Cannot add more elements than the size
        // This would cause ArrayIndexOutOfBoundsException: intArray[5] = 60;
        
        System.out.println("Array elements:");
        for (int i = 0; i < intArray.length; i++) {
            System.out.println("intArray[" + i + "] = " + intArray[i]);
        }
        
        // ArrayList declaration and initialization
        ArrayList<Integer> intList = new ArrayList<>();  // Dynamic size
        intList.add(10);
        intList.add(20);
        intList.add(30);
        intList.add(40);
        intList.add(50);
        
        // Can add more elements (dynamic sizing)
        intList.add(60);
        
        System.out.println("\nArrayList elements:");
        for (int i = 0; i < intList.size(); i++) {
            System.out.println("intList.get(" + i + ") = " + intList.get(i));
        }
        
        // ArrayList provides many useful methods
        intList.remove(1);  // Remove element at index 1
        System.out.println("\nAfter removing element at index 1: " + intList);
        
        intList.add(2, 25);  // Insert 25 at index 2
        System.out.println("After adding 25 at index 2: " + intList);
        
        System.out.println("Contains 30? " + intList.contains(30));
        System.out.println("Index of 40: " + intList.indexOf(40));
        
        // Converting ArrayList to Array
        Integer[] convertedArray = intList.toArray(new Integer[0]);
        System.out.println("\nConverted to array: " + Arrays.toString(convertedArray));
        
        // Converting Array to ArrayList
        List<Integer> convertedList = Arrays.asList(convertedArray);
        System.out.println("Converted back to list: " + convertedList);
        
        // Performance comparison
        System.out.println("\nPerformance comparison:");
        System.out.println("Arrays have slightly better performance for random access");
        System.out.println("ArrayLists are better for dynamic operations (add/remove)");
        
        // Memory usage
        System.out.println("\nMemory usage:");
        System.out.println("Arrays use less memory as they don't have the overhead of an ArrayList");
        System.out.println("ArrayLists maintain extra capacity for growth (typically 50% more than size)");
    }
    
    /**
     * Comparison between List and Set:
     * 
     * List:
     * 1. Ordered collection (maintains insertion order)
     * 2. Allows duplicate elements
     * 3. Allows null elements (can have multiple nulls)
     * 4. Examples: ArrayList, LinkedList, Vector
     * 
     * Set:
     * 1. Unordered collection (except LinkedHashSet)
     * 2. Does not allow duplicate elements
     * 3. Allows at most one null element (except TreeSet which doesn't allow null)
     * 4. Examples: HashSet, LinkedHashSet, TreeSet
     */
    private static void demonstrateListVsSet() {
        System.out.println("\n3. List vs Set:\n");
        
        // List demonstration
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add("Apple");  // Duplicate allowed
        list.add(null);     // Null allowed
        list.add(null);     // Multiple nulls allowed
        
        System.out.println("List: " + list);
        System.out.println("List size: " + list.size());
        System.out.println("List maintains insertion order and allows duplicates");
        
        // Set demonstration
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Cherry");
        hashSet.add("Apple");  // Duplicate not added
        hashSet.add(null);     // One null allowed
        
        System.out.println("\nHashSet: " + hashSet);
        System.out.println("HashSet size: " + hashSet.size());
        System.out.println("HashSet does not maintain order and doesn't allow duplicates");
        
        // LinkedHashSet maintains insertion order
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Apple");
        linkedHashSet.add("Banana");
        linkedHashSet.add("Cherry");
        linkedHashSet.add("Apple");  // Duplicate not added
        
        System.out.println("\nLinkedHashSet: " + linkedHashSet);
        System.out.println("LinkedHashSet maintains insertion order but doesn't allow duplicates");
        
        // TreeSet sorts elements
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Cherry");
        treeSet.add("Apple");
        treeSet.add("Banana");
        treeSet.add("Apple");  // Duplicate not added
        // treeSet.add(null);  // Would throw NullPointerException
        
        System.out.println("\nTreeSet: " + treeSet);
        System.out.println("TreeSet sorts elements and doesn't allow duplicates or nulls");
        
        // Common operations
        System.out.println("\nCommon operations:");
        
        // Contains
        System.out.println("List contains 'Banana': " + list.contains("Banana"));
        System.out.println("HashSet contains 'Banana': " + hashSet.contains("Banana"));
        
        // Remove
        list.remove("Cherry");
        hashSet.remove("Cherry");
        System.out.println("List after removing 'Cherry': " + list);
        System.out.println("HashSet after removing 'Cherry': " + hashSet);
        
        // When to use List vs Set
        System.out.println("\nWhen to use List vs Set:");
        System.out.println("Use List when: Order matters, duplicates are allowed, or you need random access by index");
        System.out.println("Use Set when: Order doesn't matter, duplicates are not allowed, or you need fast lookups");
    }
    
    /**
     * Comparison between HashMap, ConcurrentHashMap, and Synchronized HashMap:
     * 
     * HashMap:
     * 1. Not thread-safe
     * 2. Better performance in single-threaded environments
     * 3. Allows one null key and multiple null values
     * 
     * Synchronized HashMap (Collections.synchronizedMap):
     * 1. Thread-safe by synchronizing the entire map
     * 2. Lower performance due to locking the entire map for each operation
     * 3. Allows one null key and multiple null values
     * 
     * ConcurrentHashMap:
     * 1. Thread-safe by using a segmented locking mechanism
     * 2. Better performance in multi-threaded environments
     * 3. Does not allow null keys or values
     * 4. Provides atomic operations like putIfAbsent, replace
     */
    private static void demonstrateHashMapVariants() {
        System.out.println("\n4. HashMap vs ConcurrentHashMap vs Synchronized HashMap:\n");
        
        // HashMap - not thread-safe
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("One", 1);
        hashMap.put("Two", 2);
        hashMap.put("Three", 3);
        hashMap.put(null, 0);  // Allows null key
        
        System.out.println("HashMap: " + hashMap);
        System.out.println("HashMap allows null keys and values");
        System.out.println("HashMap is not thread-safe");
        
        // Synchronized HashMap - thread-safe but locks the entire map
        Map<String, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        synchronizedMap.put("One", 1);
        synchronizedMap.put("Two", 2);
        synchronizedMap.put("Three", 3);
        synchronizedMap.put(null, 0);  // Allows null key
        
        System.out.println("\nSynchronized HashMap: " + synchronizedMap);
        System.out.println("Synchronized HashMap allows null keys and values");
        System.out.println("Synchronized HashMap is thread-safe but locks the entire map for each operation");
        
        // ConcurrentHashMap - thread-safe with segment locking
        Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("One", 1);
        concurrentHashMap.put("Two", 2);
        concurrentHashMap.put("Three", 3);
        // concurrentHashMap.put(null, 0);  // Would throw NullPointerException
        
        System.out.println("\nConcurrentHashMap: " + concurrentHashMap);
        System.out.println("ConcurrentHashMap does not allow null keys or values");
        System.out.println("ConcurrentHashMap is thread-safe with better concurrency");
        
        // Atomic operations in ConcurrentHashMap
        concurrentHashMap.putIfAbsent("Four", 4);  // Add only if key doesn't exist
        concurrentHashMap.replace("Two", 2, 22);   // Replace only if current value matches
        
        System.out.println("\nConcurrentHashMap after atomic operations: " + concurrentHashMap);
        
        // Performance characteristics
        System.out.println("\nPerformance characteristics:");
        System.out.println("HashMap: Best for single-threaded applications");
        System.out.println("Synchronized HashMap: Simple thread-safety but poor concurrency");
        System.out.println("ConcurrentHashMap: Best for multi-threaded applications with high concurrency");
        
        // Use cases
        System.out.println("\nUse cases:");
        System.out.println("HashMap: Use when thread-safety is not required");
        System.out.println("Synchronized HashMap: Use when simple thread-safety is needed and concurrency is low");
        System.out.println("ConcurrentHashMap: Use when high concurrency and thread-safety are required");
    }
    
    /**
     * Fail-Fast vs Fail-Safe Iterators:
     * 
     * Fail-Fast Iterators:
     * 1. Throw ConcurrentModificationException if collection is modified during iteration
     * 2. Use a modification counter to detect changes
     * 3. Examples: Iterator from ArrayList, HashMap, HashSet
     * 
     * Fail-Safe Iterators:
     * 1. Don't throw exception if collection is modified during iteration
     * 2. Work on a copy of the collection
     * 3. Examples: Iterator from ConcurrentHashMap, CopyOnWriteArrayList
     */
    private static void demonstrateFailFastVsFailSafe() {
        System.out.println("\n5. Fail-Fast vs Fail-Safe Iterators:\n");
        
        // Fail-Fast Iterator Example
        System.out.println("Fail-Fast Iterator Example:");
        List<String> failFastList = new ArrayList<>();
        failFastList.add("One");
        failFastList.add("Two");
        failFastList.add("Three");
        
        try {
            // This will throw ConcurrentModificationException
            for (String item : failFastList) {
                System.out.println("Item: " + item);
                if (item.equals("Two")) {
                    System.out.println("Trying to remove 'Two' during iteration...");
                    failFastList.remove(item);  // Modifying during iteration
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException caught: " + e.getMessage());
            System.out.println("This demonstrates fail-fast behavior");
        }
        
        // Proper way to remove during iteration
        System.out.println("\nProper way to remove during iteration:");
        Iterator<String> iterator = failFastList.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println("Item: " + item);
            if (item.equals("Three")) {
                System.out.println("Removing 'Three' using iterator.remove()...");
                iterator.remove();  // Safe way to remove during iteration
            }
        }
        System.out.println("List after safe removal: " + failFastList);
        
        // Fail-Safe Iterator Example
        System.out.println("\nFail-Safe Iterator Example:");
        List<String> failSafeList = new java.util.concurrent.CopyOnWriteArrayList<>();
        failSafeList.add("One");
        failSafeList.add("Two");
        failSafeList.add("Three");
        
        // This will not throw exception but may not reflect the latest state
        for (String item : failSafeList) {
            System.out.println("Item: " + item);
            if (item.equals("Two")) {
                System.out.println("Removing 'Two' during iteration...");
                failSafeList.remove(item);  // Safe with CopyOnWriteArrayList
            }
        }
        
        System.out.println("List after removal: " + failSafeList);
        System.out.println("No exception was thrown - this demonstrates fail-safe behavior");
        
        // Summary
        System.out.println("\nSummary:");
        System.out.println("Fail-Fast: Throws exception if collection is modified during iteration");
        System.out.println("Fail-Safe: Works on a copy, doesn't throw exception but may not see latest changes");
        System.out.println("Most Java collections are fail-fast for safety");
        System.out.println("Concurrent collections are typically fail-safe for better concurrency");
    }
}