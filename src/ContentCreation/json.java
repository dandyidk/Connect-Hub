package ContentCreation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import GroupManagement.Admin;
import GroupManagement.Group;
import GroupManagement.GroupRequest;
import GroupManagement.GroupUser;
import GroupManagement.PrimaryGroupAdmin;

import org.json.simple.*;
import org.json.simple.parser.*;

public class json implements FILELOCATION {
    private JSONObject js;

    public json() {
        this.js = new JSONObject();
    }

    @SuppressWarnings("unchecked")
    public void put(String key, String object) { // create an object
        js.put(key, object);
    }

    @SuppressWarnings("unchecked")
    public void put(String key, JSONArray object) { // create an object
        js.put(key, object);
    }

    @SuppressWarnings("unchecked")
    private void createJson() {
        File file = new File(DATABASE);
        try {
            file.createNewFile();
        } catch (IOException f) {
            f.printStackTrace();
        }

        try {
            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            JSONArray array = new JSONArray();
            JSONObject temp = new JSONObject();
            temp.put("Users", array);
            temp.put("Groups", array);
            br.write(temp.toString());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void submitProfile() { // used to submit the profile of a new user, the text is the json.gettext after
                                  // putting text like profile id and what not
        File file = new File(DATABASE);
        try {
            FileReader fileReader = new FileReader(file); // check if file is there
            fileReader.close();

        } catch (FileNotFoundException e) {
            createJson();
        } catch (IOException e) {
            System.out.println("Error");
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            Object obj = parser.parse(new FileReader(DATABASE));
            jsonObject = (JSONObject) obj;
            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            JSONArray temparray = (JSONArray) jsonObject.get("Users");

            for (int i = 0; i < temparray.size(); i++) {
                JSONObject object = (JSONObject) temparray.get(i);
                if (((String) object.get("User Id")).compareTo((String) js.get("User Id")) == 0) {
                    temparray.remove(i);
                    js.put("Content", (JSONArray) object.get("Content"));
                    js.put("Profile", (JSONArray) object.get("Profile"));
                    js.put("Friend Requests", (JSONArray) object.get("Friend Requests"));
                    js.put("Friends", (JSONArray) object.get("Friends"));

                    temparray.add(js);
                    jsonObject.put("Users", temparray);
                    br.write(jsonObject.toString());
                    br.close();
                    return;
                }
            }
            JSONArray emptyarray = new JSONArray();
            js.put("Content", emptyarray);
            js.put("Profile", emptyarray);
            js.put("Friend Requests", emptyarray);
            js.put("Friends", emptyarray);

            temparray.add(js);

            jsonObject.put("Users", temparray);
            br.write(jsonObject.toString());
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void submitArray(String authorId, String key) {
        File file = new File(DATABASE);
        try {
            FileReader fileReader = new FileReader(file); // check if file is there
            fileReader.close();

        } catch (FileNotFoundException e) {
            createJson();
        } catch (IOException e) {
            System.out.println("Error");
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            Object obj = parser.parse(new FileReader(DATABASE));
            jsonObject = (JSONObject) obj;
            JSONArray temparray = (JSONArray) jsonObject.get("Users"); // getting all users

            for (int i = 0; i < temparray.size(); i++) {
                JSONObject temp = (JSONObject) temparray.get(i); // getting each user
                if (((String) temp.get("User Id")).compareTo(authorId) == 0) { // check if the user is the user we wwant
                    JSONArray tempArray2 = (JSONArray) temp.get(key); // getting the array
                    tempArray2.add(js);
                    temp.put(key, tempArray2);
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

    @SuppressWarnings("unchecked")
    public void submitArray(String authorId, String key, String keyID, String ID) { // same as last method but it
                                                                                    // removes dublicate of keyID
        File file = new File(DATABASE);
        try {
            FileReader fileReader = new FileReader(file); // check if file is there
            fileReader.close();

        } catch (FileNotFoundException e) {
            createJson();
        } catch (IOException e) {
            System.out.println("Error");
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            Object obj = parser.parse(new FileReader(DATABASE));
            jsonObject = (JSONObject) obj;
            JSONArray temparray = (JSONArray) jsonObject.get("Users"); // getting all users

            for (int i = 0; i < temparray.size(); i++) {
                JSONObject temp = (JSONObject) temparray.get(i); // getting each user
                if (((String) temp.get("User Id")).compareTo(authorId) == 0) { // check if the user is the user we wwant
                    JSONArray tempArray2 = (JSONArray) temp.get(key); // getting the array
                    for (int j = 0; j < tempArray2.size(); j++) {
                        JSONObject temp2 = (JSONObject) tempArray2.get(j); // loop every object
                        if (((String) temp2.get(keyID)).compareTo(ID) == 0) {
                            tempArray2.remove(j);
                        }
                    }
                    tempArray2.add(js);
                    temp.put(key, tempArray2);
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

    @SuppressWarnings("unchecked")
    public void submitGroupArray(String authorId, String key, String keyID, String ID) { // same as last method but it
        // removes dublicate of keyID
        File file = new File(DATABASE);
        try {
            FileReader fileReader = new FileReader(file); // check if file is there
            fileReader.close();

        } catch (FileNotFoundException e) {
            createJson();
        } catch (IOException e) {
            System.out.println("Error");
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject obj = (JSONObject) parser.parse(new FileReader(DATABASE));
            
            JSONArray temparray = (JSONArray) obj.get("Groups"); // getting all users
            JSONArray temparray3 = (JSONArray) obj.get("Users"); // getting all users
            jsonObject.put("Users",temparray3);
            JSONArray tempor = new JSONArray();
            for (int i = 0; i < temparray.size(); i++) {
                JSONObject temp = (JSONObject) temparray.get(i); // getting each group
                if (((String) temp.get("Group Id")).compareTo(authorId) == 0) { // check if the group is the group we
                                                                                // wwant
                    JSONArray tempArray2 = (JSONArray) temp.get(key); // getting the array
                    for (int j = 0; j < tempArray2.size(); j++) {
                        JSONObject temp2 = (JSONObject) tempArray2.get(j); // loop every object
                        if (((String) temp2.get(keyID)).compareTo(ID) == 0) {
                            tempArray2.remove(j);
                        }
                    }
                    tempArray2.add(js);
                    temp.remove(key);
                    temp.put(key,tempArray2);

                    tempor.add(temp);

                }
            }
            jsonObject.put("Groups",tempor);
            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(jsonObject.toString());
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public void submitGroupArray(String authorId, String key) {
        File file = new File(DATABASE);
        try {
            FileReader fileReader = new FileReader(file); // check if file is there
            fileReader.close();

        } catch (FileNotFoundException e) {
            createJson();
        } catch (IOException e) {
            System.out.println("Error");
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            Object obj = parser.parse(new FileReader(DATABASE));
            jsonObject = (JSONObject) obj;
            JSONArray temparray = (JSONArray) jsonObject.get("Groups"); // getting all users

            for (int i = 0; i < temparray.size(); i++) {
                JSONObject temp = (JSONObject) temparray.get(i); // getting each user
                if (((String) temp.get("Group Id")).compareTo(authorId) == 0) { // check if the user is the user we
                                                                                // wwant
                    JSONArray tempArray2 = (JSONArray) temp.get(key); // getting the array
                    tempArray2.add(js);
                    temp.put(key, tempArray2);
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

    public static HashMap<String, Profile> readProfiles() {
        File file = new File(DATABASE);
        try {
            FileReader fileReader = new FileReader(file); // check if file is there
            fileReader.close();

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.out.println("Error");
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Profile> profiles = new HashMap<String, Profile>();
        try {
            Object obj = parser.parse(new FileReader(DATABASE));
            jsonObject = (JSONObject) obj;
            JSONArray temparray = (JSONArray) jsonObject.get("Users");

            for (int j = 0; j < temparray.size(); j++) {
                JSONObject temp = (JSONObject) temparray.get(j);
                JSONArray tempar1 = (JSONArray) temp.get("Content");
                JSONArray tempar2 = (JSONArray) temp.get("Friends");
                JSONArray tempar3 = (JSONArray) temp.get("Friend Requests");
                JSONArray tempar4 = (JSONArray) temp.get("Profile");

                ContentMedia contents[] = new ContentMedia[tempar1.size()];
                for (int i = 0; i < tempar1.size(); i++) {// contents for loop
                    JSONObject temporary = (JSONObject) tempar1.get(i);
                    Content content = new Content((String) temporary.get("Content Text"),
                            (String) temporary.get("Content Image"));
                    Calendar cal = Calendar.getInstance(); // this part is purely to translate time from string to date,
                    // dont mind it
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    cal.setTime(sdf.parse((String) temporary.get("timeStamp")));
                    contents[i] = new ContentMedia((String) temporary.get("Content ID"),
                            (String) temporary.get("Author ID"), cal.getTime(), content);
                }

                Profile profile = new Profile((String) temp.get("User Id"), (String) temp.get("Email"),
                        (String) temp.get("Username"), (String) temp.get("HashedPassword"),
                        (String) temp.get("Date of Birth"), (String) temp.get("Status"));
                profile.setContents(contents);
                profile.setFriends(tempar2);
                profile.setProfile(tempar4);
                profile.setFriendRequests(tempar3);
                profiles.put((String) temp.get("User Id"), profile);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return profiles;

    }

    public static HashMap<String, Group> readGroups() {
        File file = new File(DATABASE);
        try {
            FileReader fileReader = new FileReader(file); // check if file is there
            fileReader.close();

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.out.println("Error");
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Group> groups = new HashMap<String, Group>();
        try {
            Object obj = parser.parse(new FileReader(DATABASE));
            jsonObject = (JSONObject) obj;
            JSONArray temparray = (JSONArray) jsonObject.get("Groups");

            for (int j = 0; j < temparray.size(); j++) {
                JSONObject temp = (JSONObject) temparray.get(j);
                JSONArray tempar2 = (JSONArray) temp.get("Contents");
                JSONArray tempar3 = (JSONArray) temp.get("Join Requests");

                ContentMedia contents[] = new ContentMedia[tempar2.size()];
                for (int i = 0; i < tempar2.size(); i++) {// contents for loop
                    JSONObject temporary = (JSONObject) tempar2.get(i);
                    Content content = new Content((String) temporary.get("Content Text"),
                            (String) temporary.get("Content Image"));
                    Calendar cal = Calendar.getInstance(); // this part is purely to translate time from string to date,
                    // dont mind it
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    cal.setTime(sdf.parse((String) temporary.get("timeStamp")));
                    contents[i] = new ContentMedia((String) temporary.get("Content ID"),
                            (String) temporary.get("Author ID"), cal.getTime(), content);
                }

                ArrayList <GroupRequest> requests = new ArrayList<GroupRequest>();

                for(int i = 0;i<tempar3.size();i++){
                    JSONObject temporary = (JSONObject) tempar3.get(i);
                    if(((String)temporary.get("User Status")).compareTo("Pending")==0){
                    requests.add(new GroupRequest((String) temporary.get("User Id")));
                    }
                }

                PrimaryGroupAdmin creator = new PrimaryGroupAdmin(json.readProfiles().get(temp.get("Group Creator ID")),
                        null);

                JSONArray tempor = (JSONArray) temp.get("Group Users");
                ArrayList<GroupUser> users = new ArrayList<GroupUser>();
                ArrayList<Admin> admins = new ArrayList<Admin>();
                for (int i = 0; i < tempor.size(); i++) {
                    JSONObject tempora = (JSONObject) tempor.get(i);
                    if (((String) tempora.get("User Status")).compareTo("User") == 0) {
                        GroupUser usering = new GroupUser(
                                json.readProfiles().get(((JSONObject) tempor.get(i)).get("User Id")), null);
                        users.add(usering);
                    } else if (((String) tempora.get("User Status")).compareTo("Admin") == 0) {
                        Admin admini = new Admin(json.readProfiles().get(tempora.get("User Id")), null);
                        admins.add(admini);
                    }

                }

                Group group = new Group((String) temp.get("Group Id"), (String) temp.get("Group Name"),
                        (String) temp.get("Group Description"), (String) temp.get("Group Photo"), creator, users,
                        admins, contents);
                group.setContents(contents);
                GroupRequest [] req = new GroupRequest[requests.size()];
                for(int i =0;i<req.length;i++){
                    req[i] = requests.get(i);
                }
                group.setRequests(req);
                groups.put((String) temp.get("Group Id"), group);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return groups;

    }

    @SuppressWarnings("unchecked")
    public static void deleteGroup(String groupId) { 
        File file = new File(DATABASE);
 

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            Object obj = parser.parse(new FileReader(DATABASE));
            jsonObject = (JSONObject) obj;
            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            JSONArray temparray = (JSONArray) jsonObject.get("Groups");
            JSONArray temparray2 = (JSONArray) jsonObject.get("Users");

            for (int i = 0; i < temparray.size(); i++) {
                JSONObject object = (JSONObject) temparray.get(i); //each group
                if (((String) object.get("Group Id")).compareTo(groupId) == 0) { //found the group
                    temparray.remove(i);

                }
            }
            jsonObject.put("Users",temparray2);
            jsonObject.put("Groups", temparray);
            br.write(jsonObject.toString());
            br.close();
            return;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // public static void deleteGroupObjectOfAnArray(String groupId,String ArrayKey,String KeyId,String objectId) {  
    //     File file = new File(DATABASE);
 

    //     JSONParser parser = new JSONParser();
    //     JSONObject jsonObject = new JSONObject();
    //     try {
    //         Object obj = parser.parse(new FileReader(DATABASE));
    //         jsonObject = (JSONObject) obj;
    //         FileWriter fr = new FileWriter(file, false);
    //         BufferedWriter br = new BufferedWriter(fr);
    //         JSONArray temparray = (JSONArray) jsonObject.get("Groups");
    //         JSONArray temparray2 = (JSONArray) jsonObject.get("Users");

    //         for (int i = 0; i < temparray.size(); i++) {
    //             JSONObject object = (JSONObject) temparray.get(i); //each group
    //             if (((String) object.get("Group Id")).compareTo(groupId) == 0) { //found the group
    //                 JSONArray tempor = (JSONArray) object.get(ArrayKey);
    //                 for(int j = 0;j<tempor.size();j++){
    //                     if(((JSONObject)tempor.get(j)).get(KeyId))
    //                 }
    //                 temparray.remove(i);

    //             }
    //         }
    //         jsonObject.put("Users",temparray2);
    //         jsonObject.put("Groups", temparray);
    //         br.write(jsonObject.toString());
    //         br.close();
    //         return;

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
}