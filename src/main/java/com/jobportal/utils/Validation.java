package com.jobportal.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    //method for validating first and last name
    public static boolean name(String username) {
        if (username.isEmpty() || username.length() < 3 || username.length() > 12) {
            System.out.println("Name length should be between 3 and 12");
            return false;
        }
        return true;
    }

    //method for validating email
    public static boolean isEmail(String userEmail) {
        Matcher matcher = pattern.matcher(userEmail);
        if (!matcher.matches()) {
            System.out.println("Entered email isn't valid");
            return false;
        }
        return true;
    }

    //method for validating password
    public static boolean password(String userpassword) {
        if (userpassword.length() < 8 || userpassword.length() > 16) {
            System.out.println("password length should be between 8 and 16");
            return false;
        }
        return true;
    }
}
