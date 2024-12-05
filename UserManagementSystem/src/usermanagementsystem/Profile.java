/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usermanagementsystem;

/**
 *
 * @author ahmed
 */
public class Profile {
    protected String userId, email, username, hashedpassword, dateOfBirth, status;

    public Profile(String userId, String email, String username, String hashedpassword, String dateOfBirth, String status) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.hashedpassword = hashedpassword;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
    }

    public Profile(String email, String username, String hashedpassword, String dateOfBirth) {
        this(null, email, username, hashedpassword, dateOfBirth, "Offline");
    }

    // Getters
    public String getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getHashedPassword() { return hashedpassword; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getStatus() { return status; }
}