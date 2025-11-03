package training.iqgateway.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static final String VEHICLE_REGEX =
        "^[A-Z]{2}[ -]?[0-9]{2}[ -]?[A-Z]{1,2}[ -]?[0-9]{4}$";
    private static final String NAME_REGEX = "^[a-zA-Z ]+$";
    private static final String AADHAAR_REGEX = "^[2-9]\\d{3} \\d{4} \\d{4}$";
    private static final String PAN_REGEX = "^[A-Z]{5}[0-9]{4}[A-Z]$";
    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9]{4,}$";


    public static boolean isValidVehicleRegistration(String regId) {
        if (regId == null || regId.trim().isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(VEHICLE_REGEX);
        Matcher matcher = pattern.matcher(regId.trim().toUpperCase());
        return matcher.matches();
    }


    public static boolean isValidPhoneNumber(long phoneNumber) {
        String phoneStr = String.valueOf(phoneNumber);
        return phoneStr.matches("^[6-9]\\d{9}$"); // Check if it is exactly 10 digits and starts with 6, 7, 8, or 9
    }


    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return name.matches(NAME_REGEX);
    }


    public static boolean isValidAadhaar(String aadhaar) {
        if (aadhaar == null) {
            return false;
        }
        return aadhaar.matches(AADHAAR_REGEX);
    }


    public static boolean isValidPan(String pan) {
        if (pan == null) {
            return false;
        }
        return pan.matches(PAN_REGEX);
    }


    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        return password.matches(PASSWORD_REGEX);
    }
}
