/*
 * Duy Nguyen
 * TestAccounts.java
 * Creates various accounts with functions for accounting
 */

// Creates base account
class Account {

    private long number;
    private double balance;

    // Constructs object with number and balance
    public Account(long number, double balance) {
        this.number = number;
        this.balance = balance;
    }
    public Account() {
        this(0, 0);
    }

    // Getters/setters
    public long getNumber() {
        return number;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Functions for depositing/withdrawing/formatting
    public void deposit(double amount) {
        if (amount >= 0) {
            balance  += amount;
        }
    }
    public void withdraw(double amount) {
        if ((amount >= 0) && (amount <= balance)){
            balance  -= amount;
        }
    }
    public String toString() {
        return String.format("Account Number: %d", number) +
                String.format("\nBalance: $%.2f", balance);
    }

}

// Creates a savings account inherited from account
class SavingsAccount extends Account {

    private double apr;

    // Constructs object from inheritor including apr
    public SavingsAccount(long number, double balance, double apr) {
        super(number, balance);
        this.apr = apr;
    }
    public SavingsAccount() {
        this(0, 0, 0);
    }

    // Getters/setters
    public double getApr() {
        return apr;
    }
    public void setApr(double apr) {
        this.apr = apr;
    }

    // Functions for calculating interest/formatting
    public double calculateInterest() {
        return getBalance() * apr;
    }
    public String toString() {
        return "\n" + super.toString() +
                String.format("\nInterest Rate: %.2f%%", apr * 100) +
                String.format("\nAnnual Interest: $%.2f", calculateInterest());
    }

}

// Creates a Credit card account inherited from account
class CreditCardAccount extends Account {

    private double apr;
    private double creditLimit;

    // Constructs object from inheritor including apr/credit limit
    public CreditCardAccount(long number, double balance,
                             double apr, double creditLimit) {
        super(number, balance);
        this.apr = apr;
        this.creditLimit = creditLimit;
    }
    public CreditCardAccount() {
        this(0, 0, 0, 0);
    }

    // Getters/setters
    public double getApr() {
        return apr;
    }
    public double getCreditLimit() {
        return creditLimit;
    }
    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }
    public void setApr(double apr) {
        this.apr = apr;
    }

    // Functions for calculating payment/withdrawing/formatting
    public double calculatePayment() {
        double payment = 0;

        if (getBalance() < 0) {
            payment = Math.min(20, apr / 12 * Math.abs(getBalance()));
        }
        return payment;
    }
    public void withdraw(double amount) {
        if (creditLimit + getBalance() >= amount) {
            setBalance(getBalance() - amount);
        }
    }
    public String toString() {
        return "\n" + super.toString() +
                String.format("\nInterest Rate: %.2f%%", apr * 100) +
                String.format("\nCredit Limit: $%.2f", creditLimit) +
                String.format("\nMonthly Payment: $%.2f", calculatePayment());
    }
}

// Test all accounts objects
public class TestAccounts {
    public static void main(String[] args) {

        // Creates array of accounts
        Account accounts[] = new Account[5];

        accounts[0] = new Account(1066, 7500);
        accounts[1] = new SavingsAccount(30507, 4500, .015);
        accounts[2] = new CreditCardAccount(51782737, 7000, .08, 1000);
        accounts[3] = new CreditCardAccount(629553328, 1500, .075, 5000);
        accounts[4] = new CreditCardAccount(4977201043L, -5000, .07, 10000);

        // Deposits/withdraws amounts and prints from all objects in array
        for (int i = 0; i < accounts.length; i++) {

            accounts[i].deposit(2134);
            accounts[i].withdraw(4782);

            System.out.print(accounts[i]);
            System.out.println();
        }
    }
}
