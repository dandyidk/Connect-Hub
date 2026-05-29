/*
 * Validation.java
 * Centralized input validation utilities for the Connect-Hub application.
 */
package UserManagementSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Provides static utility methods for validating user input across the application.
 * Includes validation for integers, email addresses, and dates of birth.
 *
 * @author ahmed
 */
public class Validation {

    /**
     * Checks whether the given string can be parsed as a valid integer.
     *
     * @param num the string to validate
     * @return {@code true} if the string represents a valid integer, {@code false} otherwise
     */
    public static boolean isinteger(String num) {
        if (num == null || num.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates whether the given string is a properly formatted email address.
     * Checks for null/empty input and matches against a standard email regex pattern.
     * <p>
     * Accepted format: {@code local-part@domain.tld} where:
     * <ul>
     *   <li>Local part may contain letters, digits, +, _, ., -</li>
     *   <li>Domain must contain at least one dot separator</li>
     *   <li>Top-level domain must be 2-6 characters</li>
     * </ul>
     *
     * @param email the email string to validate
     * @return {@code true} if the email is valid, {@code false} otherwise
     */
    public static boolean isvalidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        // Ensure the email contains exactly one '@' symbol
        if (!email.contains("@") || email.indexOf("@") != email.lastIndexOf("@")) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email.matches(emailRegex);
    }

    /**
     * Validates whether the given string represents a valid date of birth.
     * The date must be in {@code dd/MM/yyyy} format and must be in the past.
     *
     * @param dob the date of birth string to validate (expected format: dd/MM/yyyy)
     * @return {@code true} if the date is valid and in the past, {@code false} otherwise
     */
    public static boolean isValidDateOfBirth(String dob) {
        if (dob == null || dob.isEmpty()) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Strict parsing — rejects invalid dates like 32/13/2000

        try {
            Date parsedDate = dateFormat.parse(dob);
            Date currentDate = new Date();
            return parsedDate.before(currentDate);
        } catch (ParseException e) {
            return false;
        }
    }
}
