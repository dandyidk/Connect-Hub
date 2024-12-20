package NotificationSystem;

import org.json.simple.JSONObject;

import Chatting.Message;

public interface Observer {
    public void update(Message message);
}
