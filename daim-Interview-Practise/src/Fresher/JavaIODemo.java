package Fresher;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * This class demonstrates various file and I/O operations in Java
 * 1. Reading and writing text files using FileReader/FileWriter
 * 2. Reading and writing binary files using FileInputStream/FileOutputStream
 * 3. Buffered I/O for improved performance
 * 4. Java NIO.2 (New I/O) API for modern file operations
 */
public class JavaIODemo {

    // Sample file paths
    private static final String TEXT_FILE_PATH = "sample_text.txt";
    private static final String BINARY_FILE_PATH = "sample_binary.dat";
    private static final String DIRECTORY_PATH = "sample_directory";

    public static void main(String[] args) {
        System.out.println("=== Java I/O Operations Demo ===\n");

        try {
            // 1. File handling basics
            demonstrateFileBasics();

            // 2. Reading and writing text files
            demonstrateTextFileOperations();

            // 3. Reading and writing binary files
            demonstrateBinaryFileOperations();

            // 4. Buffered I/O operations
            demonstrateBufferedIO();

            // 5. Java NIO.2 operations
            demonstrateNIO2Operations();

            // Clean up created files
            cleanupFiles();

        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 1. Demonstrate basic File operations
    private static void demonstrateFileBasics() throws IOException {
        System.out.println("1. File Basics:\n");

        // Create a File object
        File textFile = new File(TEXT_FILE_PATH);

        // Create a new file
        boolean fileCreated = textFile.createNewFile();
        System.out.println("File created: " + fileCreated);

        // Get file information
        System.out.println("File name: " + textFile.getName());
        System.out.println("Absolute path: " + textFile.getAbsolutePath());
        System.out.println("File size: " + textFile.length() + " bytes");
        System.out.println("Is directory: " + textFile.isDirectory());
        System.out.println("Is file: " + textFile.isFile());
        System.out.println("Exists: " + textFile.exists());
        System.out.println("Can read: " + textFile.canRead());
        System.out.println("Can write: " + textFile.canWrite());

        // Create a directory
        File directory = new File(DIRECTORY_PATH);
        boolean dirCreated = directory.mkdir();
        System.out.println("\nDirectory created: " + dirCreated);

        // List files in current directory
        File currentDir = new File(".");
        System.out.println("\nFiles in current directory:");
        File[] files = currentDir.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName() + (file.isDirectory() ? " (dir)" : ""));
            }
        }

        System.out.println();
    }

    // 2. Demonstrate text file reading and writing
    private static void demonstrateTextFileOperations() throws IOException {
        System.out.println("2. Text File Operations:\n");

        // Writing to a text file using FileWriter
        System.out.println("Writing to text file...");
        try (FileWriter writer = new FileWriter(TEXT_FILE_PATH)) {
            writer.write("Hello, this is a sample text file.\n");
            writer.write("This is the second line.\n");
            writer.write("This is the third line with special chars: äöü€\n");
        }

        // Reading from a text file using FileReader
        System.out.println("\nReading from text file:");
        try (FileReader reader = new FileReader(TEXT_FILE_PATH);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }

        // Using try-with-resources ensures streams are properly closed
        System.out.println("\nAppending to text file...");
        try (FileWriter writer = new FileWriter(TEXT_FILE_PATH, true)) { // true for append mode
            writer.write("This line was appended.\n");
            writer.write("This is another appended line.\n");
        }

        // Reading the updated file
        System.out.println("\nReading updated text file:");
        try (Scanner scanner = new Scanner(new File(TEXT_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }

        System.out.println();
    }

    // 3. Demonstrate binary file reading and writing
    private static void demonstrateBinaryFileOperations() throws IOException {
        System.out.println("3. Binary File Operations:\n");

        // Writing binary data
        System.out.println("Writing binary data...");
        try (FileOutputStream fos = new FileOutputStream(BINARY_FILE_PATH);
             DataOutputStream dos = new DataOutputStream(fos)) {

            // Write different data types
            dos.writeInt(42);
            dos.writeDouble(3.14159);
            dos.writeBoolean(true);
            dos.writeUTF("Binary data example");
        }

        // Reading binary data
        System.out.println("\nReading binary data:");
        try (FileInputStream fis = new FileInputStream(BINARY_FILE_PATH);
             DataInputStream dis = new DataInputStream(fis)) {

            // Read different data types in the same order they were written
            int intValue = dis.readInt();
            double doubleValue = dis.readDouble();
            boolean boolValue = dis.readBoolean();
            String stringValue = dis.readUTF();

            System.out.println("Int value: " + intValue);
            System.out.println("Double value: " + doubleValue);
            System.out.println("Boolean value: " + boolValue);
            System.out.println("String value: " + stringValue);
        }

        System.out.println();
    }

    // 4. Demonstrate buffered I/O for improved performance
    private static void demonstrateBufferedIO() throws IOException {
        System.out.println("4. Buffered I/O Operations:\n");

        // Writing with BufferedWriter
        System.out.println("Writing with BufferedWriter...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEXT_FILE_PATH))) {
            writer.write("This is written using BufferedWriter.\n");
            writer.write("Buffered I/O is more efficient for larger operations.\n");
            writer.write("It reduces the number of actual I/O operations.\n");
        }

        // Reading with BufferedReader
        System.out.println("\nReading with BufferedReader:");
        try (BufferedReader reader = new BufferedReader(new FileReader(TEXT_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        // Performance comparison - create a large file
        String largeContent = "This is a sample line that will be repeated many times.\n";
        StringBuilder contentBuilder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            contentBuilder.append(largeContent);
        }
        String content = contentBuilder.toString();

        // Measure time for unbuffered write
        System.out.println("\nPerformance comparison for writing 10,000 lines:");
        long startTime = System.nanoTime();
        try (FileWriter writer = new FileWriter("unbuffered.txt")) {
            writer.write(content);
        }
        long unbufferedTime = System.nanoTime() - startTime;

        // Measure time for buffered write
        startTime = System.nanoTime();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("buffered.txt"))) {
            writer.write(content);
        }
        long bufferedTime = System.nanoTime() - startTime;

        System.out.println("Unbuffered write time: " + unbufferedTime + " ns");
        System.out.println("Buffered write time: " + bufferedTime + " ns");
        System.out.println("Performance ratio: " + (double)unbufferedTime/bufferedTime + "x");

        // Clean up temp files
        new File("unbuffered.txt").delete();
        new File("buffered.txt").delete();

        System.out.println();
    }

    // 5. Demonstrate Java NIO.2 (New I/O) introduced in Java 7
    private static void demonstrateNIO2Operations() throws IOException {
        System.out.println("5. Java NIO.2 Operations:\n");

        // Create a Path object
        Path textFilePath = Paths.get(TEXT_FILE_PATH);
        Path dirPath = Paths.get(DIRECTORY_PATH);

        // File information using Path and Files
        System.out.println("Path information:");
        System.out.println("File name: " + textFilePath.getFileName());
        System.out.println("Absolute path: " + textFilePath.toAbsolutePath());
        System.out.println("Parent directory: " + textFilePath.getParent());

        // Writing using Files class
        System.out.println("\nWriting using Files.write()...");
        List<String> lines = Arrays.asList(
            "This is written using NIO.2 Files class.",
            "Java NIO.2 provides modern file handling capabilities.",
            "It was introduced in Java 7."
        );
        Files.write(textFilePath, lines, StandardCharsets.UTF_8);

        // Reading using Files class
        System.out.println("\nReading using Files.readAllLines():");
        List<String> readLines = Files.readAllLines(textFilePath, StandardCharsets.UTF_8);
        for (String line : readLines) {
            System.out.println(line);
        }

        // File operations
        System.out.println("\nFile operations:");
        System.out.println("Exists: " + Files.exists(textFilePath));
        System.out.println("Size: " + Files.size(textFilePath) + " bytes");
        System.out.println("Is regular file: " + Files.isRegularFile(textFilePath));
        System.out.println("Is directory: " + Files.isDirectory(dirPath));

        // Create a temporary file
        Path tempFile = Files.createTempFile("prefix", ".suffix");
        System.out.println("\nCreated temporary file: " + tempFile);

        // Copy file
        Path copyPath = Paths.get("copy_of_" + TEXT_FILE_PATH);
        Files.copy(textFilePath, copyPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Copied file to: " + copyPath);

        // Walk directory tree
        System.out.println("\nWalking directory tree from current directory:");
        Path currentDir = Paths.get(".");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDir)) {
            for (Path path : stream) {
                System.out.println(path.getFileName() + 
                              (Files.isDirectory(path) ? " (dir)" : ""));
            }
        }

        // Clean up
        Files.deleteIfExists(copyPath);
        Files.deleteIfExists(tempFile);

        System.out.println();
    }

    // Clean up all created files and directories
    private static void cleanupFiles() throws IOException {
        System.out.println("Cleaning up created files and directories...");

        // Delete files
        Files.deleteIfExists(Paths.get(TEXT_FILE_PATH));
        Files.deleteIfExists(Paths.get(BINARY_FILE_PATH));

        // Delete directory
        Files.deleteIfExists(Paths.get(DIRECTORY_PATH));

        System.out.println("Cleanup completed.");
    }
}
