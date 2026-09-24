package Fresher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;

/**
 * A simple bank account application to demonstrate account operations
 */
public class BankAccountApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankSystem bankSystem = new BankSystem();

        // Create sample accounts
        Account savingsAccount = new SavingsAccount("AC001", "John Doe", 1000.0, 0.05); // 5% interest
        Account checkingAccount = new CheckingAccount("AC002", "Jane Smith", 2000.0, 500.0); // $500 overdraft

        bankSystem.addAccount(savingsAccount);
        bankSystem.addAccount(checkingAccount);

        System.out.println("=== Welcome to Simple Bank Application ===\n");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Create a new account");
            System.out.println("2. View account details");
            System.out.println("3. Deposit money");
            System.out.println("4. Withdraw money");
            System.out.println("5. Transfer money");
            System.out.println("6. View transaction history");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Create account
                    createAccount(scanner, bankSystem);
                    break;
                case 2: // View details
                    viewAccountDetails(scanner, bankSystem);
                    break;
                case 3: // Deposit
                    performDeposit(scanner, bankSystem);
                    break;
                case 4: // Withdraw
                    performWithdrawal(scanner, bankSystem);
                    break;
                case 5: // Transfer
                    performTransfer(scanner, bankSystem);
                    break;
                case 6: // View history
                    viewTransactionHistory(scanner, bankSystem);
                    break;
                case 7: // Exit
                    exit = true;
                    System.out.println("Thank you for using Simple Bank Application!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void createAccount(Scanner scanner, BankSystem bankSystem) {
        System.out.println("\n=== Create New Account ===");

        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.println("\nSelect account type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");
        System.out.print("Enter your choice: ");
        int accountType = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String accountId = "AC" + String.format("%03d", bankSystem.getNextAccountNumber());
        Account account = null;

        if (accountType == 1) {
            System.out.print("Enter interest rate (e.g. 0.05 for 5%): ");
            double interestRate = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            account = new SavingsAccount(accountId, name, initialDeposit, interestRate);
            System.out.println("Savings account created successfully!");
        } else if (accountType == 2) {
            System.out.print("Enter overdraft limit: ");
            double overdraftLimit = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            account = new CheckingAccount(accountId, name, initialDeposit, overdraftLimit);
            System.out.println("Checking account created successfully!");
        } else {
            System.out.println("Invalid account type selection.");
            return;
        }

        bankSystem.addAccount(account);
        System.out.println("Your account ID is: " + accountId);
    }

    private static void viewAccountDetails(Scanner scanner, BankSystem bankSystem) {
        System.out.println("\n=== View Account Details ===");

        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();

        Account account = bankSystem.findAccount(accountId);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        System.out.println(account.getAccountDetails());
    }

    private static void performDeposit(Scanner scanner, BankSystem bankSystem) {
        System.out.println("\n=== Deposit Money ===");

        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();

        Account account = bankSystem.findAccount(accountId);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (amount <= 0) {
            System.out.println("Invalid amount. Deposit amount must be positive.");
            return;
        }

        boolean success = account.deposit(amount);
        if (success) {
            System.out.printf("Deposit successful. New balance: $%.2f\n", account.getBalance());
        } else {
            System.out.println("Deposit failed. Please try again.");
        }
    }

    private static void performWithdrawal(Scanner scanner, BankSystem bankSystem) {
        System.out.println("\n=== Withdraw Money ===");

        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();

        Account account = bankSystem.findAccount(accountId);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (amount <= 0) {
            System.out.println("Invalid amount. Withdrawal amount must be positive.");
            return;
        }

        boolean success = account.withdraw(amount);
        if (success) {
            System.out.printf("Withdrawal successful. New balance: $%.2f\n", account.getBalance());
        } else {
            System.out.println("Withdrawal failed. Insufficient funds or exceeds overdraft limit.");
        }
    }

    private static void performTransfer(Scanner scanner, BankSystem bankSystem) {
        System.out.println("\n=== Transfer Money ===");

        System.out.print("Enter source account ID: ");
        String sourceId = scanner.nextLine();

        Account sourceAccount = bankSystem.findAccount(sourceId);
        if (sourceAccount == null) {
            System.out.println("Source account not found!");
            return;
        }

        System.out.print("Enter destination account ID: ");
        String destId = scanner.nextLine();

        Account destAccount = bankSystem.findAccount(destId);
        if (destAccount == null) {
            System.out.println("Destination account not found!");
            return;
        }

        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (amount <= 0) {
            System.out.println("Invalid amount. Transfer amount must be positive.");
            return;
        }

        boolean success = bankSystem.transferMoney(sourceAccount, destAccount, amount);
        if (success) {
            System.out.printf("Transfer successful.\n");
            System.out.printf("Source account balance: $%.2f\n", sourceAccount.getBalance());
            System.out.printf("Destination account balance: $%.2f\n", destAccount.getBalance());
        } else {
            System.out.println("Transfer failed. Insufficient funds or exceeds overdraft limit.");
        }
    }

    private static void viewTransactionHistory(Scanner scanner, BankSystem bankSystem) {
        System.out.println("\n=== View Transaction History ===");

        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();

        Account account = bankSystem.findAccount(accountId);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        List<Transaction> transactions = account.getTransactionHistory();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this account.");
            return;
        }

        System.out.println("\nTransaction History for Account: " + accountId);
        System.out.println("----------------------------------------------");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Transaction transaction : transactions) {
            System.out.printf("%-20s | %-10s | $%-10.2f | $%-10.2f\n", 
                    dateFormat.format(transaction.getDate()),
                    transaction.getType(),
                    transaction.getAmount(),
                    transaction.getBalanceAfter());
        }
    }
}

/**
 * Represents a bank system that manages accounts and transactions
 */
class BankSystem {
    private List<Account> accounts;
    private int nextAccountNumber;

    public BankSystem() {
        accounts = new ArrayList<>();
        nextAccountNumber = 3; // Starting from 3 as we already have 2 sample accounts
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public boolean transferMoney(Account from, Account to, double amount) {
        if (from.withdraw(amount)) {
            to.deposit(amount);
            // Add transfer transactions
            from.addTransaction(new Transaction("TRANSFER OUT", amount, from.getBalance()));
            to.addTransaction(new Transaction("TRANSFER IN", amount, to.getBalance()));
            return true;
        }
        return false;
    }

    public int getNextAccountNumber() {
        return nextAccountNumber++;
    }
}

/**
 * Represents a bank account with basic functionality
 */
abstract class Account {
    private String accountId;
    private String accountHolder;
    private double balance;
    private List<Transaction> transactions;

    public Account(String accountId, String accountHolder, double initialDeposit) {
        this.accountId = accountId;
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();

        // Add initial deposit transaction
        if (initialDeposit > 0) {
            addTransaction(new Transaction("DEPOSIT", initialDeposit, initialDeposit));
        }
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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        addTransaction(new Transaction("DEPOSIT", amount, balance));
        return true;
    }

    public abstract boolean withdraw(double amount);

    public abstract String getAccountDetails();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }
}

/**
 * Represents a savings account with interest rate
 */
class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountId, String accountHolder, double initialDeposit, double interestRate) {
        super(accountId, accountHolder, initialDeposit);
        this.interestRate = interestRate;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }

        if (amount > getBalance()) {
            return false; // Insufficient funds
        }

        setBalance(getBalance() - amount);
        addTransaction(new Transaction("WITHDRAW", amount, getBalance()));
        return true;
    }

    public void applyInterest() {
        double interest = getBalance() * interestRate;
        setBalance(getBalance() + interest);
        addTransaction(new Transaction("INTEREST", interest, getBalance()));
    }

    @Override
    public String getAccountDetails() {
        return String.format("Account Type: Savings\n" +
                            "Account ID: %s\n" +
                            "Account Holder: %s\n" +
                            "Balance: $%.2f\n" +
                            "Interest Rate: %.2f%%\n" +
                            "Annual Interest: $%.2f",
                            getAccountId(), getAccountHolder(), getBalance(),
                            interestRate * 100, getBalance() * interestRate);
    }
}

/**
 * Represents a checking account with overdraft facility
 */
class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String accountId, String accountHolder, double initialDeposit, double overdraftLimit) {
        super(accountId, accountHolder, initialDeposit);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }

        if (amount > getBalance() + overdraftLimit) {
            return false; // Exceeds overdraft limit
        }

        setBalance(getBalance() - amount);
        addTransaction(new Transaction("WITHDRAW", amount, getBalance()));
        return true;
    }

    @Override
    public String getAccountDetails() {
        return String.format("Account Type: Checking\n" +
                            "Account ID: %s\n" +
                            "Account Holder: %s\n" +
                            "Balance: $%.2f\n" +
                            "Overdraft Limit: $%.2f\n" +
                            "Available Balance: $%.2f",
                            getAccountId(), getAccountHolder(), getBalance(),
                            overdraftLimit, getBalance() + overdraftLimit);
    }
}

/**
 * Represents a bank transaction
 */
class Transaction {
    private Date date;
    private String type;
    private double amount;
    private double balanceAfter;

    public Transaction(String type, double amount, double balanceAfter) {
        this.date = new Date();
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }
}
