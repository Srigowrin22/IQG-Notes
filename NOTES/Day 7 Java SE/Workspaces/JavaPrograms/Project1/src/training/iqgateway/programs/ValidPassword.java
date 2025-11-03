package training.iqgateway.programs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPassword {
     static boolean isValidPassword(String password) {
            String regex = "^(?=.*[0-9]{2,})(?=.*[a-zA-Z])[a-zA-Z0-9]{10,}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(password);
            return matcher.matches();
        }
     public static void main(String[] args) {
        String password = "buzz22601MAAN**!";
        if(isValidPassword(password)) {
        System.out.println("The Password your are provided "+password+" is valid");
        } else {
            System.out.println("The Password your are provided "+password+" is not valid");
        }
    }
}
