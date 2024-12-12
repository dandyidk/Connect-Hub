package GroupManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ContentCreation.ContentMedia;
import ContentCreation.FILELOCATION;
import ContentCreation.Profile;
import ContentCreation.json;

public class GroupBuilder  implements FILELOCATION{

    private Profile primaryAdmin;

    public GroupBuilder(Profile creator) {
                
                this.primaryAdmin = creator;
    }

    @SuppressWarnings("unchecked")
    public Group buildGroup(String name, String description, String groupPhoto){
        PrimaryGroupAdmin creator = new PrimaryGroupAdmin(primaryAdmin.getUserId(), primaryAdmin.getEmail(), primaryAdmin.getUsername(), primaryAdmin.getHashedpassword(), primaryAdmin.getDateOfBirth(), primaryAdmin.isStatus(), null);

        Group group = new Group(name,description,groupPhoto,creator);
        creator.setGroup(group);

        for(int i = 0; i <999;i++){
            if(!json.readGroups().containsKey(Integer.toString(i))){
                group.setId(Integer.toString(i));
                break;
            }
        }


        JSONObject js = new JSONObject();
        js.put("Group Id",Integer.toString(group.getId()));
        js.put("Group Name",name);
        js.put("Group Description",description);
        js.put("Group Photo",groupPhoto);
        JSONObject jsarray = new JSONObject();
        JSONArray array= new JSONArray();
        // for(int i = 0;i<userss.length;i++){
        //     userss[i].setGroup(group);
        //     JSONArray temp = new JSONArray();
        //     jsarray.put("User Id",userss[i].getUserId());
        //     jsarray.put("User Status","User");
        //     array.add(jsarray);
        // }
        
        js.put("Group Users",array);
        js.put("Group Creator ID",creator.getUserId());
        js.put("Contents",new JSONArray());
        js.put("Join Requests", array);
        submitGroup(js);

        
        return group;
    }

    @SuppressWarnings("unchecked")
    public void submitGroup(JSONObject js){
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            File file = new File(DATABASE);
            Object obj = parser.parse(new FileReader(DATABASE));
            jsonObject = (JSONObject) obj;
            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            JSONArray temparray = (JSONArray) jsonObject.get("Groups");

            for (int i = 0; i < temparray.size(); i++) {
                JSONObject object = (JSONObject) temparray.get(i);
                if (((String) object.get("Group Id")).compareTo((String) js.get("Group Id")) == 0) {
                    temparray.remove(i);
                    js.put("Group Admins", (JSONArray) object.get("Group Admins"));
                    js.put("Contents", (JSONArray) object.get("Contents"));
                    temparray.add(js);
                    jsonObject.put("Groups", temparray);
                    br.write(jsonObject.toString());
                    br.close();
                    return;
                }
            }
            JSONArray emptyarray = new JSONArray();
            js.put("Contents", emptyarray);
            js.put("Group Admins", emptyarray);

            temparray.add(js);

            jsonObject.put("Groups", temparray);
            br.write(jsonObject.toString());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}