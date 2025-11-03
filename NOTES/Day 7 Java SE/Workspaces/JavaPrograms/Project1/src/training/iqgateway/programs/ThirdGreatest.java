package training.iqgateway.programs;

public class ThirdGreatest {
    static int greatest(int a, int b, int c) {
        int largest = a;
        if (b > a && b > c) {
            return b;
        } else if (c > b && c > a) {
            return c;
        }
        return largest;
    }

    public static void main(String[] args) {
        int a = 1000;
        int b = 200;
        int c = 30;
        int largestNum = greatest(a, b, c);
        System.out.println("The Largest among Three Numbers is: " +
                           largestNum);
    }
}

