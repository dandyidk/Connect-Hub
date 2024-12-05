package task.pkg4;

public class Friend {
    // ID of the user initiating the friend request
    private String userId;
    
    // ID of the user receiving the friend request
    private String friendId;
    
    // Status of the friendship: "Pending", "Accepted", or "Blocked"
    private String status;

    // Constructor to initialize userId, friendId, and status
    public Friend(String userId, String friendId, String status) {
        this.userId = userId;
        this.friendId = friendId;
        this.status = status;
    }

    // Getter for userId
    public String getUserId() { return userId; }

    // Getter for friendId
    public String getFriendId() { return friendId; }

    // Getter for status
    public String getStatus() { return status; }

    // Setter for status
    public void setStatus(String status) { this.status = status; }

    // Converts the Friend object to a string representation
    @Override
    public String toString() {
        return userId + "," + friendId + "," + status;
    }
}
