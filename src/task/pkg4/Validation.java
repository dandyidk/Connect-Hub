/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package task.pkg4;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ahmed
 */
public class Validation {
    public static boolean isinteger(String num){
        try{

        return true;
        }catch(Exception e){
            return false;
        }
    }
    public static boolean isvalidEmail(String email){
        if(email==null || email.isEmpty()){
            return false;
        }
        String emailRegex="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email.matches(emailRegex);
    }
    public static boolean isValidDateOfBirth(String dob) {
        if (dob == null || dob.isEmpty()) {
            return false; // Null or empty strings are invalid
        }

        // Define the expected date format (e.g., "dd/MM/yyyy")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Ensure strict parsing of date

        try {
            Date parsedDate = dateFormat.parse(dob); // Try to parse the date
            Date currentDate = new Date();          // Get the current date

            // Check if the parsed date is before the current date
            return parsedDate.before(currentDate);
        } catch (ParseException e) {
            return false; // If parsing fails, the date is invalid
 }
}

}
