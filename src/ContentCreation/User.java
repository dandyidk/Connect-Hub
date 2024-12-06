/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ContentCreation;




/**
 *
 * @author ahmed
 */
public class User extends Profile implements Service{
    public User(String userId, String email, String username, String hashedpassword, String dateOfBirth, String status) {
        super(userId, email, username, hashedpassword, dateOfBirth, status);
    }

    public User(String email, String username, String hashedpassword, String dateOfBirth) {
        super(email, username, hashedpassword, dateOfBirth);
    }
    
    public void signup(){
        json j = new json();

       this.userId = Integer.toString(json.readProfiles().size());
       this.status = "Online";
       j.put("User Id",this.userId);
       j.put("Username",this.username);
       j.put("Email",this.email);
       j.put("HashedPassword", this.hashedpassword);
       j.put("Date of Birth", this.dateOfBirth);
       j.put("Status",this.status);
       j.submitProfile();
    }
    public void login(){
        
    }
    
}
