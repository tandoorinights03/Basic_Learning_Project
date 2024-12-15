import java.util.InputMismatchException;
import java.util.Scanner;

public class SimpleBankingSystem {
    // Instantiate objects and perform necessary setup
    private static AccountService accountService = new AccountService();
    private static Account currentAccount;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initial menu
        while (true) {
            System.out.println("================================");
            System.out.println("Welcome to Easy Finance Bank!");
            System.out.println("1. Create Account");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
                continue;
            }

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> logIn();
                case 3 -> {
                    System.out.println("Thank you for using Easy Finance Bank. Goodbye!");
                    return;
                }
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                    delay();
                }
            }
        }
    }

    private static void createAccount() {
        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your PIN: ");
            int pin = scanner.nextInt();

            currentAccount = accountService.createAccount(name, pin);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            scanner.nextLine(); 
        }

        delay();
        performAccountOperations();
    }

    private static void logIn() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        Account oldAccount = accountService.findAccountByName(name);

        if (oldAccount == null) {
            System.out.println("Account not found.");
            delay();
            return;
        }

        System.out.print("Enter PIN for " + name + ": ");
        int pin = scanner.nextInt();
        scanner.nextLine(); 

        if (pin == oldAccount.getPin()) {
            currentAccount = oldAccount;
            performAccountOperations();
        } else {
            System.out.println("PIN does not match!");
        }

        delay();
    }

    private static void performAccountOperations() {
        while (true) {
            System.out.println("================================");
            System.out.println("Account Services Menu");
            System.out.println("1. Show Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Account Info");
            System.out.println("6. Log Out");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
                continue;
            }

            switch (choice) {
                case 1 -> showBalance();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> transfer();
                case 5 -> {
                    accountService.showAccountInfo(currentAccount);
                    delay();
                }
                case 6 -> {
                    currentAccount = null;
                    return;
                }
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                    delay();
                }
            }
        }
    }

    private static void showBalance() {
        System.out.println("Current Balance: $" + accountService.showBalance(currentAccount));
        delay();
    }

    private static void deposit() {
        try {
            System.out.print("Enter amount to deposit: $");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            accountService.deposit(currentAccount, amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            scanner.nextLine(); 
        }

        delay();
    }

    private static void withdraw() {
        try {
            System.out.print("Enter amount to withdraw: $");
            double amount = scanner.nextDouble();
            scanner.nextLine(); 

            accountService.withdraw(currentAccount, amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            scanner.nextLine(); 
        }

        delay();
    }

    private static void transfer() {
        System.out.print("Enter recipient's name: ");
        String recipientName = scanner.nextLine();

        Account recipientAccount = accountService.findAccountByName(recipientName);
        if (recipientAccount == null) {
            System.out.println("Recipient account not found.");
            delay();
            return;
        }

        try {
            System.out.print("Enter amount to transfer: $");
            double amount = scanner.nextDouble();
            scanner.nextLine(); 

            accountService.transfer(currentAccount, recipientAccount, amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            scanner.nextLine(); 
        }

        delay();
    }

    private static void delay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
