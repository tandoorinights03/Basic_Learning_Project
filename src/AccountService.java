import java.util.List;
import java.util.ArrayList;

public class AccountService {
    private List<Account> accounts;

    // Constructor
    public AccountService() {
        this.accounts = new ArrayList<>();
    }
    
    public Account createAccount(String name, int pin) {
        Account newAccount = new Account(name, pin);
        accounts.add(newAccount);
        System.out.println("Account created successfully. Account number: " + newAccount.getAccountNumber());
        return newAccount;
    }

    
    public void showAccountInfo(Account account) {
        System.out.println("================================");
        System.out.println("=\tAccount Number: " + account.getAccountNumber());
        System.out.println("=\tName: " + account.getName());
        System.out.println("=\tBalance: $" + account.getBalance());
        System.out.println("================================");
    }

    public double showBalance(Account account) {
        return account.getBalance();
    }

    public void deposit(Account account, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount for deposit.");
            return;
        }
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposit successful. Updated balance: $" + account.getBalance());
    }

    public void withdraw(Account account, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount for withdrawal.");
            return;
        }
        if (amount > account.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }
        account.setBalance(account.getBalance() - amount);
        System.out.println("Withdrawal successful. Updated balance: $" + account.getBalance());
    }

    public void transfer(Account senderAccount, Account recipientAccount, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount for transfer.");
            return;
        }
        if (amount > senderAccount.getBalance()) {
            System.out.println("Insufficient funds for transfer.");
            return;
        }
        senderAccount.setBalance(senderAccount.getBalance() - amount);
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);
        System.out.println("Transfer successful. Updated balance: $" + senderAccount.getBalance());
    }

   
    public Account findAccountByName(String name) {
        for (Account account : accounts) {
            if (account.getName().equals(name)) {
                return account;
            }
        }
        return null;
    }
}
