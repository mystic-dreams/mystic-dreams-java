package main.utility;

import java.util.regex.Pattern;

public class Validator {

    /*
        Name Rules:
        1) Alphanumeric only
        2) Up to 18 characters
     */
    public static boolean validateName(String name) {
        Pattern alphanumeric = Pattern.compile("^[a-zA-Z0-9]{1,18}$");

        return alphanumeric.matcher(name).find();

    }
}
