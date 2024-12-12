package GroupManagement;

import ContentCreation.Profile;

public class Admin extends Profile {

    private Group group;

    public Admin(String userId, String email, String username, String hashedpassword, String dateOfBirth, String status,Group group){
        super(userId,email,username,hashedpassword,dateOfBirth,status);
        this.group = group;

        
    }
    public Admin(Profile profile,Group group){
        super(profile.getUserId(),profile.getEmail(),profile.getUsername(),profile.getHashedpassword(),profile.getDateOfBirth(),profile.isStatus());
        this.group = group;

        
    }
    public void setGroup(Group group){
        this.group = group;
    }

    public boolean approveRequests(Profile User){

        return true;
    }
    public boolean denyRequests(Profile User){
        return true;
    }

    public void removeMember(Profile User){

    }

    public void managePosts(Profile User){

    }
}