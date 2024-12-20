package Commenting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ContentCreation.ContentMedia;
import ContentCreation.FILELOCATION;
import ContentCreation.json;


public class CommentBuilder implements FILELOCATION {
    private String text;
    private String id;
    private ContentMedia post;
    public CommentBuilder(String text,String id, ContentMedia post){
        this.text = text;
        this.id = id;
        this.post = post;
    }
    @SuppressWarnings("unchecked")
    public void buildComment(){
        JSONObject js= new JSONObject();
        js.put("User Id",id );
        js.put("Text",text);
        js.put("Content ID",post.getContentId());
        js.put("Author ID",post.getContentId());
        String ids = Integer.toString(json.readComments().size());
        js.put("Comment Id",ids);

        
        File file = new File(DATABASE);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            Object obj = parser.parse(new FileReader(DATABASE));
            jsonObject = (JSONObject) obj;
            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            JSONArray temparray = (JSONArray) jsonObject.get("Comments");

            for (int i = 0; i < temparray.size(); i++) {
                JSONObject object = (JSONObject) temparray.get(i);
                if (((String) object.get("Comment Id")).compareTo((String) js.get("Comment Id")) == 0) {
                    temparray.remove(i);
                    temparray.add(js);
                    jsonObject.put("Comments", temparray);
                    br.write(jsonObject.toString());
                    br.close();
                    return;
                }
            }

            temparray.add(js);

            jsonObject.put("Comments", temparray);
            br.write(jsonObject.toString());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
