
package chatting.mycompany.chatting;

import java.util.Date;

public class Message {
    private String senderId;
    private String receiverId;
    private String timestamp;
    private String messageText;

    // Constructor
    public Message(String senderId, String receiverId, String messageText) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.timestamp = new Date().toString(); // Set current timestamp
        this.messageText = messageText;
    }

    // Getter for senderId
    public String getSenderId() {
        return senderId;
    }

    // Setter for senderId
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    // Getter for receiverId
    public String getReceiverId() {
        return receiverId;
    }

    // Setter for receiverId
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    // Getter for timestamp
    public String getTimestamp() {
        return timestamp;
    }

    // Setter for timestamp
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // Getter for messageText
    public String getMessageText() {
        return messageText;
    }

    // Setter for messageText
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    // Optionally, override toString for easier display of Message
    @Override
    public String toString() {
        return "Message from " + senderId + " to " + receiverId + " at " + timestamp + ": " + messageText;
    }
}

