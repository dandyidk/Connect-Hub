package NotificationSystem;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ContentCreation.FILELOCATION;
import ContentCreation.json;
import GroupManagement.Admin;
import GroupManagement.Group;

public class NotificationGroupDecorator  extends NotificationDecorator implements FILELOCATION{
    private StringBuilder groupUsersDisplay;
    private StringBuilder notificationsDisplay;
    private int userId;
    private ArrayList<Group> groupies;
    public NotificationGroupDecorator(StringBuilder groupDisplay,int userId,NotificationInterface decoratedNotification){
        super(decoratedNotification);
        this.groupUsersDisplay = groupDisplay;
        this.userId = userId;
    }
    
    public static JSONObject jsonObjReader(String filePath) {
        JSONParser parser = new JSONParser();
        JSONObject jsonData = null;
        try {
            jsonData = (JSONObject) parser.parse(new FileReader(filePath));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
    public void getGroupUsers() {
        JSONObject jsonData = jsonObjReader(DATABASE);
        if (jsonData == null) {
            groupUsersDisplay.append("Error: Unable to read data.\n");
            return;
        }

        JSONArray groupsArray = (JSONArray) jsonData.get("Groups");
        if (groupsArray == null || groupsArray.isEmpty()) {
            groupUsersDisplay.append("No groups found.\n");
            return;
        }

        boolean userInGroup = false;

        for (Object groupObject : groupsArray) {
            JSONObject group = (JSONObject) groupObject;
            JSONArray groupUsers = (JSONArray) group.get("Group Users");
            JSONArray joinRequests = (JSONArray) group.get("Join Requests");

            if (groupUsers != null) {
                for (Object userObject : groupUsers) {
                    JSONObject groupUser = (JSONObject) userObject;
                    int groupId = Integer.parseInt((String) groupUser.get("User Id"));
                    if (groupId == userId) {
                        userInGroup = true;
                        break;
                    }
                }
            }

            if (userInGroup) {
                // Add group name for display
                String groupName = (String) group.get("Group Name");
                groupUsersDisplay.append("Group: ").append(groupName).append("\n");

                // Display users with status "Accepted" or "Removed"
                if (joinRequests != null) {
                    for (Object requestObject : joinRequests) {
                        JSONObject joinRequest = (JSONObject) requestObject;
                        String userStatus = (String) joinRequest.get("User Status");

                        if ("Accepted".equalsIgnoreCase(userStatus) || "Removed".equalsIgnoreCase(userStatus)) {
                            String userIdStr = (String) joinRequest.get("User Id");

                            // Find user details in Users array
                            JSONArray usersArray = (JSONArray) jsonData.get("Users");
                            for (Object userObject : usersArray) {
                                JSONObject user = (JSONObject) userObject;
                                if (user.get("User Id").equals(userIdStr)) {
                                    String username = (String) user.get("Username");
                                    groupUsersDisplay.append(" - ").append(username)
                                            .append(" (").append(userStatus).append(")\n");
                                    break;
                                }
                            }
                        }
                    }
                }
                userInGroup = false; // Reset for the next group
            }
        }
    }
    public void getGroupPostNotifications() {
        JSONObject jsonData = jsonObjReader(DATABASE);
        if (jsonData == null) {
            notificationsDisplay.append("Error: Unable to read data.\n");
            return;
        }
    
        JSONArray groupsArray = (JSONArray) jsonData.get("Groups");
        if (groupsArray == null || groupsArray.isEmpty()) {
            notificationsDisplay.append("No groups found.\n");
            return;
        }
    
        JSONArray usersArray = (JSONArray) jsonData.get("Users");
        if (usersArray == null || usersArray.isEmpty()) {
            notificationsDisplay.append("No users found.\n");
            return;
        }
    
        for (Object groupObject : groupsArray) {
            JSONObject group = (JSONObject) groupObject;
    
            // Check if the user is in the group
            boolean userInGroup = false;
            JSONArray groupUsers = (JSONArray) group.get("Group Users");
            if (groupUsers != null) {
                for (Object userObject : groupUsers) {
                    JSONObject groupUser = (JSONObject) userObject;
                    int groupUserId = Integer.parseInt((String) groupUser.get("User Id"));
                    if (groupUserId == userId) {
                        userInGroup = true;
                        break;
                    }
                }
            }
    
            if (userInGroup) {
                String groupName = (String) group.get("Group Name");
                JSONArray postsArray = (JSONArray) group.get("Contents");
                if (postsArray != null) {
                    for (Object postObject : postsArray) {
                        JSONObject post = (JSONObject) postObject;
    
                        String authorId = (String) post.get("Author ID");
                        String postText = (String) post.get("Content Text");
                        String timeStamp = (String) post.get("timeStamp");
                        String postId = (String) post.get("Content ID");
    
                        // Fetch author details
                        String authorName = "Unknown";
                        for (Object userObject : usersArray) {
                            JSONObject user = (JSONObject) userObject;
                            if (authorId.equals((String) user.get("User Id"))) {
                                authorName = (String) user.get("Username");
                                break;
                            }
                        }
    
                        // Display notification
                        notificationsDisplay.append(authorName)
                                .append(" posted in group ")
                                .append(groupName)
                                .append(" at ")
                                .append(timeStamp)
                                .append(":\n")
                                .append(" - ")
                                .append(postText)
                                .append("\n\n");
                    }
                }
            }
        }
    }
     public void checkGroupStatusChange() {
        JSONObject jsonData = jsonObjReader(DATABASE);
        if (jsonData == null) {
            notificationsDisplay.append("Error: Unable to read data.\n");
            return;
        }
    
        JSONArray groupsArray = (JSONArray) jsonData.get("Groups");
        if (groupsArray == null || groupsArray.isEmpty()) {
            notificationsDisplay.append("No groups found.\n");
            return;
        }
    
        JSONArray usersArray = (JSONArray) jsonData.get("Users");
        if (usersArray == null || usersArray.isEmpty()) {
            notificationsDisplay.append("No users found.\n");
            return;
        }
            
        // Iterate over groups and check for status changes
        for (Object groupObject : groupsArray) {
            JSONObject group = (JSONObject) groupObject;
            
            // Get group details
            String groupName = (String) group.get("Group Name");
            JSONArray groupUsers = (JSONArray) group.get("Group Users");
    
            // Check if user is part of the group
            boolean userInGroup = false;
            for (Object groupUserObject : groupUsers) {
                JSONObject groupUser = (JSONObject) groupUserObject;
                int groupUserId = Integer.parseInt((String) groupUser.get("User Id"));
                if (groupUserId == userId) {
                    userInGroup = true;
                    break;
                }
            }
    
            // If user is not part of the group, skip this group
            if (!userInGroup) {
                continue;
            }
            for(int i =0;i<groupUsers.size();i++){
                JSONObject user = (JSONObject)groupUsers.get(i);
                if(((String)user.get("User Status")).compareTo("Admin")==0){
                    boolean flag = true;
                    for(Group groupoo:groupies){
                        if(groupoo.getId()==Integer.parseInt((String)group.get("Group Id"))){
                            for(Admin admin:groupoo.getAdmin()){
                                if(admin.getUserId().compareTo(((String)user.get("User Status")))==0){
                                    flag = false;
                                }
                            }
                            if(flag){
                                notificationsDisplay.append("In group '").append(groupName).append("' the user '").append(json.readProfiles().get((String)user.get("User Id")).getUsername()).append("was demoted");
  
                            }
                        }
                    }
                }
                if(((String)user.get("User Status")).compareTo("User")==0){
                    boolean flag = true;
                    for(Group groupoo:groupies){
                        if(groupoo.getId()==Integer.parseInt((String)group.get("Group Id"))){
                            for(Admin admin:groupoo.getAdmin()){
                                if(admin.getUserId().compareTo(((String)user.get("User Status")))==0){
                                    flag = false;
                                }
                            }
                            if(flag){
                                notificationsDisplay.append("In group '").append(groupName).append("' the user '").append(json.readProfiles().get((String)user.get("User Id")).getUsername()).append("was promoted");
  
                            }
                        }
                    }
                }
            }

        }
    

    }
    @Override
    public String display(){
        return super.display() + groupUsersDisplay.toString() + notificationsDisplay.toString();
    }
}
