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

public class AddingFriendsService implements FILELOCATION {
    private Profile user;
    Friend friend;
    public AddingFriendsService(Profile user){
        this.user = user;
    }
    public void addFriend(Profile profile){
        json js = new json();
        js.put("Friend ID",profile.getUserId());
        js.put("Friend Username",profile.getUsername());
        js.put("Friend Status","Friends");
        js.submitArray(user.getUserId(), "Friends");
        js = new json();
        js.put("Friend ID",user.getUserId());
        js.put("Friend Username",user.getUsername());
        js.put("Friend Status","Friends");
        js.submitArray(profile.getUserId(), "Friends");
    }
    public void blockFriend(Profile profile){
        json js = new json();
        js.put("Friend ID",profile.getUserId());
        js.put("Friend Username",profile.getUsername());
        js.put("Friend Status","Blocked");
        js.submitArray(user.getUserId(), "Friends","Friend ID",profile.getUserId());
        js = new json();
        js.put("Friend ID",user.getUserId());
        js.put("Friend Username",user.getUsername());
        js.put("Friend Status","Blocked");
        js.submitArray(profile.getUserId(), "Friends","Friend ID",user.getUserId());
    }
    @SuppressWarnings("unchecked")
    public void removeFriend(Profile profile){
        File file = new File(DATABASE);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try { //im too lazy to do a function lol
            Object obj = parser.parse(new FileReader(DATABASE));
            jsonObject = (JSONObject) obj;
            JSONArray temparray = (JSONArray) jsonObject.get("Users");

            for (int i = 0; i < temparray.size(); i++) {
                JSONObject temp = (JSONObject) temparray.get(i);
                if (((String) temp.get("User Id")).compareTo(this.user.getUserId()) == 0) {
                    JSONArray tempArray2 = (JSONArray) temp.get("Friends");
                    for(int j =0;j<tempArray2.size();j++){
                        if(((String)((JSONObject)tempArray2.get(j)).get("Friend ID")).compareTo(profile.getUserId())==0){
                            tempArray2.remove(j);
                        }
                    }
                    temp.put("Friends", tempArray2);
                }
            }

            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(jsonObject.toString());
            br.close();
            //ends here,
            obj = parser.parse(new FileReader(DATABASE));
            jsonObject = (JSONObject) obj;
            temparray = (JSONArray) jsonObject.get("Users");

            for (int i = 0; i < temparray.size(); i++) {
                JSONObject temp = (JSONObject) temparray.get(i);
                if (((String) temp.get("User Id")).compareTo(profile.getUserId()) == 0) {
                    JSONArray tempArray2 = (JSONArray) temp.get("Friends");
                    for(int j =0;j<tempArray2.size();j++){
                        if(((String)((JSONObject)tempArray2.get(j)).get("Friend ID")).compareTo(user.getUserId())==0){
                            tempArray2.remove(j);
                        }
                    }
                    temp.put("Friends", tempArray2);
                }
            }

            fr = new FileWriter(file, false);
             br = new BufferedWriter(fr);
            br.write(jsonObject.toString());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
