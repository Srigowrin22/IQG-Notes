package bookdetails.oracle.el;

/**
 * Defines the functions for the tag library
 * Each function is defined as a static method
 */

public class Functions {

    public static String reverse(String text) {
        return new StringBuffer(text).reverse().toString();
    }

    public static String caps(String text) {
        return text.toUpperCase();
    }
}

