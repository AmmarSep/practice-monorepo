package Intermediate.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class demonstrates how to create and use custom exceptions in Java
 * with best practices for exception hierarchy and handling.
 */
public class CustomExceptionDemo {

    public static void main(String[] args) {
        System.out.println("=== Custom Exception Demonstration ===\n");

        BankAccountManager accountManager = new BankAccountManager();
        Scanner scanner = new Scanner(System.in);

        try {
            // Demo: Create new account
            System.out.println("Creating a new account...");
            BankAccount account = accountManager.createAccount("John Doe", 1000.0);
            System.out.println("Account created successfully: " + account);

            // Demo: Deposit money
            System.out.println("\nDepositing $500 to the account...");
            accountManager.deposit(account.getAccountId(), 500.0);
            System.out.println("New balance: $" + account.getBalance());

            // Demo: Insufficient funds exception
            System.out.println("\nAttempting to withdraw $2000 (more than balance)...");
            accountManager.withdraw(account.getAccountId(), 2000.0);
        } catch (InsufficientFundsException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
            System.out.println("Current balance: $" + e.getAvailableBalance());
            System.out.println("Attempted withdrawal: $" + e.getRequestedAmount());
            System.out.println("Additional details: " + e.getAdditionalInfo());
        } catch (BankAccountException e) {
            System.out.println("Banking error: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Caused by: " + e.getCause().getMessage());
            }
        }

        try {
            // Demo: Account not found exception
            System.out.println("\nAttempting to access non-existent account...");
            accountManager.withdraw("INVALID_ID", 100.0);
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Requested account ID: " + e.getAccountId());
        } catch (BankAccountException e) {
            System.out.println("Banking error: " + e.getMessage());
        }

        try {
            // Demo: Invalid input exception
            System.out.println("\nAttempting to deposit negative amount...");
            BankAccount account = accountManager.getAccounts().get(0);
            accountManager.deposit(account.getAccountId(), -100.0);
        } catch (InvalidInputException e) {
            System.out.println("Input error: " + e.getMessage());
            System.out.println("Validation errors: " + e.getValidationErrors());
        } catch (BankAccountException e) {
            System.out.println("Banking error: " + e.getMessage());
        }

        try {
            // Demo: Transaction limit exception
            System.out.println("\nAttempting multiple transactions to exceed limit...");
            BankAccount account = accountManager.getAccounts().get(0);
            for (int i = 0; i < 6; i++) {
                System.out.println("Transaction " + (i + 1) + "...");
                accountManager.deposit(account.getAccountId(), 10.0);
            }
        } catch (TransactionLimitExceededException e) {
            System.out.println("Transaction limit error: " + e.getMessage());
            System.out.println("Max transactions allowed: " + e.getMaxTransactionsAllowed());
            System.out.println("Current transaction count: " + e.getCurrentTransactionCount());
            System.out.println("Try again after: " + e.getNextAvailableTime());
        } catch (BankAccountException e) {
            System.out.println("Banking error: " + e.getMessage());
        }

        // Demo: Exception with suppressed exceptions
        try {
            System.out.println("\nDemonstrating suppressed exceptions...");
            accountManager.closeAccount("ACC123");
        } catch (BankAccountException e) {
            System.out.println("Error during account closure: " + e.getMessage());

            if (e.getSuppressed().length > 0) {
                System.out.println("\nSuppressed exceptions:");
                for (Throwable suppressed : e.getSuppressed()) {
                    System.out.println("  - " + suppressed.getMessage());
                }
            }
        }

        scanner.close();
    }
}

/**
 * Base exception class for all banking-related exceptions
 * Following best practice of having a hierarchy of custom exceptions
 */
class BankAccountException extends Exception {
    private static final long serialVersionUID = 1L;

    public BankAccountException(String message) {
        super(message);
    }

    public BankAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Exception thrown when trying to withdraw more money than available
 * Includes contextual information about the account and transaction
 */
class InsufficientFundsException extends BankAccountException {
    private static final long serialVersionUID = 1L;

    private final double availableBalance;
    private final double requestedAmount;
    private final String additionalInfo;

    public InsufficientFundsException(double availableBalance, double requestedAmount) {
        super("Insufficient funds: Requested $" + requestedAmount + 
              " but only $" + availableBalance + " available");
        this.availableBalance = availableBalance;
        this.requestedAmount = requestedAmount;
        this.additionalInfo = "You need an additional $" + 
                             (requestedAmount - availableBalance) + " to complete this transaction";
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}

/**
 * Exception thrown when an account is not found
 */
class AccountNotFoundException extends BankAccountException {
    private static final long serialVersionUID = 1L;

    private final String accountId;

    public AccountNotFoundException(String accountId) {
        super("Account not found with ID: " + accountId);
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }
}

/**
 * Exception thrown when input validation fails
 * Contains a list of validation errors
 */
class InvalidInputException extends BankAccountException {
    private static final long serialVersionUID = 1L;

    private final List<String> validationErrors;

    public InvalidInputException(String message) {
        super(message);
        this.validationErrors = new ArrayList<>();
        this.validationErrors.add(message);
    }

    public InvalidInputException(List<String> validationErrors) {
        super("Invalid input: " + String.join(", ", validationErrors));
        this.validationErrors = validationErrors;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }
}

/**
 * Exception thrown when transaction limit is exceeded
 */
class TransactionLimitExceededException extends BankAccountException {
    private static final long serialVersionUID = 1L;

    private final int maxTransactionsAllowed;
    private final int currentTransactionCount;
    private final String nextAvailableTime;

    public TransactionLimitExceededException(int maxTransactionsAllowed, int currentTransactionCount) {
        super("Transaction limit exceeded: Maximum " + maxTransactionsAllowed + 
              " transactions allowed, but attempted " + currentTransactionCount);
        this.maxTransactionsAllowed = maxTransactionsAllowed;
        this.currentTransactionCount = currentTransactionCount;
        this.nextAvailableTime = "Tomorrow"; // In a real application, this would be calculated
    }

    public int getMaxTransactionsAllowed() {
        return maxTransactionsAllowed;
    }

    public int getCurrentTransactionCount() {
        return currentTransactionCount;
    }

    public String getNextAvailableTime() {
        return nextAvailableTime;
    }
}

/**
 * A simple bank account class
 */
class BankAccount {
    private String accountId;
    private String accountHolder;
    private double balance;
    private int transactionCount;

    public BankAccount(String accountId, String accountHolder, double initialBalance) {
        this.accountId = accountId;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionCount = 0;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
        this.transactionCount++;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
        this.transactionCount++;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    @Override
    public String toString() {
        return "BankAccount [accountId=" + accountId + ", accountHolder=" + accountHolder + ", balance=$" + balance + "]";
    }
}

/**
 * A class to manage bank accounts and demonstrate different exception scenarios
 */
class BankAccountManager {
    private static final int MAX_TRANSACTIONS_PER_DAY = 5;
    private List<BankAccount> accounts;
    private int accountCounter;

    public BankAccountManager() {
        this.accounts = new ArrayList<>();
        this.accountCounter = 1;
    }

    /**
     * Create a new account
     */
    public BankAccount createAccount(String accountHolder, double initialBalance) throws InvalidInputException {
        // Validate input
        List<String> errors = new ArrayList<>();

        if (accountHolder == null || accountHolder.trim().isEmpty()) {
            errors.add("Account holder name cannot be empty");
        }

        if (initialBalance < 0) {
            errors.add("Initial balance cannot be negative");
        }

        if (!errors.isEmpty()) {
            throw new InvalidInputException(errors);
        }

        // Create new account
        String accountId = "ACC" + accountCounter++;
        BankAccount account = new BankAccount(accountId, accountHolder, initialBalance);
        accounts.add(account);

        return account;
    }

    /**
     * Deposit money into an account
     */
    public void deposit(String accountId, double amount) throws BankAccountException {
        // Validate amount
        if (amount <= 0) {
            throw new InvalidInputException("Deposit amount must be positive");
        }

        // Find account
        BankAccount account = findAccount(accountId);

        // Check transaction limit
        if (account.getTransactionCount() >= MAX_TRANSACTIONS_PER_DAY) {
            throw new TransactionLimitExceededException(MAX_TRANSACTIONS_PER_DAY, account.getTransactionCount() + 1);
        }

        // Perform deposit
        account.deposit(amount);
        System.out.println("Successfully deposited $" + amount + " to account " + accountId);
    }

    /**
     * Withdraw money from an account
     */
    public void withdraw(String accountId, double amount) throws BankAccountException {
        // Validate amount
        if (amount <= 0) {
            throw new InvalidInputException("Withdrawal amount must be positive");
        }

        // Find account
        BankAccount account = findAccount(accountId);

        // Check sufficient funds
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException(account.getBalance(), amount);
        }

        // Check transaction limit
        if (account.getTransactionCount() >= MAX_TRANSACTIONS_PER_DAY) {
            throw new TransactionLimitExceededException(MAX_TRANSACTIONS_PER_DAY, account.getTransactionCount() + 1);
        }

        // Perform withdrawal
        account.withdraw(amount);
        System.out.println("Successfully withdrew $" + amount + " from account " + accountId);
    }

    /**
     * Find account by ID
     */
    private BankAccount findAccount(String accountId) throws AccountNotFoundException {
        for (BankAccount account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        throw new AccountNotFoundException(accountId);
    }

    /**
     * Get all accounts
     */
    public List<BankAccount> getAccounts() {
        return accounts;
    }

    /**
     * Close an account - demonstrates suppressed exceptions
     */
    public void closeAccount(String accountId) throws BankAccountException {
        try {
            // This will throw AccountNotFoundException for the demo
            BankAccount account = findAccount(accountId);

            // Perform account closure operations
            // In a real application, there would be multiple operations here
            System.out.println("Closing account: " + account.getAccountId());

            // Simulate resources that need to be closed
            AutoCloseable resource1 = new DatabaseConnection();
            AutoCloseable resource2 = new NetworkConnection();
            AutoCloseable resource3 = new FileLogger();

            // Try-with-resources with multiple resources
            try (resource1; resource2; resource3) {
                // Simulate some operations
                System.out.println("Performing account closure operations...");

                // Simulate an exception during closure
                throw new IOException("Error during data synchronization");
            }
        } catch (AccountNotFoundException e) {
            // Primary exception - account not found
            BankAccountException accountException = new BankAccountException(
                    "Failed to close account: " + e.getMessage(), e);

            // Add suppressed exceptions for demonstration
            accountException.addSuppressed(new IOException("Failed to update transaction log"));
            accountException.addSuppressed(new IOException("Failed to send notification email"));

            throw accountException;
        } catch (Exception e) {
            throw new BankAccountException("Error during account closure: " + e.getMessage(), e);
        }
    }

    /**
     * Simulated resources for demonstrating suppressed exceptions
     */
    private class DatabaseConnection implements AutoCloseable {
        @Override
        public void close() throws Exception {
            System.out.println("Closing database connection...");
            // Could throw an exception here
        }
    }

    private class NetworkConnection implements AutoCloseable {
        @Override
        public void close() throws Exception {
            System.out.println("Closing network connection...");
            // Could throw an exception here
        }
    }

    private class FileLogger implements AutoCloseable {
        @Override
        public void close() throws Exception {
            System.out.println("Closing file logger...");
            // Could throw an exception here
        }
    }
}
