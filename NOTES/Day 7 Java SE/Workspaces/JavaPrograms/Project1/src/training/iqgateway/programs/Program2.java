package training.iqgateway.programs;

public class Program2 {
    static void pattern(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n * 2; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                } else {
                    if (j % 2 == 0) {
                        System.out.print(" ");
                    } else {
                        System.out.print("*");
                    }
                }
            }
            for (int k = 0; k < n * 2; k++) {
                System.out.print("=");
            }
            System.out.println();
        }
        for (int i = 0; i < n - 3; i++) {
            for (int j = 0; j < n * 4; j++) {
                System.out.print("=");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 6;
        pattern(n);
    }
}
