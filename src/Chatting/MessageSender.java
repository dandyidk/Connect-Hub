package Chatting;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class MessageSender {

    static String FILE_PATH = "C:\\Users\\mohamed\\OneDrive\\Desktop\\test\\Connect-Hub\\Chats.json";   // Path to your chat data JSON file

    // Method to send a message (takes a Message object as input)
    public void sendMessage(Message message) {
        try {
            File file = new File(FILE_PATH);
            JSONObject chatData;

            // If the file exists, read the data
            if (file.exists()) {
                String content = new String(Files.readAllBytes(file.toPath()));
                chatData = new JSONObject(content);
            } else {
                // If the file doesn't exist, create a new JSON object
                chatData = new JSONObject();
                chatData.put("chats", new JSONArray());
            }

            // Check if a chat exists between the sender and receiver
            JSONArray chats = chatData.getJSONArray("chats");
            boolean chatExists = false;

            for (int i = 0; i < chats.length(); i++) {
                JSONObject chat = chats.getJSONObject(i);
                JSONArray users = chat.getJSONArray("users");

                // If the chat contains both the sender and receiver
                if ((users.getString(0).equals(message.getSenderId())) && users.getString(1).equals(message.getReceiverId()) || (users.getString(0).equals(message.getReceiverId()) && users.getString(1).equals(message.getSenderId()))) {
                    // Add the message to the existing chat
                    chat.getJSONArray("messages").put(createMessageJson(message));
                    chatExists = true;
                    break;
                }
            }

            // If no existing chat is found, create a new chat
            if (!chatExists) {
                JSONObject newChat = new JSONObject();
                newChat.put("users", new JSONArray().put(message.getSenderId()).put(message.getReceiverId()));
                JSONArray newMessages = new JSONArray();
                newMessages.put(createMessageJson(message));
                newChat.put("messages", newMessages);
                chats.put(newChat);
            }

            // Write the updated chat data back to the JSON file
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(chatData.toString(4));  // Pretty-print with an indentation of 4
            }

            System.out.println("Message sent successfully from " + message.getSenderId() + " to " + message.getReceiverId());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error sending message.");
        }
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


