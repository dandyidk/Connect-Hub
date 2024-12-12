package GroupManagement;

import ContentCreation.Profile;
import ContentCreation.json;

public class PrimaryGroupAdmin extends Admin{
    private Group group;

    public PrimaryGroupAdmin(String userId, String email, String username, String hashedpassword, String dateOfBirth, String status,Group group){
        super(userId,email,username,hashedpassword,dateOfBirth,status,group);
        this.group = group;
    }

    public PrimaryGroupAdmin(Profile profile,Group group){
        super(profile.getUserId(),profile.getEmail(),profile.getUsername(),profile.getHashedpassword(),profile.getDateOfBirth(),profile.isStatus(),group);
        this.group = group;
    }

    public void promote(GroupUser user){
        Admin temp = new Admin(user.getUserId(), user.getEmail(), user.getUsername(), user.getHashedpassword(), user.getDateOfBirth(), user.isStatus(), this.group);
        group.addAdmin(temp);
    }
    public void demote(Admin user){
        group.removeAdmin(user);
        Profile tempo = user;
        GroupUser temp = new GroupUser(tempo, group);
        group.addUser(temp);
    }


    public void deleteGroup(String groupId){
        json.deleteGroup(groupId);
    }
}