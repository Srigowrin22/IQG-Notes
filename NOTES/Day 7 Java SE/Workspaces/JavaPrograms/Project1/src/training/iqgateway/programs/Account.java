package training.iqgateway.programs;

public class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        if (accountNumber != null && !accountNumber.isEmpty() &&
            balance >= 0) {
            this.accountNumber = accountNumber;
            this.balance = balance;
            System.out.println("Account created successfully!...");
            System.out.println("Your ACC NO is : " + this.accountNumber +
                               " with a balance of " + this.balance +
                               " rupees");

        } else {
            System.out.println("Failed to create an Account for the given acountNumber: " +
                               accountNumber);
        }
    }

    public static void main(String[] args) {
        Account acc1 = new Account("12345", 0.022);
        Account acc2 = new Account("54321", -0.226);
    }
}
