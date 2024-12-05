package FriendManagement;


import org.json.simple.JSONArray;

import ContentCreation.Profile;


public class Friend extends Profile {
    
    private String friendStatus;

    
    public Friend(String userId, String email, String username, String hashedpassword, String dateOfBirth, String friendStatus) {
        super(userId, email, username, hashedpassword, dateOfBirth, "offline"); // Default status is offline
        
        this.friendStatus = friendStatus;
    }

    
    public JSONArray fetchContents() {
        return this.contents != null ? this.contents : new JSONArray(); // Return contents or empty array if no contents exist
    }

    
    public String getStatus() {
        return friendStatus;
    }

    
    public void setStatus(String status) {
        this.friendStatus = status;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}


