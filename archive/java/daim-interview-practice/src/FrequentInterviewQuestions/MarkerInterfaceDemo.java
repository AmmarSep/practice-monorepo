//package FrequentInterviewQuestions;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * This class demonstrates Marker Interfaces in Java
// *
// * A marker interface is an interface that has no methods or constants.
// * It provides runtime type information about objects and simply "marks" a class
// * that implements it to have a specific behavior or property.
// */
//public class MarkerInterfaceDemo {
//
//    public static void main(String[] args) {
//        System.out.println("===== Marker Interface Demonstration =====");
//
//        // 1. Standard Marker Interfaces in Java
//        demonstrateStandardMarkerInterfaces();
//
//        // 2. Custom Marker Interface Usage
//        demonstrateCustomMarkerInterface();
//
//        // 3. Type-Safe Alternatives to Marker Interfaces
//        demonstrateAlternativesToMarkerInterfaces();
//    }
//
//    /**
//     * Demonstrates standard marker interfaces provided by Java
//     */
//    private static void demonstrateStandardMarkerInterfaces() {
//        System.out.println("\n1. Standard Marker Interfaces in Java:\n");
//
//        // Serializable Interface
//        System.out.println("a) Serializable Interface:");
//
//        Employee emp = new Employee(101, "John Doe", "Engineering");
//        System.out.println("Created employee: " + emp);
//
//        // Check if object implements Serializable
//        if (emp instanceof Serializable) {
//            System.out.println("Employee object is serializable");
//            System.out.println("It can be converted to a byte stream and saved to a file or");
//            System.out.println("transmitted over a network.");
//        } else {
//            System.out.println("Employee object is not serializable");
//        }
//
//        // Cloneable Interface
//        System.out.println("\nb) Cloneable Interface:");
//
//        try {
//            Student student = new Student(1001, "Alice Smith", "Computer Science");
//            System.out.println("Original student: " + student);
//
//            // Clone the student object
//            Student clonedStudent = (Student) student.clone();
//            System.out.println("Cloned student: " + clonedStudent);
//
//            // Verify that it's a different object with the same values
//            System.out.println("Are they the same object? " + (student == clonedStudent));  // false
//            System.out.println("Do they have the same values? " + student.equals(clonedStudent));  // true
//
//            // Try to clone a non-cloneable object
//            NonCloneableStudent nonCloneableStudent = new NonCloneableStudent(1002, "Bob Johnson", "Physics");
//            System.out.println("\nTrying to clone a non-cloneable object:");
//            Object clonedNonCloneable = nonCloneableStudent.clone();
//
//        } catch (CloneNotSupportedException e) {
//            System.out.println("Clone failed: " + e.getMessage());
//            System.out.println("The object does not implement the Cloneable interface.");
//        }
//    }
//
//    /**
//     * Demonstrates creating and using a custom marker interface
//     */
//    private static void demonstrateCustomMarkerInterface() {
//        System.out.println("\n2. Custom Marker Interface Usage:\n");
//
//        // Creating objects that may or may not be deletable
//        RegularDocument doc1 = new RegularDocument("Project Plan", "John");
//        RegularDocument doc2 = new RegularDocument("Budget Report", "Alice");
//        ProtectedDocument doc3 = new ProtectedDocument("Confidential Report", "Bob");
//        ProtectedDocument doc4 = new ProtectedDocument("Legal Contract", "Carol");
//
//        // Add them to a list
//        List<Document> documents = new ArrayList<>();
//        documents.add(doc1);
//        documents.add(doc2);
//        documents.add(doc3);
//        documents.add(doc4);
//
//        // Print all documents
//        System.out.println("All documents:");
//        for (Document doc : documents) {
//            System.out.println(doc);
//        }
//
//        // Try to delete each document
//        System.out.println("\nAttempting to delete documents:");
//        for (Document doc : documents) {
//            tryToDeleteDocument(doc);
//        }
//
//        // Another example with Parseable interface
//        System.out.println("\nParseable interface example:");
//        XMLFile xmlFile = new XMLFile("config.xml");
//        JSONFile jsonFile = new JSONFile("data.json");
//        BinaryFile binaryFile = new BinaryFile("image.jpg");
//
//        parseFile(xmlFile);   // Can be parsed
//        parseFile(jsonFile);  // Can be parsed
//        parseFile(binaryFile); // Cannot be parsed
//    }
//
//    /**
//     * Demonstrates alternatives to marker interfaces
//     */
//    private static void demonstrateAlternativesToMarkerInterfaces() {
//        System.out.println("\n3. Type-Safe Alternatives to Marker Interfaces:\n");
//
//        // 1. Using annotations instead of marker interfaces
//        System.out.println("a) Using annotations:");
//
//        AnnotatedClass obj = new AnnotatedClass();
//
//        // Check if the class has the @Deletable annotation
//        if (obj.getClass().isAnnotationPresent(Deletable.class)) {
//            System.out.println("AnnotatedClass has @Deletable annotation");
//            System.out.println("Deleting object...");
//        } else {
//            System.out.println("AnnotatedClass does not have @Deletable annotation");
//        }
//
//        // 2. Using abstract methods instead
//        System.out.println("\nb) Using abstract methods:");
//
//        ValidatableDocument validDoc = new ValidatableDocument("Valid Document", "John");
//        System.out.println(validDoc);
//
//        boolean isValid = validDoc.isValid();
//        System.out.println("Is document valid? " + isValid);
//
//        if (isValid) {
//            System.out.println("Processing valid document...");
//        } else {
//            System.out.println("Document validation failed.");
//        }
//
//        // 3. Using enum for type safety
//        System.out.println("\nc) Using enum for type safety:");
//
//        EnumTypedDocument confidentialDoc = new EnumTypedDocument(
//                "Classified Information", "Admin", DocumentType.CONFIDENTIAL);
//
//        EnumTypedDocument publicDoc = new EnumTypedDocument(
//                "Public Announcement", "PR Team", DocumentType.PUBLIC);
//
//        System.out.println(confidentialDoc);
//        System.out.println(publicDoc);
//
//        processDocumentByType(confidentialDoc);
//        processDocumentByType(publicDoc);
//    }
//
//    /**
//     * Helper method to try deleting a document
//     */
//    private static void tryToDeleteDocument(Document doc) {
//        System.out.print("Attempting to delete \"" + doc.getTitle() + "\": ");
//
//        if (doc instanceof Deletable) {
//            System.out.println("Success! Document is deletable.");
//            // In a real application, we would actually delete the document here
//        } else {
//            System.out.println("Failed! Document is protected and cannot be deleted.");
//        }
//    }
//
//    /**
//     * Helper method to try parsing a file
//     */
//    private static void parseFile(File file) {
//        System.out.print("Attempting to parse " + file.getName() + ": ");
//
//        if (file instanceof Parseable) {
//            System.out.println("Success! File is parseable.");
//            // In a real application, we would actually parse the file here
//        } else {
//            System.out.println("Failed! File cannot be parsed.");
//        }
//    }
//
//    /**
//     * Helper method to process a document based on its type
//     */
//    private static void processDocumentByType(EnumTypedDocument doc) {
//        System.out.print("Processing document \"" + doc.getTitle() + "\": ");
//
//        switch (doc.getType()) {
//            case PUBLIC:
//                System.out.println("Publishing to website");
//                break;
//            case INTERNAL:
//                System.out.println("Sharing on intranet");
//                break;
//            case CONFIDENTIAL:
//                System.out.println("Encrypting and restricting access");
//                break;
//            default:
//                System.out.println("Unknown document type");
//        }
//    }
//}
//
//// Classes for demonstrating Serializable marker interface
//class Employee implements Serializable {
//    // Serializable is a marker interface with no methods
//    private static final long serialVersionUID = 1L;
//
//    private int id;
//    private String name;
//    private String department;
//
//    public Employee(int id, String name, String department) {
//        this.id = id;
//        this.name = name;
//        this.department = department;
//    }
//
//    @Override
//    public String toString() {
//        return "Employee [id=" + id + ", name=" + name + ", department=" + department + "]";
//    }
//}
//
//// Classes for demonstrating Cloneable marker interface
//class Student implements Cloneable {
//    // Cloneable is a marker interface with no methods
//    private int id;
//    private String name;
//    private String course;
//
//    public Student(int id, String name, String course) {
//        this.id = id;
//        this.name = name;
//        this.course = course;
//    }
//
//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        // Since we implement Cloneable, this will succeed
//        return super.clone();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//
//        Student student = (Student) obj;
//        return id == student.id &&
//               name.equals(student.name) &&
//               course.equals(student.course);
//    }
//
//    @Override
//    public String toString() {
//        return "Student [id=" + id + ", name=" + name + ", course=" + course + "]";
//    }
//}
//
//class NonCloneableStudent {
//    // This class does NOT implement Cloneable
//    private int id;
//    private String name;
//    private String course;
//
//    public NonCloneableStudent(int id, String name, String course) {
//        this.id = id;
//        this.name = name;
//        this.course = course;
//    }
//
//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        // This will throw CloneNotSupportedException since the class doesn't implement Cloneable
//        return super.clone();
//    }
//
//    @Override
//    public String toString() {
//        return "NonCloneableStudent [id=" + id + ", name=" + name + ", course=" + course + "]";
//    }
//}
//
//// Custom marker interface and related classes
//interface Deletable {
//    // This is a marker interface with no methods
//    // It marks classes that can be deleted
//}
//
//abstract class Document {
//    private String title;
//    private String author;
//
//    public Document(String title, String author) {
//        this.title = title;
//        this.author = author;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    @Override
//    public String toString() {
//        return getClass().getSimpleName() + " [title=" + title + ", author=" + author + "]";
//    }
//}
//
//class RegularDocument extends Document implements Deletable {
//    // Regular documents can be deleted
//    public RegularDocument(String title, String author) {
//        super(title, author);
//    }
//}
//
//class ProtectedDocument extends Document {
//    // Protected documents cannot be deleted (no Deletable interface)
//    public ProtectedDocument(String title, String author) {
//        super(title, author);
//    }
//}
//
//// Another custom marker interface example
//interface Parseable {
//    // This is a marker interface with no methods
//    // It marks classes that can be parsed
//}
//
//abstract class File {
//    private String name;
//
//    public File(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public String toString() {
//        return getClass().getSimpleName() + " [name=" + name + "]";
//    }
//}
//
//class XMLFile extends File implements Parseable {
//    public XMLFile(String name) {
//        super(name);
//    }
//}
//
//class JSONFile extends File implements Parseable {
//    public JSONFile(String name) {
//        super(name);
//    }
//}
//
//class BinaryFile extends File {
//    // Binary files cannot be parsed (no Parseable interface)
//    public BinaryFile(String name) {
//        super(name);
//    }
//}
//
//// Alternatives to marker interfaces
//
//// 1. Using annotations
//import java.lang.annotation.*;
//
//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE)
//@interface Deletable {
//    // Annotation as an alternative to marker interface
//}
//
//@Deletable
//class AnnotatedClass {
//    // This class is marked as deletable using an annotation
//}
//
//// 2. Using abstract methods
//interface Validatable {
//    boolean isValid();  // Non-empty interface with an actual method
//}
//
//class ValidatableDocument extends Document implements Validatable {
//    public ValidatableDocument(String title, String author) {
//        super(title, author);
//    }
//
//    @Override
//    public boolean isValid() {
//        // Actual implementation instead of just marking
//        return getTitle() != null && !getTitle().isEmpty() &&
//               getAuthor() != null && !getAuthor().isEmpty();
//    }
//}
//
//// 3. Using enum for type safety
//enum DocumentType {
//    PUBLIC,
//    INTERNAL,
//    CONFIDENTIAL
//}
//
//class EnumTypedDocument extends Document {
//    private DocumentType type;
//
//    public EnumTypedDocument(String title, String author, DocumentType type) {
//        super(title, author);
//        this.type = type;
//    }
//
//    public DocumentType getType() {
//        return type;
//    }
//
//    @Override
//    public String toString() {
//        return super.toString() + " [type=" + type + "]";
//    }
//}
