package training.iqgateway.programs;

public class Program1 {
    static void javaPattren() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((j == 3 && i != 3) || (i == 2 && j == 0) ||
                    (i == 3 && (j == 1 || j == 2))) {
                    System.out.print("J");
                } else {
                    System.out.print(" ");
                }
            }
            for (int j = 0; j < 3 - i; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < 2 * i + 1; k++) {
                if ((k == (2 * i + 1) / 2 && i == 0) || i == 2 ||
                    (i == 1 && k == (2 * i + 1) / 2 + 1) ||
                    (i == 1 && k == 0) || (i == 3 && (k == 0 || k == 2 * i))) {
                    System.out.print("a");
                } else {
                    System.out.print(" ");
                }
            }
            for (int j = 0; j < 3 - i; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j < 2 * 4 - 1; j++) {
                if (j == i || j == (2 * 4 - 2 - i)) {
                    System.out.print("V");
                } else {
                    System.out.print(" ");

                }
            }
            for (int j = 0; j < 3 - i; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < 2 * i + 1; k++) {
                if ((k == (2 * i + 1) / 2 && i == 0) || i == 2 ||
                    (i == 1 && k == (2 * i + 1) / 2 + 1) ||
                    (i == 1 && k == 0) || (i == 3 && (k == 0 || k == 2 * i))) {
                    System.out.print("a");
                } else {
                    System.out.print(" ");
                }
            }
            for (int j = 0; j < 3 - i; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        javaPattren();
    }
}
