import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProfileManager {
    private String profilePhotoPath;
    private String coverPhotoPath;
    private String bio;

    // Constructor
    public ProfileManager(String profilePhotoPath, String coverPhotoPath, String bio) {
        this.profilePhotoPath = profilePhotoPath;
        this.coverPhotoPath = coverPhotoPath;
        this.bio = bio;
    }

    // Getters
    public String getProfilePhotoPath() {
        return profilePhotoPath;
    }

    public String getCoverPhotoPath() {
        return coverPhotoPath;
    }

    public String getBio() {
        return bio;
    }

    // Setters
    public void setProfilePhotoPath(String profilePhotoPath) {
        this.profilePhotoPath = profilePhotoPath;
    }

    public void setCoverPhotoPath(String coverPhotoPath) {
        this.coverPhotoPath = coverPhotoPath;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    // Load Profile from JSON
    public static ProfileManager loadFromJson(String filePath, String userId) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(filePath);
        JSONObject root = (JSONObject) parser.parse(reader);
        reader.close();

        JSONArray users = (JSONArray) root.get("users");

        for (Object obj : users) {
            JSONObject user = (JSONObject) obj;
            if (user.get("userId").equals(userId)) {
                JSONObject profile = (JSONObject) user.get("profile");
                return new ProfileManager(
                    (String) profile.get("profilePhotoPath"),
                    (String) profile.get("coverPhotoPath"),
                    (String) profile.get("bio")
                );
            }
        }

        return null; // User not found
    }

    // Save Profile to JSON
    public static void saveToJson(String filePath, String userId, ProfileManager updatedProfile) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(filePath);
        JSONObject root = (JSONObject) parser.parse(reader);
        reader.close();

        JSONArray users = (JSONArray) root.get("users");

        for (Object obj : users) {
            JSONObject user = (JSONObject) obj;
            if (user.get("userId").equals(userId)) {
                JSONObject profile = (JSONObject) user.get("profile");
                profile.put("profilePhotoPath", updatedProfile.getProfilePhotoPath());
                profile.put("coverPhotoPath", updatedProfile.getCoverPhotoPath());
                profile.put("bio", updatedProfile.getBio());
                break;
            }
        }

        FileWriter writer = new FileWriter(filePath);
        writer.write(root.toJSONString());
        writer.close();
    }

}
