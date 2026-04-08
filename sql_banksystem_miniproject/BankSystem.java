import java.io.*;
import java.util.*;

/* ================= ENUM ================= */

enum TransactionType {
    DEPOSIT,
    WITHDRAW
}

/* ================= EXCEPTION ================= */

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}

/* ================= TRANSACTION ================= */

class Transaction implements Serializable {

    private TransactionType type;
    private double amount;
    private Date date;

    public Transaction(TransactionType type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date();
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public void display() {
        System.out.println(type + " | Amount: " + amount + " | " + date);
    }
}

/* ================= ACCOUNT ================= */

class Account implements Serializable {
//why serializable? because we want to save the account details and transactions to a file, so we need to serialize the objects.
//if we don't implement Serializable, we will get a NotSerializableException when we try to save the bank data to a file.
//why we need to serialize the objects  because we want to save the state of the objects to a file, so that we can load it back later and continue from where we left off.
    private int accountNumber;
    private String name;
    private double balance;

    private List<Transaction> transactions = new ArrayList<>();

    public Account(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {

        balance += amount;

        transactions.add(new Transaction(TransactionType.DEPOSIT, amount));

        System.out.println("Deposit successful.");
    }

    public void withdraw(double amount) throws InsufficientBalanceException {

        if (amount > balance) {
            throw new InsufficientBalanceException("❌ Insufficient Balance!");
        }

        balance -= amount;

        transactions.add(new Transaction(TransactionType.WITHDRAW, amount));

        System.out.println("Withdrawal successful.");
    }

    public void showDetails() {
        System.out.println("\nAccount Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void showTransactions() {

        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        transactions.forEach(Transaction::display);
    }
}

/* ================= BANK ================= */

class Bank implements Serializable { 
    

    private Map<Integer, Account> accounts = new HashMap<>();

    public void createAccount(int accNo, String name, double balance) {

        if (accounts.containsKey(accNo)) {
            System.out.println("Account already exists.");
            return;
        }

        accounts.put(accNo, new Account(accNo, name, balance));

        System.out.println("Account created successfully.");
    }

    public Account getAccount(int accNo) {
        return accounts.get(accNo);
    }

    public Collection<Account> getAllAccounts() {
        return accounts.values();
    }
}

/* ================= FILE MANAGER ================= */

class FileManager {

    private static final String FILE = "bankdata.dat";

    public static void save(Bank bank) {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(FILE));

            out.writeObject(bank);

            out.close();

        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    public static Bank load() {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(FILE));

            Bank bank = (Bank) in.readObject();

            in.close();

            return bank;

        } catch (Exception e) {
            return new Bank();
        }
    }
}

/* ================= MAIN ================= */

public class BankSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Bank bank = FileManager.load();

        int choice;

        do {

            System.out.println("\n===== SMART BANK SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show Account");
            System.out.println("5. Show Transactions");
            System.out.println("6. Transaction Analysis");
            System.out.println("7. Exit");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Account Number: ");
                    int acc = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double bal = sc.nextDouble();

                    bank.createAccount(acc, name, bal);

                    break;

                case 2:

                    System.out.print("Enter Account Number: ");
                    acc = sc.nextInt();

                    Account a = bank.getAccount(acc);

                    if (a == null) {
                        System.out.println("Account not found.");
                        break;
                    }

                    System.out.print("Enter amount: ");
                    double dep = sc.nextDouble();

                    a.deposit(dep);

                    break;

                case 3:

                    System.out.print("Enter Account Number: ");
                    acc = sc.nextInt();

                    a = bank.getAccount(acc);

                    if (a == null) {
                        System.out.println("Account not found.");
                        break;
                    }

                    System.out.print("Enter amount: ");
                    double wit = sc.nextDouble();

                    try {
                        a.withdraw(wit);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:

                    System.out.print("Enter Account Number: ");
                    acc = sc.nextInt();

                    a = bank.getAccount(acc);

                    if (a != null)
                        a.showDetails();
                    else
                        System.out.println("Account not found.");

                    break;

                case 5:

                    System.out.print("Enter Account Number: ");
                    acc = sc.nextInt();

                    a = bank.getAccount(acc);

                    if (a != null)
                        a.showTransactions();
                    else
                        System.out.println("Account not found.");


                    break;

                case 6:

                    System.out.print("Enter Account Number: ");
                    acc = sc.nextInt();

                    a = bank.getAccount(acc);

                    if (a == null) {
                        System.out.println("Account not found.");
                        break;
                    }

                    double totalWithdraw =
                            a.getTransactions().stream()
                                    .filter(t -> t.getType() == TransactionType.WITHDRAW)
                                    .mapToDouble(Transaction::getAmount)
                                    .sum();

                    System.out.println("Total Withdrawn: " + totalWithdraw);

                    break;

                case 7:

                    FileManager.save(bank);

                    System.out.println("Data saved. Goodbye!");

                    break;

                default:
                    System.out.println("Invalid choice");

            }

        } while (choice != 7);

        sc.close();
    }
}