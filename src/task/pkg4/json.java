package task.pkg4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

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
            br.write(temp.toString());
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
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
    public void submitContent(String authorId) {
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
            JSONArray temparray = (JSONArray) jsonObject.get("Users");

            for (int i = 0; i < temparray.size(); i++) {
                JSONObject temp = (JSONObject) temparray.get(i);
                if (((String) temp.get("User Id")).compareTo(authorId) == 0) {
                    JSONArray tempArray2 = (JSONArray) temp.get("Content");
                    tempArray2.add(js);
                    temp.put("Content", tempArray2);
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

            for (int i = 0; i < temparray.size(); i++) {
                JSONObject temp = (JSONObject) temparray.get(i);
                JSONArray tempar = (JSONArray) temp.get("Content");

                Profile profile = new Profile((String) temp.get("User Id"), (String) temp.get("Email"),
                        (String) temp.get("Username"), (String) temp.get("HashedPassword"),
                        (String) temp.get("Date of Birth"), (String) temp.get("Status"), tempar);
                profiles.put((String) temp.get("User Id"), profile);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return profiles;

    }
}
