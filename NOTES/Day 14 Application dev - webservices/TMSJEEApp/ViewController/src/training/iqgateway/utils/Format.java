package training.iqgateway.utils;


import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.faces.application.FacesMessage;

public class Format {

    public String toDate(Timestamp date) {
        Date hireDateObj =
            date; // could be java.sql.Timestamp or java.util.Date
        String hireDate = null;
        if (hireDateObj != null) {
            SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // or your desired format
            hireDate = sdf.format(hireDateObj);
        }
        return hireDate;
    }

    public String getDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public String vadilateRegistrationID(String regis) {
        if (regis == null ||
            !regis.matches("^[A-Z]{2}\\d{1,2}[A-Z]{1,2}\\d{4}$")) {
            return "Invalid Registration ID! Format: KA01AB1234";
        }
        return null;
    }

    public String formatTimeFormat(String time) {
        SimpleDateFormat inputFormat =
            new SimpleDateFormat("HH:mm"); // adjust if your input format is different
        SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a");
        Date date;
        try {
            if (time == null || time.trim().isEmpty()) {
                // Use current time
                date = new Date();
            } else {
                // Parse the input time string
                date = inputFormat.parse(time);
            }
            return outputFormat.format(date);
        } catch (ParseException e) {
            // If parsing fails, use current time
            return outputFormat.format(new Date());
        }
    }

    public Timestamp formatDateFormat(Date date) {
        if (date == null) {
            return null; // or handle null as needed
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return java.sql.Timestamp.valueOf(sdf.format(date));
    }

    public Timestamp convertStringToTimestamp(String offenceDate) throws ParseException {
        if (offenceDate == null || offenceDate.trim().isEmpty()) {
            // Return current timestamp if input is null or empty
            return new Timestamp(System.currentTimeMillis());
        }

        // Define possible date formats
        String[] dateFormats = { "yyyy-MM-dd", "dd-MM-yyyy", "MM-yyyy-dd" };

        ParseException parseException = null;

        for (String format : dateFormats) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                sdf.setLenient(false); // Strict parsing
                Date parsedDate = sdf.parse(offenceDate);
                // Convert to Timestamp (time set to 00:00:00)
                return new Timestamp(parsedDate.getTime());
            } catch (ParseException e) {
                parseException = e; // Keep last exception to throw if all fail
            }
        }

        // If none of the formats matched, throw exception or handle it
        throw new ParseException("Unparseable date: \"" + offenceDate + "\"",
                                 0);
    }

    public boolean isOnlyLetters(String str) {
        return str.matches("[a-zA-Z]{3,}");
    }


    // Validate Aadhar: 12 digits, numeric

    //    public String validateAadhar(String aadhar) {
    //        if (aadhar == null) {
    //            return "Aadhar number is required.";
    //        }
    //        // Remove all whitespace
    //        String cleaned = aadhar.replaceAll("\\s", "");
    //        if (cleaned.isEmpty()) {
    //            return "Aadhar number is required.";
    //        }
    //        if (!cleaned.matches("\\d{12}")) {
    //            return "Aadhar number must be exactly 12 digits (no letters or special characters).";
    //        }
    //        return null;
    //    }

    public String validateAadhar(String aadhar) {
        if (aadhar == null) {
            return "Aadhar number is required.";
        }
        if (aadhar.trim().isEmpty()) {
            return "Aadhar number is required.";
        }
        // Validate format: exactly 12 digits, spaces allowed anywhere
        // Regex explanation:
        // ^(?:\d\s*){12}$ means 12 digits possibly separated by spaces
        if (!aadhar.matches("^(?:\\d\\s*){12}$")) {
            return "Aadhar number must contain exactly 12 digits; spaces are allowed but no letters or special characters.";
        }
        return null; // Valid Aadhaar with spaces preserved
    }


    // Validate PAN: 10 chars, format (e.g. ABCDE1234F)

    public String validatePan(String pan) {
        if (pan == null || pan.trim().isEmpty()) {
            return "PAN card number is required.";
        }
        if (!pan.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
            return "PAN card must be 10 characters (e.g. ABCDE1234F).";
        }
        return null;
    }

    // Validate Phone: 10 digits, numeric

    public String validatePhone(Long phone) {
        if (phone == null) {
            return "Phone number is required.";
        }
        String phoneStr = phone.toString();
        if (!phoneStr.matches("\\d{10}")) {
            return "Phone number must be exactly 10 digits.";
        }
        return null;
    }
    
    public boolean isValidPassword(String password) {
        if (password == null || password.length() < 6) return false;
        boolean hasLetter = false, hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) hasLetter = true;
            if (Character.isDigit(c)) hasDigit = true;
        }
        return hasLetter && hasDigit;
    }

}

