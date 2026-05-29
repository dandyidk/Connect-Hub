/*
 * Observer.java
 * Observer interface for the Observer design pattern used in the notification/chat system.
 */
package NotificationSystem;

import Chatting.Message;

/**
 * Observer interface for the Observer design pattern.
 * Implementations receive updates when a new chat message is sent,
 * enabling real-time notification and message persistence.
 *
 * @see Chatting.MessageSender
 */
public interface Observer {

    /**
     * Called when a new message event occurs.
     *
     * @param message the message that triggered the update
     */
    public void update(Message message);
}
