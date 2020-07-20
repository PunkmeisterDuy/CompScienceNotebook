/*
 * Duy Nguyen
 * TestAccounts.java
 * Records accounts with various functions for accounting
 */

class Account {

    private long number;
    private double balance;

    public Account(long number, double balance) {
        this.number = number;
        this.balance = balance;
    }
    public Account() {
        this(0, 0);
    }

    public long getNumber() {
        return number;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

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

class SavingsAccount extends Account {

    private double apr;

    public SavingsAccount(long number, double balance, double apr) {
        super(number, balance);
        this.apr = apr;
    }
    public SavingsAccount() {
        this(0, 0, 0);
    }

    public double getApr() {
        return apr;
    }
    public void setApr(double apr) {
        this.apr = apr;
    }

    public double calculateInterest() {
        return getBalance() * apr;
    }
    public String toString() {
        return "\n" + super.toString() +
                String.format("\nInterest Rate: %.2f%%", apr * 100) +
                String.format("\nAnnual Interest: $%.2f", calculateInterest());
    }

}

class CreditCardAccount extends Account {

    private double apr;
    private double creditLimit;

    public CreditCardAccount(long number, double balance,
                             double apr, double creditLimit) {

        super(number, balance);
        this.apr = apr;
        this.creditLimit = creditLimit;
    }
    public CreditCardAccount() {
        this(0, 0, 0, 0);
    }

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

    public String toString() {
        return "\n" + super.toString() +
                String.format("\nInterest Rate: %.2f%%", apr * 100) +
                String.format("\nCredit Limit: $%.2f", creditLimit) +
                String.format("\nMonthly Payment: $%.2f", calculatePayment());
    }
    public void withdraw(double amount) {
        if (creditLimit + getBalance() >= amount) {
            setBalance(getBalance() - amount);
        }
    }
    public double calculatePayment() {

        double payment = 0;

        if (getBalance() < 0) {
            payment = Math.min(20, apr / 12 * Math.abs(getBalance()));
        }
        return payment;
    }

}

public class TestAccounts {
    public static void main(String[] args) {

        Account accounts[] = new Account[5];

        accounts[0] = new Account(1066, 7500);
        accounts[1] = new SavingsAccount(30507, 4500, .015);
        accounts[2] = new CreditCardAccount(51782737, 7000, .08, 1000);
        accounts[3] = new CreditCardAccount(629553328, 1500, .075, 5000);
        accounts[4] = new CreditCardAccount(4977201043L, -5000, .07, 10000);

        for (int i = 0; i < accounts.length; i++) {

            accounts[i].deposit(2134);
            accounts[i].withdraw(4782);

            System.out.print(accounts[i]);
            System.out.println();
        }

    }
}
