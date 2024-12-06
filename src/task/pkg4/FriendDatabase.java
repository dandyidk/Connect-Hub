package task.pkg4;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FriendDatabase {
    private static final String FILENAME = "friends.json"; // File to store friend data
    private List<Friend> friends; // List of Friend objects

    // Constructor: Loads existing friend data from the file
    public FriendDatabase() {
        friends = loadFriends();
    }

    // Loads friend data from the JSON file
    List<Friend> loadFriends() {
        try {
            String content = Files.readString(Path.of(FILENAME)); // Read file content as string
            return new Gson().fromJson(content, new TypeToken<List<Friend>>() {}.getType()); // Parse JSON to List<Friend>
        } catch (IOException e) {
            return new ArrayList<>(); // Return empty list if file not found or error occurs
        }
    }

    // Saves the current friend list to the JSON file
    private void saveFriends() {
        try (Writer writer = new FileWriter(FILENAME)) {
            new Gson().toJson(friends, writer); // Convert list to JSON and write to file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Sends a friend request from userId to friendId
    public void sendFriendRequest(String userId, String friendId) {
        // Check if a friend request already exists
        if (friends.stream().anyMatch(f -> f.getUserId().equals(userId) && f.getFriendId().equals(friendId))) {
            return;
        }
        friends.add(new Friend(userId, friendId, "Pending")); // Add new friend request with "Pending" status
        saveFriends(); // Save changes
    }

    // Responds to a friend request by accepting or declining it
    public void respondToFriendRequest(String userId, String friendId, boolean accept) {
        for (Friend f : friends) {
            // Find the friend request with "Pending" status
            if (f.getUserId().equals(friendId) && f.getFriendId().equals(userId) && f.getStatus().equals("Pending")) {
                f.setStatus(accept ? "Accepted" : "Declined"); // Update status based on response
                saveFriends(); // Save changes
                return;
            }
        }
    }

    // Retrieves a list of all accepted friends for a given user
    public List<String> getFriendList(String userId) {
        List<String> friendList = new ArrayList<>();
        for (Friend f : friends) {
            // Check for "Accepted" friendships involving userId
            if (f.getUserId().equals(userId) && f.getStatus().equals("Accepted")) {
                friendList.add(f.getFriendId());
            } else if (f.getFriendId().equals(userId) && f.getStatus().equals("Accepted")) {
                friendList.add(f.getUserId());
            }
        }
        return friendList;
    }

    // Blocks a friend, preventing interaction
    public void blockFriend(String userId, String friendId) {
        for (Friend f : friends) {
            // Find the friendship relationship
            if ((f.getUserId().equals(userId) && f.getFriendId().equals(friendId)) ||
                (f.getUserId().equals(friendId) && f.getFriendId().equals(userId))) {
                f.setStatus("Blocked"); // Update status to "Blocked"
                saveFriends(); // Save changes
                return;
            }
        }
    }

    // Removes a friend relationship
    public void removeFriend(String userId, String friendId) {
        // Remove friendship involving the given user IDs
        friends.removeIf(f -> (f.getUserId().equals(userId) && f.getFriendId().equals(friendId)) ||
                              (f.getUserId().equals(friendId) && f.getFriendId().equals(userId)));
        saveFriends(); // Save changes
    }

    // Suggests potential friends who are not already connected to the user
    public List<String> suggestFriends(String userId) {
        Set<String> currentFriends = new HashSet<>(getFriendList(userId)); // Get current friends
        currentFriends.add(userId); // Include userId in the set

        List<String> suggestions = new ArrayList<>();
        for (Friend f : friends) {
            // Add suggestions for unconnected users
            if (!currentFriends.contains(f.getUserId())) {
                suggestions.add(f.getUserId());
            } else if (!currentFriends.contains(f.getFriendId())) {
                suggestions.add(f.getFriendId());
            }
        }
        return suggestions;
    }
}
