package Commenting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ContentCreation.ContentMedia;
import ContentCreation.FILELOCATION;
import ContentCreation.json;

public class LikeFactory  implements FILELOCATION{
        private String id;
        private HashMap <String ,Like> likes;
    private ContentMedia post;
     public LikeFactory(){
        this.likes = json.readLikess();
  
    }

@SuppressWarnings("unchecked")
public void buildLike(String id, ContentMedia post){
    for(Like like:likes.values()){
        if((like.getAuthorId().compareTo(post.getUserId())==0)&&(like.getContentId().compareTo(post.getContentId())==0)&&(like.getUserId().compareTo(id)==0)){
            return ;
        }
    }
    JSONObject js= new JSONObject();
    js.put("User Id",id );
    js.put("Content ID",post.getContentId());
    js.put("Author ID",post.getUserId());
    String ids = Integer.toString(json.readLikess().size());
    js.put("Like Id",ids);
    
    File file = new File(DATABASE);

    JSONParser parser = new JSONParser();
    JSONObject jsonObject = new JSONObject();
    try {
        Object obj = parser.parse(new FileReader(DATABASE));
        jsonObject = (JSONObject) obj;
        FileWriter fr = new FileWriter(file, false);
        BufferedWriter br = new BufferedWriter(fr);
        JSONArray temparray = (JSONArray) jsonObject.get("Likes");

        for (int i = 0; i < temparray.size(); i++) {
            JSONObject object = (JSONObject) temparray.get(i);
            if (((String) object.get("Like Id")).compareTo((String) js.get("Like Id")) == 0) {
                temparray.remove(i);
                temparray.add(js);
                jsonObject.put("Likes", temparray);
                br.write(jsonObject.toString());
                br.close();
                return;
            }
        }

        temparray.add(js);

        jsonObject.put("Likes", temparray);
        br.write(jsonObject.toString());
        br.close();
    } catch (Exception e) {
        e.printStackTrace();
    }



}
}
