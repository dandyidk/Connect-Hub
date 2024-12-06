/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UserManagementSystem;
import ContentCreation.Profile;
import ContentCreation.json;


import java.util.HashMap;

/**
 *
 * @author ahmed
 */
public class User extends Profile implements Service{
    private HashMap<String,Profile> unique=new HashMap<String,Profile>();
    public User(String userId, String email, String username, String hashedpassword, String dateOfBirth, String status) {
        super(userId, email, username, hashedpassword, dateOfBirth, status);
    }

    public User(String email, String username, String hashedpassword, String dateOfBirth) {
        super(email, username, hashedpassword, dateOfBirth);
    }
    
    public void signup(){
       json j=new json();
       try{
       this.userId=Integer.toString(json.readProfiles().size());
       }catch(Exception e){
        this.userId ="0";
       }
       this.status = "Offline";
       j.put("User Id",this.userId);
       j.put("Username",this.username);
       j.put("Email",this.email);
       j.put("HashedPassword", this.hashedpassword);
       j.put("Date of Birth", this.dateOfBirth);
       j.put("Status",this.status);
       j.submitProfile();
    }
    public void login(){
        this.status = "Online";
        json j= new json();
        j.put("User Id",this.userId);
       j.put("Username",this.username);
       j.put("Email",this.email);
       j.put("HashedPassword", this.hashedpassword);
       j.put("Date of Birth", this.dateOfBirth);
       j.put("Status",this.status);
       j.submitProfile();
    }
    
}
