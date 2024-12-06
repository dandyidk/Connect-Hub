package usermanagementsystem.ProfileManagenebtSystem;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class ProfileManagement {

    // Private constructor to prevent instantiation
    private ProfileManagement() {
        // Nothing to initialize
    }

    // Static method to read and parse the JSON data
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

    // Static method to update the profile picture for a specific user
    public static void updateProfileField(String filePath, int userId, String fieldKey, String fieldValue) {
        JSONObject jsonData = jsonObjReader(filePath);
        if (jsonData != null) {
            JSONArray usersArray = (JSONArray) jsonData.get("Users");
            boolean userFound = false;
    
            for (Object userObject : usersArray) {
                JSONObject user = (JSONObject) userObject;
                int id = Integer.parseInt((String) user.get("User Id"));
                if (id == userId) {
                    userFound = true;
    
                    JSONArray profileArray = (JSONArray) user.get("Profile");
                    if (profileArray == null) {
                        profileArray = new JSONArray();
                    }
    
                    boolean fieldUpdated = false;
    
                    // Search for the field in the profile array and update it if found
                    for (Object profileObject : profileArray) {
                        JSONObject profile = (JSONObject) profileObject;
                        if (profile.containsKey(fieldKey)) {
                            profile.put(fieldKey, fieldValue);
                            fieldUpdated = true;
                            break;
                        }
                    }
    
                    // If the field wasn't found, add a new field
                    if (!fieldUpdated) {
                        JSONObject newField = new JSONObject();
                        newField.put(fieldKey, fieldValue);
                        profileArray.add(newField);
                    }
    
                    user.put("Profile", profileArray);
    
                    // Write the updated JSON back to the file
                    try (FileWriter file = new FileWriter(filePath)) {
                        file.write(jsonData.toJSONString());
                        System.out.println(fieldKey + " updated successfully.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
    
                    break;
                }
            }
    
            if (!userFound) {
                System.out.println("Error: User ID not found.");
            }
        }
    }

    public static void updatePassword(String newPassword, String filePath, int userId) {
        // Read the JSON data from the file
        JSONObject jsonData = jsonObjReader(filePath);
        if (jsonData != null) {
            // Get the array of users
            JSONArray usersArray = (JSONArray) jsonData.get("Users");
            boolean userFound = false;
    
            // Iterate through the users to find the specified user
            for (Object userObject : usersArray) {
                JSONObject user = (JSONObject) userObject;
                int id = Integer.parseInt((String) user.get("User Id"));
    
                if (id == userId) {
                    // Update the password for the user
                    user.put("HashedPassword", newPassword);
                    userFound = true;
                    break;
                }
            }
    
            // If user is found, write the updated JSON back to the file
            if (userFound) {
                try (FileWriter file = new FileWriter(filePath)) {
                    file.write(jsonData.toJSONString());
                    System.out.println("Password updated successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("User with ID " + userId + " not found.");
            }
        }
    }
    

    public static void getUserPosts(String filePath, int userId) {
        // Read the JSON data from the file
        JSONObject jsonData = jsonObjReader(filePath);
        if (jsonData != null) {
            // Get the array of users
            JSONArray usersArray = (JSONArray) jsonData.get("Users");
            boolean userFound = false;
    
            // Iterate through the users to find the specified user
            for (Object userObject : usersArray) {
                JSONObject user = (JSONObject) userObject;
                int id = Integer.parseInt((String) user.get("User Id"));
    
                if (id == userId) {
                    userFound = true;
    
                    // Get the user's posts
                    JSONArray postsArray = (JSONArray) user.get("Content");
    
                    System.out.println("Posts for user ID: " + userId);
    
                    if (postsArray != null && !postsArray.isEmpty()) {
                        for (Object postObject : postsArray) {
                            JSONObject post = (JSONObject) postObject;
    
                            // Fetch post details
                            String timestamp = (String) post.get("timeStamp");
                            String contentText = (String) post.get("Content Text");
                            String contentImage = (String) post.get("Content Image");
                            String contentId = (String) post.get("Content ID");
    
                            // Display post details
                            System.out.println("Content ID:" + contentId);
                            System.out.println("Timestamp: " + timestamp);
                            System.out.println("Content Text: " + contentText);
                            if (contentImage != null && !contentImage.isEmpty()) {
                                System.out.println("Content Image: " + contentImage);
                            }
                            System.out.println("-------------");
                        }
                    } else {
                        System.out.println("No posts found for user ID: " + userId);
                    }
                    break;
                }
            }
    
            if (!userFound) {
                System.out.println("User with ID " + userId + " not found.");
            }
        }
    }

    public static void getUserFriends(String filePath, int userId) {
        // Read the JSON data from the file
        JSONObject jsonData = jsonObjReader(filePath);
        if (jsonData != null) {
            // Get the array of users
            JSONArray usersArray = (JSONArray) jsonData.get("Users");
            boolean userFound = false;
    
            // Iterate through the users to find the specified user
            for (Object userObject : usersArray) {
                JSONObject user = (JSONObject) userObject;
                int id = Integer.parseInt((String) user.get("User Id"));
    
                if (id == userId) {
                    userFound = true;
    
                    // Get the user's friends
                    JSONArray friendsArray = (JSONArray) user.get("Friends");
    
                    System.out.println("Friends for user ID: " + userId);
    
                    if (friendsArray != null && !friendsArray.isEmpty()) {
                        for (Object friendObject : friendsArray) {
                            JSONObject friend = (JSONObject) friendObject;
                            int friendId = Integer.parseInt((String) friend.get("Friend ID"));
    
                            // Find friend's details in the users array
                            for (Object userObj : usersArray) {
                                JSONObject friendDetails = (JSONObject) userObj;
                                int currentUserId = Integer.parseInt((String) friendDetails.get("User Id"));
    
                                if (currentUserId == friendId) {
                                    String username = (String) friendDetails.get("Username");
                                    String status = (String) friendDetails.get("Status");
    
                                    // Display friend details
                                    System.out.println("Friend ID: " + friendId);
                                    System.out.println("Username: " + username);
                                    System.out.println("Status: " + status);
                                    System.out.println("-------------");
                                    break;
                                }
                            }
                        }
                    } else {
                        System.out.println("No friends found for user ID: " + userId);
                    }
                    break;
                }
            }
    
            if (!userFound) {
                System.out.println("User with ID " + userId + " not found.");
            }
        }
    }
    
}
    
    