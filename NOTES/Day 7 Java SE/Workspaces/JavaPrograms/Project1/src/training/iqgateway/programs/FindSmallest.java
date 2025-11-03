package training.iqgateway.programs;

public class FindSmallest {
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 15;
        int num3 = 9;

        int smallest = findSmallest(num1, num2, num3);

        System.out.println("The smallest number is: " + smallest);
    }

    public static int findSmallest(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
