/*
 * NotificationInterface.java
 * Base interface for the Decorator pattern used in the notification system.
 */
package NotificationSystem;

/**
 * Base component interface for the Notification Decorator pattern.
 * Defines the contract for displaying notification content.
 * Concrete decorators wrap this interface to add different notification types
 * (friend requests, group activity, interactions, etc.).
 *
 * @see NotificationDecorator
 * @see NotificationFriendRequestsDecorator
 * @see NotificationGroupDecorator
 * @see NotificationInteractionDecorator
 */
public interface NotificationInterface {

    /**
     * Renders the notification content as a displayable string.
     *
     * @return the formatted notification text
     */
    public String display();
}
