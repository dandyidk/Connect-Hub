package NotificationSystem;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import FriendManagement.FriendRequests;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Notification {

    private Notification() {
        // Private constructor to prevent instantiation
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

    public static void getUserRequests(String filePath, int userId, StringBuilder friendRequestsDisplay) {
        JSONObject jsonData = jsonObjReader(filePath);

        if (jsonData == null) {
            friendRequestsDisplay.append("Error: Unable to read user data.\n");
            return;
        }

        JSONArray usersArray = (JSONArray) jsonData.get("Users");
        if (usersArray == null || usersArray.isEmpty()) {
            friendRequestsDisplay.append("No users found in the data.\n");
            return;
        }

        // Find the current user by ID
        JSONObject currentUser = null;
        for (Object userObject : usersArray) {
            JSONObject user = (JSONObject) userObject;
            int id = Integer.parseInt((String) user.get("User Id"));
            if (id == userId) {
                currentUser = user;
                break;
            }
            
        }

        if (currentUser == null) {
            friendRequestsDisplay.append("User with ID ").append(userId).append(" not found.\n");
            return;
        }

        // Fetch friend requests for the user
        JSONArray friendRequestsArray = (JSONArray) currentUser.get("Friend Requests");
        if (friendRequestsArray == null || friendRequestsArray.isEmpty()) {
            friendRequestsDisplay.append("No friend requests found for you").append("\n");
            return;
        }

        // Iterate over friend requests and fetch details
        for (Object requestObject : friendRequestsArray) {
            JSONObject request = (JSONObject) requestObject;
            String profileId = (String) request.get("Profile Id");

            // Find the friend's details in the users array
            boolean friendFound = false;
            for (Object userObject : usersArray) {
                JSONObject potentialFriend = (JSONObject) userObject;
                String potentialFriendId = (String) potentialFriend.get("User Id");
                if (potentialFriendId.equals(profileId)) {
                    String friendName = (String) potentialFriend.get("Username");
                    friendRequestsDisplay.append("Friend Request from: ").append(friendName).append("\n");
                    friendFound = true;
                    break;
                }
            }

            if (!friendFound) {
                friendRequestsDisplay.append("Friend with Profile ID ").append(profileId).append(" not found in the data.\n");
            }
        }
    }


    public static void getGroupUsers(String filePath, int userId, StringBuilder groupUsersDisplay) {
        JSONObject jsonData = jsonObjReader(filePath);
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


    public static void getGroupPostNotifications(String filePath, int userId, StringBuilder notificationsDisplay) {
        JSONObject jsonData = jsonObjReader(filePath);
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


       // Method to check group status change and notify
       public static void checkGroupStatusChange(String filePath, int userId, StringBuilder notificationsDisplay) {
        JSONObject jsonData = jsonObjReader(filePath);
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
    
            // Proceed with status change check only if the user is in the group
            String currentStatus = (String) group.get("Group Description");
            String previousStatus = (String) group.get("Previos Group Description");
    
            // If there's a status change
            // If there's a status change
            if (previousStatus != null && !previousStatus.equals(currentStatus)) {
                notificationsDisplay.append("Group '").append(groupName).append("' status changed from '")
                        .append(previousStatus).append("' to '").append(currentStatus).append("'.\n");
            }

            // Update previous status for future comparisons
            group.remove("Previos Group Description");
            group.put("Previos Group Description", currentStatus);
        }
    
        // Save the updated JSON data with new "Previous Status" fields
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonData.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void refresh(String filePath, int userId, StringBuilder friendRequestsDisplay, StringBuilder groupUsersDisplay, StringBuilder notificationsDisplay,StringBuilder statusNotificationDisplay) {
        // Clear previous displays
        friendRequestsDisplay.setLength(0);
        groupUsersDisplay.setLength(0);
        notificationsDisplay.setLength(0);
        statusNotificationDisplay.setLength(0);

        // Fetch updated data
        getUserRequests(filePath, userId, friendRequestsDisplay);
        getGroupUsers(filePath, userId, groupUsersDisplay);
        getGroupPostNotifications(filePath, userId, notificationsDisplay);
        checkGroupStatusChange(filePath,userId, statusNotificationDisplay);
        }

         // Method to accept a friend request
    public static void acceptFriendRequest(String filePath, int userId, String profileId) {
        JSONObject jsonData = jsonObjReader(filePath);
        if (jsonData == null) {
            System.out.println("Error: Unable to read user data.");
            return;
        }

        JSONArray usersArray = (JSONArray) jsonData.get("Users");
        if (usersArray == null || usersArray.isEmpty()) {
            System.out.println("No users found in the data.");
            return;
        }

        // Find the current user by userId
        JSONObject currentUser = null;
        for (Object userObject : usersArray) {
            JSONObject user = (JSONObject) userObject;
            int id = Integer.parseInt((String) user.get("User Id"));
            if (id == userId) {
                currentUser = user;
                break;
            }
        }

        if (currentUser == null) {
            System.out.println("User with ID " + userId + " not found.");
            return;
        }

        // Find the friend requests and the corresponding friend
        JSONArray friendRequestsArray = (JSONArray) currentUser.get("Friend Requests");
        JSONArray friendsArray = (JSONArray) currentUser.get("Friends");

        boolean requestFound = false;
        for (Object requestObject : friendRequestsArray) {
            JSONObject request = (JSONObject) requestObject;
            String requestProfileId = (String) request.get("Profile Id");

            if (profileId.equals(requestProfileId)) {
                // Move the friend request to the "Friends" array
                JSONObject friend = new JSONObject();
                friend.put("Profile Id", profileId);
                friendsArray.add(friend);

                // Remove the accepted friend request from the "Friend Requests" array
                friendRequestsArray.remove(requestObject);
                requestFound = true;
                break;
            }
        }

        if (requestFound) {
            // Save the updated JSON data to the file
            try (FileWriter file = new FileWriter(filePath)) {
                file.write(jsonData.toJSONString());
                file.flush();
                System.out.println("Friend request accepted.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Friend request from Profile ID " + profileId + " not found.");
        }
    }

    // Method to delete a friend request
    public static void deleteFriendRequest(String filePath, int userId, String profileId) {
        JSONObject jsonData = jsonObjReader(filePath);
        if (jsonData == null) {
            System.out.println("Error: Unable to read user data.");
            return;
        }

        JSONArray usersArray = (JSONArray) jsonData.get("Users");
        if (usersArray == null || usersArray.isEmpty()) {
            System.out.println("No users found in the data.");
            return;
        }

        // Find the current user by userId
        JSONObject currentUser = null;
        for (Object userObject : usersArray) {
            JSONObject user = (JSONObject) userObject;
            int id = Integer.parseInt((String) user.get("User Id"));
            if (id == userId) {
                currentUser = user;
                break;
            }
        }

        if (currentUser == null) {
            System.out.println("User with ID " + userId + " not found.");
            return;
        }

        // Find the friend requests and remove the specified one
        JSONArray friendRequestsArray = (JSONArray) currentUser.get("Friend Requests");
        boolean requestFound = false;
        for (Object requestObject : friendRequestsArray) {
            JSONObject request = (JSONObject) requestObject;
            String requestProfileId = (String) request.get("Profile Id");

            if (profileId.equals(requestProfileId)) {
                // Remove the friend request
                friendRequestsArray.remove(requestObject);
                requestFound = true;
                break;
            }
        }

        if (requestFound) {
            // Save the updated JSON data to the file
            try (FileWriter file = new FileWriter(filePath)) {
                file.write(jsonData.toJSONString());
                file.flush();
                System.out.println("Friend request deleted.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Friend request from Profile ID " + profileId + " not found.");
        }
    }
    // Method to get user ID from username
    public static String getUserIdFromUsername(String filePath, String username) {
        JSONObject jsonData = jsonObjReader(filePath);

        if (jsonData == null) {
            System.out.println("Error: Unable to read user data.");
            return null;
        }

        JSONArray usersArray = (JSONArray) jsonData.get("Users");
        if (usersArray == null || usersArray.isEmpty()) {
            System.out.println("No users found in the data.");
            return null;
        }

        // Iterate through the users array to find the user by username
        for (Object userObject : usersArray) {
            JSONObject user = (JSONObject) userObject;
            String userUsername = (String) user.get("Username");

            if (username.equalsIgnoreCase(userUsername)) {
                return (String) user.get("User Id");
            }
        }

        // Return null if no user with the specified username is found
        System.out.println("User with username '" + username + "' not found.");
        return null;
    }


    

    public static void main(String[] args) {
        String filePath = "C:\\Users\\mohamed\\OneDrive\\Desktop\\final\\profiles.json"; // Update with the correct path
        int userId = 2; // Replace with the user ID you want to test

        // Example: Accepting a friend request
        acceptFriendRequest(filePath, userId, "3");  // Accept friend request from profile ID "3"

        // Example: Deleting a friend request
        deleteFriendRequest(filePath, userId, "1");  // Delete friend request from profile ID "3"
    }
    
}
