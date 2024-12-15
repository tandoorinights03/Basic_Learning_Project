import java.util.Random;

public class Account {
    private String name;
    private int pin;
    private double balance;
    private String accountNumber;

    // Constructor
    public Account(String name, int pin){
        this.name = name;
        this.pin = pin;
        this.balance = 0.0;
        this.accountNumber = generateAccountNumber();
    }


    public String getName() {
        return name;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    // Method to generate account number
    private String generateAccountNumber() {
        // Get the initial of the name
        char initial = name.charAt(0);

        // Generate a 6-digit random number
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000; 

        // Construct and return the account number
        return "EFB" + initial + String.format("%04d", randomNumber);
    }
}
