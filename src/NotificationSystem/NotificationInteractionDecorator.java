package NotificationSystem;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Commenting.Comment;
import ContentCreation.FILELOCATION;
import ContentCreation.json;

public class NotificationInteractionDecorator extends NotificationDecorator implements FILELOCATION{
    private StringBuilder commentsDisplaye;
    private int userId;
    StringBuilder chatsDisplaye;
    public NotificationInteractionDecorator(int userId, StringBuilder commentsDisplaye ,NotificationInterface decoratedNotification,StringBuilder chatsDisplaye){
        super(decoratedNotification);
        this.commentsDisplaye = commentsDisplaye;
        this.userId = userId;
    }
    public static JSONObject jsonObjReader(String filePath) {
        JSONParser parser = new JSONParser();
        JSONObject jsonData = null;
        try {
            jsonData = (JSONObject) parser.parse(new FileReader(filePath));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
    public void checkComments(){
        HashMap<String,Comment> comments = json.readComments();
        String commentText="";
        boolean flag = false;
        for(Comment comment:comments.values()){
            if(comment.getAuthorId().compareTo(json.readProfiles().get(Integer.toString(userId)).getUserId())==0){
                flag = true;
                commentText = commentText + comment.getText();
            }
        }
        if(flag){
            commentsDisplaye.append("New comments from your recent posts:"+commentText);
        }
        else{
            commentsDisplaye.append("No new comments");
        }
    }
    public static void checkChats(String filePath, int userId, StringBuilder chatsDisplaye){
        JSONObject jsonObject = jsonObjReader(CHATS);

        JSONArray chats = (JSONArray) jsonObject.get("chats");

        for (Object chatObj : chats) {
            JSONObject chat = (JSONObject) chatObj;

            JSONArray messages = (JSONArray) chat.get("messages");

            for (Object messageObj : messages) {
                JSONObject message = (JSONObject) messageObj;

                String receiverId = (String) message.get("receiverId");
                String senderId = (String) message.get("senderId");
                if (receiverId.equals(Integer.toString(userId))) {
                    String messageText = (String) message.get("messageText");
                    chatsDisplaye.append("Message from "+json.readProfiles().get(senderId).getUsername()+" :"+messageText);

                }
            }
        }
        }
        @Override
    public String display(){
        return super.display() + commentsDisplaye.toString() + chatsDisplaye.toString();
    }
            
}
