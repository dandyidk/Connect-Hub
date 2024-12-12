package GroupManagement;

import ContentCreation.json;

public class GroupRequest {

    private String userId;
    private String groupId;
    public GroupRequest(String UserId){
        this.userId = UserId;
    }
    public GroupRequest(String UserId,String groupId){
        this.userId = UserId;
        this.groupId = groupId;
    }
    public void sendRequest(Group group){
        json js = new json();
        js.put("User Id", userId);
        js.put("User Status", "Pending");
        js.submitGroupArray(Integer.toString(group.getId()), "Join Requests","User Id",userId);
    }
    public String getUserId(){
        return userId;
    }
    public String getGroupId(){
        return this.groupId;
    }

    public void declineRequest(Group group){
        json js = new json();
        js.put("User Id", userId);
        js.put("User Status", "Declined");
        js.submitGroupArray(Integer.toString(group.getId()), "Join Requests","User Id",userId);
    }
    public void acceptRequest(Group group){
        json js = new json();
        js.put("User Id", userId);
        js.put("User Status", "Accepted");
        js.submitGroupArray(Integer.toString(group.getId()), "Join Requests","User Id",userId);
    }
}