package JavaSimplePrograms;

/**
 * This class demonstrates marker interfaces in Java
 * Marker interfaces are interfaces with no methods or fields, used to indicate something to the compiler/JVM
 */
public class MarkerInterfaceDemo {

    public static void main(String[] args) {
        System.out.println("===== Marker Interface Demonstration =====\n");

        // Built-in marker interfaces
        System.out.println("1. Built-in Marker Interfaces in Java\n");
        explainBuiltInMarkerInterfaces();

        // Custom marker interface example
        System.out.println("\n2. Custom Marker Interface Example\n");

        // Create objects of different classes
        RegularFile textFile = new RegularFile("document.txt", 1024);
        EncryptedFile secretFile = new EncryptedFile("secret.enc", 2048);

        // Process the files
        System.out.println("Processing files:");
        processFile(textFile);    // Regular file processing
        processFile(secretFile);  // Special handling for encrypted file

        // Auditable example
        System.out.println("\n3. Auditable Marker Interface Example\n");

        BankAccount regularAccount = new BankAccount("123456", 1000.0);
        PremiumBankAccount premiumAccount = new PremiumBankAccount("789012", 5000.0);

        // Perform operations on accounts
        regularAccount.deposit(500.0);
        premiumAccount.deposit(1000.0);

        // Generate audit logs
        System.out.println("\nGenerating audit logs:");
        generateAuditLog(regularAccount);  // Not auditable
        generateAuditLog(premiumAccount);  // Auditable

        // Serializable example
        System.out.println("\n4. Serializable Marker Interface Example\n");

        // Create objects
        SerializableUser user1 = new SerializableUser("John", "john@example.com");
        NonSerializableUser user2 = new NonSerializableUser("Alice", "alice@example.com");

        // Try to serialize objects
        serializeObject(user1);  // Should succeed
        serializeObject(user2);  // Should fail

        // Practical examples of using marker interfaces
        System.out.println("\n5. Permission-based Example\n");

        // Create objects of different user types
        RegularUser regularUser = new RegularUser("Bob");
        AdminUser adminUser = new AdminUser("Admin");

        // Try to perform admin operation with different users
        performAdminOperation(regularUser);  // Should be denied
        performAdminOperation(adminUser);    // Should be allowed
    }

    /**
     * Explains built-in marker interfaces in Java
     */
    private static void explainBuiltInMarkerInterfaces() {
        System.out.println("Java has several built-in marker interfaces:");

        System.out.println("\na) java.io.Serializable");
        System.out.println("   - Marks classes whose objects can be converted to byte stream");
        System.out.println("   - Used with ObjectOutputStream and ObjectInputStream");
        System.out.println("   - Example: FileOutputStream, ArrayList, HashMap");

        System.out.println("\nb) java.lang.Cloneable");
        System.out.println("   - Marks classes whose objects can be cloned using Object.clone() method");
        System.out.println("   - Without implementing Cloneable, Object.clone() throws CloneNotSupportedException");
        System.out.println("   - Example: ArrayList, HashMap, Date");

        System.out.println("\nc) java.rmi.Remote");
        System.out.println("   - Marks classes whose objects can be accessed from another JVM");
        System.out.println("   - Used in Remote Method Invocation (RMI)");
        System.out.println("   - All methods must declare throwing RemoteException");

        System.out.println("\nd) java.util.RandomAccess");
        System.out.println("   - Marks List implementations that support fast random access");
        System.out.println("   - Used by algorithms to determine the best approach for list traversal");
        System.out.println("   - Example: ArrayList implements RandomAccess, LinkedList does not");
    }

    /**
     * Custom marker interface for encrypted files
     */
    interface Encrypted {
        // No methods or fields - this is a marker interface
    }

    /**
     * Base class for file operations
     */
    static class File {
        private String name;
        private long size;

        public File(String name, long size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public long getSize() {
            return size;
        }
    }

    /**
     * Regular file class (not encrypted)
     */
    static class RegularFile extends File {
        public RegularFile(String name, long size) {
            super(name, size);
        }
    }

    /**
     * Encrypted file class implementing the Encrypted marker interface
     */
    static class EncryptedFile extends File implements Encrypted {
        public EncryptedFile(String name, long size) {
            super(name, size);
        }
    }

    /**
     * Method that processes files differently based on whether they implement the Encrypted marker interface
     */
    private static void processFile(File file) {
        System.out.println("\nProcessing file: " + file.getName() + " (Size: " + file.getSize() + " bytes)");

        if (file instanceof Encrypted) {
            System.out.println("Detected encrypted file. Special handling required.");
            System.out.println("Decrypting file before processing...");
            // Special handling for encrypted files
            System.out.println("Processing decrypted content...");
        } else {
            System.out.println("Regular file detected. Standard processing.");
            // Standard file processing
            System.out.println("Processing content directly...");
        }

        System.out.println("File processing complete.");
    }

    /**
     * Marker interface for auditable objects
     */
    interface Auditable {
        // No methods or fields - this is a marker interface
    }

    /**
     * Base account class
     */
    static class BankAccount {
        private String accountNumber;
        private double balance;

        public BankAccount(String accountNumber, double balance) {
            this.accountNumber = accountNumber;
            this.balance = balance;
        }

        public void deposit(double amount) {
            balance += amount;
            System.out.println("Deposited $" + amount + " to account " + accountNumber + 
                              ". New balance: $" + balance);
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public double getBalance() {
            return balance;
        }
    }

    /**
     * Premium account class implementing the Auditable marker interface
     */
    static class PremiumBankAccount extends BankAccount implements Auditable {
        public PremiumBankAccount(String accountNumber, double balance) {
            super(accountNumber, balance);
        }
    }

    /**
     * Method that generates audit logs only for objects that implement the Auditable marker interface
     */
    private static void generateAuditLog(BankAccount account) {
        if (account instanceof Auditable) {
            System.out.println("Generating audit log for account: " + account.getAccountNumber());
            System.out.println("Account type: Premium Account (Auditable)");
            System.out.println("Current balance: $" + account.getBalance());
            System.out.println("Timestamp: " + java.time.LocalDateTime.now());
        } else {
            System.out.println("Account " + account.getAccountNumber() + " is not auditable. Skipping audit log.");
        }
    }

    /**
     * Classes for serialization example
     */
    static class SerializableUser implements java.io.Serializable {
        private String name;
        private String email;

        public SerializableUser(String name, String email) {
            this.name = name;
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{name='" + name + "', email='" + email + "'}";
        }
    }

    static class NonSerializableUser {
        private String name;
        private String email;

        public NonSerializableUser(String name, String email) {
            this.name = name;
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{name='" + name + "', email='" + email + "'}";
        }
    }

    /**
     * Method that attempts to serialize an object
     */
    private static void serializeObject(Object object) {
        System.out.println("Attempting to serialize: " + object);

        if (object instanceof java.io.Serializable) {
            System.out.println("Object is Serializable. Serialization would succeed.");
            // In a real application, you would do:
            // try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("object.ser"))) {
            //     out.writeObject(object);
            // }
        } else {
            System.out.println("Object is NOT Serializable. Serialization would fail with NotSerializableException.");
        }
    }

    /**
     * Marker interface for admin access
     */
    interface AdminAccess {
        // No methods or fields - this is a marker interface
    }

    /**
     * Base user class
     */
    static class User {
        private String username;

        public User(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }

    /**
     * Regular user class (no admin access)
     */
    static class RegularUser extends User {
        public RegularUser(String username) {
            super(username);
        }
    }

    /**
     * Admin user class implementing the AdminAccess marker interface
     */
    static class AdminUser extends User implements AdminAccess {
        public AdminUser(String username) {
            super(username);
        }
    }

    /**
     * Method that checks for admin access using the marker interface
     */
    private static void performAdminOperation(User user) {
        System.out.println("User " + user.getUsername() + " is attempting to perform admin operation.");

        if (user instanceof AdminAccess) {
            System.out.println("Access granted! User has admin privileges.");
            System.out.println("Performing sensitive admin operation...");
        } else {
            System.out.println("Access denied! User does not have admin privileges.");
        }
    }
}
