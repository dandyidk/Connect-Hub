package NewsFeed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ContentCreation.Content;
import ContentCreation.ContentMedia;
import ContentCreation.Profile;
import ContentCreation.json;
import FriendManagement.Friend;


public class NewsFeed implements NewsFeedEngine {

    private HashMap<String, Profile> profiles;
    private Profile user;
    private JSONArray friendsArray;

    public NewsFeed(Profile user) {
        profiles = json.readProfiles();
        this.user = user;
        this.friendsArray = user.getFriends();
    }

    @Override
    public ArrayList<ContentMedia> fetchContent() {
        ArrayList<Friend> friends = fetchFriends();
        ArrayList<ContentMedia> contents = new ArrayList<ContentMedia>(); // all contents
        for (int i = 0; i < friendsArray.size(); i++) {
            Profile profile = profiles.get(friends.get(i).getUserId());
            JSONArray friendContents = (profile).getContents(); // getting tht friend contents

            for (int j = 0; j < friendContents.size(); j++) {
                JSONObject contentInfo = (JSONObject) friendContents.get(j);
                try{
                if(((String)contentInfo.get("Is Expired")).compareTo("true")==0){
                    break;
                }
            }catch(NullPointerException e){}
                Content text = new Content((String) contentInfo.get("Content Text"),
                        (String) contentInfo.get("Content Image"));

                Calendar cal = Calendar.getInstance(); // this part is purely to translate time from string to date,
                                                       // dont mind it
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                try {
                    cal.setTime(sdf.parse((String) contentInfo.get("timeStamp")));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                ContentMedia content = new ContentMedia((String) contentInfo.get("Content ID"),
                        (String) contentInfo.get("Author ID"), cal.getTime(), text);
                contents.add(content);
            }
        }
        return  contents;
    }

    @Override
    public ArrayList<Friend> fetchFriends() {
        ArrayList<Friend> friends = new ArrayList<Friend>(); // all contents
        for (int i = 0; i < friendsArray.size(); i++) {
            JSONObject friendInfo = (JSONObject) friendsArray.get(i); // info of each friend
            Profile friend = profiles.get(friendInfo.get("Friend ID")); // getting the friend :D
            Friend friendss = new Friend(friend.getUserId(), friend.getEmail(), friend.getUsername(),friend.getHashedpassword(), friend.getDateOfBirth(), "Friends");
            friends.add( friendss);
        }
        return friends;
    }

    @Override
    public ArrayList<Profile> fetchFriendsSuggesstion() {
        ArrayList<Friend> friends = fetchFriends();
        ArrayList<Profile> friendSug = new ArrayList<Profile>();
        for(int i =0;i<profiles.size();i++){
            boolean flag = true;
            for(int j =0; j<friends.size();j++){
                if(profiles.get(Integer.toString(i)).getUserId().compareTo(friends.get(j).getUserId())==0){
                    flag = false;
                }
            }
            if(flag){
                if(user.getUserId().compareTo(profiles.get(Integer.toString(i)).getUserId())!=0){
                    friendSug.add(profiles.get(Integer.toString(i)));
                }
            }
        }
        return friendSug;

    }

}
