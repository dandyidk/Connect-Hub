package NotificationSystem;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ContentCreation.Profile;
import ContentCreation.json;

public class NotificationFriendRequestsDecorator extends NotificationDecorator {
    private StringBuilder friendRequestsDisplay;
    private int userId;
    public NotificationFriendRequestsDecorator(StringBuilder friendRequestsDisplay,int userId,NotificationInterface decoratedNotification){
        super(decoratedNotification);
        this.friendRequestsDisplay = friendRequestsDisplay;
        this.userId = userId;
    }

    public void getUserRequests() {
        Profile user = json.readProfiles().get(Integer.toString(userId));
        JSONArray friendRequestsArray=user.getFriendsRequests();

        for (Object requestObject : friendRequestsArray) {
            JSONObject request = (JSONObject) requestObject;
            String profileId = (String) request.get("Profile Id");
            boolean friendFound = false;
            if (((String)request.get("Request Status")).compareTo("Pending")==0) {
                String friendName = json.readProfiles().get(profileId).getUsername();
                friendRequestsDisplay.append("Friend Request from: ").append(friendName).append("\n");
                friendFound = true;
                break;
            }

            if (!friendFound) {
                friendRequestsDisplay.append("No friend requests found for you").append("\n");
            }
        }
    }
    @Override
    public String display() {
       return super.display() +friendRequestsDisplay.toString();
    }
    
    
}