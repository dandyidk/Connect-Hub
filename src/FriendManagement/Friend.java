package FriendManagement;





import ContentCreation.Profile;


public class Friend extends Profile{
    
    private String friendStatus;

    
    public Friend(String userId, String email, String username, String hashedpassword, String dateOfBirth, String friendStatus,String status) {
        super(userId, email, username, hashedpassword, dateOfBirth, status); // Default status is offline
        
        this.friendStatus = friendStatus;
    }

    


    
    public String getStatus() {
        return friendStatus;
    }

    
    public void setStatus(String status) {
        this.friendStatus = status;
    }

}


