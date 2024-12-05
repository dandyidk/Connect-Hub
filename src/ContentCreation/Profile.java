/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ContentCreation;


import org.json.simple.JSONArray;

/**
 *
 * @author ahmed
 */
public class Profile {
    protected String userId;
    protected String email;
    protected String username;
    protected String hashedpassword;
    protected String dateOfBirth;
    protected String status;
    protected JSONArray contents;
    protected JSONArray friends;
    protected JSONArray profile;
    protected JSONArray friendRequests;

    public Profile(String userId, String email, String username, String hashedpassword, String dateOfBirth, String status) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.hashedpassword = hashedpassword;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
    }
    public Profile( String email, String username, String hashedpassword, String dateOfBirth) {
        this.email = email;
        this.username = username;
        this.hashedpassword = hashedpassword;
        this.dateOfBirth = dateOfBirth;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHashedpassword(String hashedpassword) {
        this.hashedpassword = hashedpassword;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setContents(JSONArray contents) {
        this.contents = contents;
    }
    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedpassword() {
        return hashedpassword;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public JSONArray getContents() {
        return contents;
    }
    public String isStatus() {
        return status;
    }
}
