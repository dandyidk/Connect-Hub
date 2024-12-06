package FriendManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ContentCreation.FILELOCATION;
import ContentCreation.Profile;
import ContentCreation.json;

public class FriendRequests implements FriendRequestsInterface, FILELOCATION {
    private String status;
    private Profile reciever;
    private Profile sender;

    public FriendRequests(String status, Profile reciever, Profile sender) {
        this.status = status;
        this.reciever = reciever;
        this.sender = sender;
    }

    public FriendRequests(String status, String reciever, String sender) {
        this.status = status;
        this.reciever = json.readProfiles().get(sender);
        this.sender = json.readProfiles().get(reciever);
    }

    public void sendFriendRequest() {
        JSONArray friends = sender.getFriends();
        for(int i =0;i<friends.size();i++){
            if(((Friend)friends.get(i)).getUserId().compareTo(reciever.getUserId())==0){
                return;
            }
        }
        this.status = "Pending";
        updateFriendRequests();
    }

    @SuppressWarnings("unchecked")
    public void updateFriendRequests() {

        JSONObject js = new JSONObject();
        js.put("Profile Id", sender.getUserId());
        js.put("Profile Username", sender.getUsername());
        js.put("Request Status", this.status);

        File file = new File(DATABASE);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            Object obj = parser.parse(new FileReader(DATABASE));
            jsonObject = (JSONObject) obj;
            JSONArray temparray = (JSONArray) jsonObject.get("Users");

            for (int i = 0; i < temparray.size(); i++) {
                JSONObject temp = (JSONObject) temparray.get(i);
                if (((String) temp.get("User Id")).compareTo(reciever.getUserId()) == 0) { // found the user
                    JSONArray tempArray2 = (JSONArray) temp.get("Friend Requests"); // got their friend request
                    // need to remove the friend requests

                    for (int j = 0; j < tempArray2.size(); j++) {
                        if (((String) ((JSONObject) tempArray2.get(j)).get("Profile Id"))
                                .compareTo(sender.getUserId()) == 0) { // see if this friend request occured before, if
                                                                       // found then update it

                            if (((String) js.get("Request Status")).compareTo("Removed") == 0) {
                                tempArray2.remove(j);
                            } else {
                                tempArray2.remove((j));

                                tempArray2.add(js);
                                temp.put("Friend Requests", tempArray2);
                                FileWriter fr = new FileWriter(file, false);
                                BufferedWriter br = new BufferedWriter(fr);
                                br.write(jsonObject.toString());
                                br.close();
                                return;
                            }

                        }
                    }
                    tempArray2.add(js);
                    temp.put("Friend Requests", tempArray2);
                }
            }

            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(jsonObject.toString());
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void acceptFriendRequestStatus() {
        this.status = "Accept";
        updateFriendRequests();
        AddingFriendsService add = new AddingFriendsService(this.reciever);
        add.addFriend(sender);
    }

    public void removeFriendRequestStatus() {
        this.status = "Remove";
        updateFriendRequests();
    }

}
