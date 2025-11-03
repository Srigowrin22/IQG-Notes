package training.iqgateway.programs;

public class Convertion {
    public static void main(String[] args) {
        int number = 255; // Example number to convert

        // Convert to different bases
        System.out.println("Original number: " + number);

        // i. Convert to binary
        String binary = Integer.toBinaryString(number);
        System.out.println("Binary: " + binary);

        // ii. Convert to hexadecimal
        String hex = Integer.toHexString(number);
        System.out.println("Hexadecimal: " + hex);

        // iii. Convert to octal
        String octal = Integer.toOctalString(number);
        System.out.println("Octal: " + octal);

        // iv. Vice versa conversions
        System.out.println("\nReverse conversions:");

        // Binary back to integer
        int fromBinary = Integer.parseInt(binary, 2);
        System.out.println("Binary " + binary + " ? " + fromBinary);

        // Hexadecimal back to integer
        int fromHex = Integer.parseInt(hex, 16);
        System.out.println("Hexadecimal " + hex + " ? " + fromHex);

        // Octal back to integer
        int fromOctal = Integer.parseInt(octal, 8);
        System.out.println("Octal " + octal + " ? " + fromOctal);
    }
}
