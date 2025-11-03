package oe;

import java.text.DecimalFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * A utility class providing various helper methods for:
 * <ul>
 *   <li>Date and calendar calculations</li>
 *   <li>Shipping calculations</li>
 *   <li>Financial formatting</li>
 *   <li>Object information</li>
 * </ul>
 * 
 * <p>All methods are static and the class cannot be instantiated.
 *
 * <p>Example usage:
 * <pre>
 * boolean leap = Util.isLeapYear(2020);
 * String price = Util.toMoney(19.99);
 * Date date = Util.getDate(15, 5, 2023);
 * </pre>
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class Util {
    /**
     * Private constructor to prevent instantiation
     */
    private Util() {}

    /**
     * Determines if a year is a leap year.
     * 
     * @param year The year to check
     * @return true if the year is a leap year, false otherwise
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
    }

    /**
     * Gets the number of days in a specified month and year.
     * 
     * @param month The month (1-12)
     * @param year The year
     * @return Number of days in the month (28-31)
     */
    public static int lastDayInMonth(int month, int year) {
        int daysInMonth = 0;

        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                daysInMonth = 31;
                break;
            case 2:
                daysInMonth = isLeapYear(year) ? 29 : 28;
                break;
            case 4: case 6: case 9: case 11:
                daysInMonth = 30;
                break;
        }
        return daysInMonth;
    }

    /**
     * Gets the shipping days based on region code.
     * 
     * @param region The region code (A=Americas, E=Europe, P=Asia/Pacific)
     * @return Number of days to ship (defaults to 1 for unknown regions)
     */
    public static int getDaysToShip(char region) {
        int daysToShip = 1;
        switch (region) {
            case 'A': daysToShip = 3; break;
            case 'E': daysToShip = 5; break;
            case 'P': daysToShip = 7; break;
        }
        return daysToShip;
    }

    /**
     * Formats a monetary amount as a currency string.
     * 
     * @param amount The amount to format
     * @return Formatted string (e.g., "$1,234.56")
     */
    public static String toMoney(double amount) {
        DecimalFormat df = new DecimalFormat("$##,###.00");
        return df.format(amount);
    }

    /**
     * Formats a Date object as a string.
     * 
     * @param d The Date to format
     * @return Formatted date string (e.g., "15-May-2023")
     */
    public static String toDateString(Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(d);
    }

    /**
     * Creates a Date object from day, month and year values.
     * 
     * @param day The day of month (1-31)
     * @param month The month (1-12)
     * @param year The year
     * @return Corresponding Date object
     */
    public static Date getDate(int day, int month, int year) {
        // Decrement month, Java interprets 0 as January.
        GregorianCalendar gc = new GregorianCalendar(year, --month, day);
        return gc.getTime();
    }

    /**
     * Gets the simple class name (without package) of an object.
     * 
     * @param o The object to inspect
     * @return The simple class name
     */
    public static String getClassName(Object o) {
        String className = o.getClass().getName();
        return className.substring(className.lastIndexOf('.') + 1);
    }

    /**
     * Main method for testing utility functions.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        int year = 1900;
        System.out.println("Year: " + year + " leap year? " + isLeapYear(year));
        year = 1964;
        System.out.println("Year: " + year + " leap year? " + isLeapYear(year));
        year = 1967;
        System.out.println("Year: " + year + " leap year? " + isLeapYear(year));
        year = 1984;
        System.out.println("Year: " + year + " leap year? " + isLeapYear(year));
        year = 1996;
        System.out.println("Year: " + year + " leap year? " + isLeapYear(year));
        year = 1997;
        System.out.println("Year: " + year + " leap year? " + isLeapYear(year));
        year = 2000;
        System.out.println("Year: " + year + " leap year? " + isLeapYear(year));
        year = 2001;
        System.out.println("Year: " + year + " leap year? " + isLeapYear(year));

        int day = 27;
        int month = 1;
        year = 2002;

        int daysInMonth = lastDayInMonth(month, year);
        System.out.println("Date is: " + day + "/" + month + "/" + year +
                           " (and has " + daysInMonth + " days in the month)");
        day = 20;
        month = 2;
        year = 2000;
        daysInMonth = lastDayInMonth(month, year);
        System.out.println("Date is: " + day + "/" + month + "/" + year +
                           " (and has " + daysInMonth + " days in the month)");
        /*
        **  Remove comments to test with month 13.
        **
        month = 13;
        daysInMonth = lastDayInMonth(month, year);
        System.out.println("Date is: " + day + "/" + month + "/" + year +
                           " (and has " + daysInMonth + " days in the month)");
        */
        int daysToShip = getDaysToShip('A');
        System.out.println("It takes " + daysToShip + " days to ship to Americas");

        day = 27;
        month = 1;
        year = 2002;

        daysInMonth = lastDayInMonth(month, year);
        /*
        **  Solution to 4b.
        for (int aDay = day; aDay <= daysInMonth; aDay++)
        {
          System.out.println(aDay + "/" + month + "/" + year);
        }
        */
        day = 10; 
        month = 2;
        /*
        **  4e. with 'E' or 'P'
        daysToShip = getDaysToShip('E');
        daysToShip = getDaysToShip('P');
        */
        for (int aDay = day, j = 0; aDay <= daysInMonth && j < daysToShip; aDay++, j++)
        {
          System.out.println(aDay + "/" + month + "/" + year);
        }
    }
}
