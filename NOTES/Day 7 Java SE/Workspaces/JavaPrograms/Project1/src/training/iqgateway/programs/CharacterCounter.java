package training.iqgateway.programs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacterCounter {
    public static void main(String[] args) {

        String inputString = "!Java1 Java2 Java3!";
        System.out.println("Entered string is : \"!Java1 Java2 Java3!\" ");

        int letters = countMatches(inputString, "[a-zA-Z]");
        int spaces = countMatches(inputString, "\\s");
        int numbers = countMatches(inputString, "\\d");
        int otherChars = inputString.length() - (letters + spaces + numbers);

        System.out.println("\nCharacter Counts");
        System.out.println("Letters: " + letters);
        System.out.println("Spaces: " + spaces);
        System.out.println("Numbers: " + numbers);
        System.out.println("Other Characters: " + otherChars);


    }

    private static int countMatches(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
