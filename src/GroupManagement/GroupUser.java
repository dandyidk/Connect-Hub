package GroupManagement;

import ContentCreation.Profile;

public class GroupUser extends Profile {
    private Group group;
    public GroupUser(String userId, String email, String username, String hashedpassword, String dateOfBirth, String status,Group group){
        super(userId,email,username,hashedpassword,dateOfBirth,status);
    }
    public GroupUser(Profile profile,Group group){
        super(profile.getUserId(),profile.getEmail(),profile.getUsername(),profile.getHashedpassword(),profile.getDateOfBirth(),profile.isStatus());
    }
    public void postContent(){

    }
    public void leaveGroup(){

    }
    public void setGroup(Group group){
        this.group = group;
    }
}
