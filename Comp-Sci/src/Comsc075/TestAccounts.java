package Comsc075;/*
 * Duy Nguyen
 * Comsc075.TestAccounts.java
 * Creates various accounts with functions for accounting
 */

// Creates base account
class Account {

    private long number;
    private double balance;

    // Constructs object with number and balance
    public Account() {
        this(0L, 0.0);
    }
    public Account(long number, double balance) {
        this.number = number;
        this.balance = balance;
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
            balance  = balance - amount;
        }
    }

    @Override
    public String toString() {
        return String.format("Comsc075.Account Number: %d", number) +
                String.format("\nBalance: $%.2f", balance);
    }

}

// Creates a savings account inherited from account
class SavingsAccount extends Account {

    private double apr;

    // Constructs object from inheritor including apr
    public SavingsAccount() {
        this(0L, 0.0, 0.0);
    }
    public SavingsAccount(long number, double balance, double apr) {
        super(number, balance);
        this.apr = apr;
    }

    // Getters/setters
    public double getApr() {
        return apr;
    }
    public void setApr(double apr) {
        if (apr > 0) {
            this.apr = apr;
        }
    }

    // Functions for calculating interest/formatting
    public double calculateInterest() {
        return getBalance() * apr;
    }

    @Override
    public String toString() {
        return "\n" + super.toString() +
                String.format("\nInterest Rate: %.2f%%", apr * 100);
    }

}

// Creates a Credit card account inherited from account
class CreditCardAccount extends Account {

    private double apr;
    private double creditLimit;

    // Constructs object from inheritor including apr/credit limit
    public CreditCardAccount() {
        this(0L, 0.0, 0.0, 0);
    }
    public CreditCardAccount(long number, double balance,
                             double apr, double creditLimit) {
        super(number, balance);
        this.apr = apr;
        this.creditLimit = creditLimit;
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
        if (apr > 0) {
            this.apr = apr;
        }
    }

    // Functions for calculating payment/withdrawing/formatting
    public double calculatePayment() {
        double payment = 0.0;

        if (getBalance() < 0) {
            payment = Math.min(20, (apr / 12) * -getBalance());
        }

        return payment;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= creditLimit + getBalance()) {
            setBalance(getBalance() - amount);
        }
    }

    @Override
    public String toString() {
        return "\n" + super.toString() +
                String.format("\nInterest Rate: %.2f%%", apr * 100) +
                String.format("\nCredit Limit: $%.2f", creditLimit);
    }
}

// Comsc075.Test all accounts objects
public class TestAccounts {
    public static void main(String[] args) {

        // Creates array of accounts
        Account accounts[] = new Account[5];

        accounts[0] = new Account(1066L, 7500.0);
        accounts[1] = new SavingsAccount(30507L, 4500.0, 0.015);
        accounts[2] = new CreditCardAccount(51782737L, 7000.0, 0.08, 1000);
        accounts[3] = new CreditCardAccount(629553328L, 1500.0, 0.075, 5000);
        accounts[4] = new CreditCardAccount(4977201043L, -5000.0, 0.07, 10000);

        // Deposits/withdraws amounts and prints from all objects in array
        for (int i = 0; i < accounts.length; i++) {

            accounts[i].deposit(2134.0);
            accounts[i].withdraw(4782.0);

            System.out.print(accounts[i]);

            if (accounts[i] instanceof SavingsAccount) {
                System.out.printf("\nInterest: $%.2f",
                        ((SavingsAccount) accounts[i]).calculateInterest());
            }
            else if (accounts[i] instanceof CreditCardAccount) {
                System.out.printf("\nMonthly Payment: $%.2f",
                        ((CreditCardAccount) accounts[i]).calculatePayment());
            }

            System.out.println();
        }
    }
}
