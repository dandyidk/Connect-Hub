/*
 * Like.java
 * Represents a user's "like" interaction on a content item.
 */
package Commenting;

import ContentCreation.ContentMedia;

/**
 * Model class representing a "like" action performed by a user on a content item.
 * Tracks the liker's user ID, the target content, and a unique like ID.
 *
 * @see LikeFactory
 */
public class Like {

    /** The user ID of the person who liked the content. */
    private String userId;

    /** The ID of the content item that was liked. */
    private String contentId;

    /** The unique identifier for this like. */
    private String id;

    /** The user ID of the content author. */
    private String author;

    /**
     * Creates a like with only the liker's user ID.
     *
     * @param userId the liker's user ID
     */
    public Like(String userId) {
        this.userId = userId;
    }

    /**
     * Creates a fully initialized like with all fields.
     *
     * @param userId    the liker's user ID
     * @param contentId the ID of the content being liked
     * @param id        the unique like ID
     * @param author    the user ID of the content author
     */
    public Like(String userId, String contentId, String id, String author) {
        this.userId = userId;
        this.id = id;
        this.contentId = contentId;
        this.author = author;
    }

    /** @return the unique like ID */
    public String getId() {
        return this.id;
    }

    /** @return the liker's user ID */
    public String getUserId() {
        return this.userId;
    }

    /** @return the ID of the liked content item */
    public String getContentId() {
        return this.contentId;
    }

    /** @return the user ID of the content author */
    public String getAuthorId() {
        return this.author;
    }
}
