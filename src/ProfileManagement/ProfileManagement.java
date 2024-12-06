package ProfileManagement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class ProfileManagement {

    private ProfileManagement() {
        // Prevent instantiation
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

    @SuppressWarnings("unchecked")
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
                    for (Object profileObject : profileArray) {
                        JSONObject profile = (JSONObject) profileObject;
                        if (profile.containsKey(fieldKey)) {
                            profile.put(fieldKey, fieldValue);
                            fieldUpdated = true;
                            break;
                        }
                    }

                    if (!fieldUpdated) {
                        JSONObject newField = new JSONObject();
                        newField.put(fieldKey, fieldValue);
                        profileArray.add(newField);
                    }

                    user.put("Profile", profileArray);

                    try (FileWriter file = new FileWriter(filePath)) {
                        file.write(jsonData.toJSONString());
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

    @SuppressWarnings("unchecked")
    public static void updatePassword(String newPassword, String filePath, int userId) {
        JSONObject jsonData = jsonObjReader(filePath);
        if (jsonData != null) {
            JSONArray usersArray = (JSONArray) jsonData.get("Users");
            boolean userFound = false;

            for (Object userObject : usersArray) {
                JSONObject user = (JSONObject) userObject;
                int id = Integer.parseInt((String) user.get("User Id"));

                if (id == userId) {
                    user.put("HashedPassword", newPassword);
                    userFound = true;
                    break;
                }
            }

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

    public static void getUserPosts(String filePath, int userId, StringBuilder postsDisplay) {
        JSONObject jsonData = jsonObjReader(filePath);
        if (jsonData != null) {
            JSONArray usersArray = (JSONArray) jsonData.get("Users");
            boolean userFound = false;

            for (Object userObject : usersArray) {
                JSONObject user = (JSONObject) userObject;
                int id = Integer.parseInt((String) user.get("User Id"));

                if (id == userId) {
                    userFound = true;
                    JSONArray postsArray = (JSONArray) user.get("Content");

                    if (postsArray != null && !postsArray.isEmpty()) {
                        for (Object postObject : postsArray) {
                            JSONObject post = (JSONObject) postObject;

                            String timestamp = (String) post.get("timeStamp");
                            String contentText = (String) post.get("Content Text");
                            String contentImage = (String) post.get("Content Image");
                            String contentId = (String) post.get("Content ID");

                            postsDisplay.append("Content ID: ").append(contentId).append("\n");
                            postsDisplay.append("Timestamp: ").append(timestamp).append("\n");
                            postsDisplay.append("Content Text: ").append(contentText).append("\n");
                            if (contentImage != null && !contentImage.isEmpty()) {
                                postsDisplay.append("Content Image: ").append(contentImage).append("\n");
                            }
                            postsDisplay.append("-------------\n");
                        }
                    } else {
                        postsDisplay.append("No posts found for user ID: ").append(userId).append("\n");
                    }
                    break;
                }
            }

            if (!userFound) {
                postsDisplay.append("User with ID ").append(userId).append(" not found.\n");
            }
        }
    }

    public static void getUserFriends(String filePath, int userId, StringBuilder friendsDisplay) {
        JSONObject jsonData = jsonObjReader(filePath);
        if (jsonData != null) {
            JSONArray usersArray = (JSONArray) jsonData.get("Users");
            JSONObject currentUser = null;
            // Find the current user by ID
            for (Object userObject : usersArray) {
                JSONObject user = (JSONObject) userObject;
                int id = Integer.parseInt((String) user.get("User Id"));
                if (id == userId) {
                    currentUser = user;
                    break;
                }
            }
            if (currentUser != null) {
                JSONArray friendsArray = (JSONArray) currentUser.get("Friends");
    
                if (friendsArray != null && !friendsArray.isEmpty()) {
                    for (Object friendObject : friendsArray) {
                        JSONObject friend = (JSONObject) friendObject;
                        String friendId = (String) friend.get("Friend ID");
                        // Find the friend's details in the Users array
                        for (Object userObject : usersArray) {
                            JSONObject potentialFriend = (JSONObject) userObject;
                            String potentialFriendId = (String) potentialFriend.get("User Id");
                            if (potentialFriendId.equals(friendId)) {
                                String friendName = (String) potentialFriend.get("Username");
                                String friendStatus = (String) potentialFriend.get("Status");
    
                                friendsDisplay.append("Friend: ").append(friendName)
                                              .append(" | Status: ").append(friendStatus).append("\n");
                                break;
                            }
                        }
                    }
                } else {
                    friendsDisplay.append("No friends found for user ID: ").append(userId).append("\n");
                }
            } else {
                friendsDisplay.append("User with ID ").append(userId).append(" not found.\n");
            }
        } else {
            friendsDisplay.append("Error: Unable to read user data.\n");
        }
    }
    

    public static JSONObject getUserProfile(String filePath, int userId) {
        JSONObject jsonData = jsonObjReader(filePath);  
        if (jsonData != null) {
            JSONArray usersArray = (JSONArray) jsonData.get("Users");
  
            System.out.println("Users Array: " + usersArray);
            for (Object userObject : usersArray) {
                JSONObject user = (JSONObject) userObject;
    
                // use string comparison to match user ID
                String userIdString = (String) user.get("User Id");
                if (userIdString != null && userIdString.equals(String.valueOf(userId))) {
                    return user; 
                }
            }
        }
        return null; 
    }
    
    
}
