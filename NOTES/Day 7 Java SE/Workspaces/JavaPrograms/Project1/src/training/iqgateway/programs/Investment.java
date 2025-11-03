package training.iqgateway.programs;

import java.util.Scanner;

public class Investment {
    private double invest;
    private int rate;
    public Investment() {
        super();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the investment amount: ");
        double invest = sc.nextDouble();
        
        System.out.println("Input the rate of interest: ");
        int rate = sc.nextInt();
        
        
    }
}
