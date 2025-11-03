package training.iqgateway.programs;

public class FutureInvestment {
    public static void main(String[] args) {
        
        double investmentAmount = 1000;
        System.out.print("Investment amount: " + investmentAmount);

        double annualInterestRate = 10;
        System.out.print("Rate of interest: " + annualInterestRate);

        
        System.out.println("\nYears FutureValue");
        for (int year = 1; year <= 5; year++) {
            double futureValue = calculateFutureValue(investmentAmount, annualInterestRate, year);
            System.out.printf("%d %.2f%n", year, futureValue);
        }
    }
    
    public static double calculateFutureValue(double investmentAmount, double annualInterestRate, int years) {
        double monthlyInterestRate = annualInterestRate / 100 / 12;
        int numberOfMonths = years * 12;
        
        double futureValue = investmentAmount * Math.pow(1 + monthlyInterestRate, numberOfMonths);
        return futureValue;
    }
}