package Chatting;

import org.json.JSONObject;

import ContentCreation.FILELOCATION;
import ContentCreation.json;
import NotificationSystem.Observer;


public class MessageSender implements FILELOCATION,Observer{

    static String FILE_PATH = CHATS;   // Path to your chat data JSON file
    

    // Method to send a message (takes a Message object as input)
    @Override
    public void update(Message message){
        json.sendMessage(message);
    }
    public void sendMessage(Message message) {
        update(message);
       
    }

    // Helper method to create a JSON object for the message
    private JSONObject createMessageJson(Message message) {
        JSONObject messageJson = new JSONObject();
        messageJson.put("senderId", message.getSenderId());
        messageJson.put("receiverId", message.getReceiverId());
        messageJson.put("timestamp", message.getTimestamp());
        messageJson.put("messageText", message.getMessageText());
        return messageJson;
    }

   
}


