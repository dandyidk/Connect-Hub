/*
 * Comment.java
 * Represents a user's comment on a content item (post or story).
 */
package Commenting;

/**
 * Model class representing a comment made by a user on a specific content item.
 * Stores the comment text, the commenter's user ID, the target content ID,
 * a unique comment ID, and the content author's ID.
 *
 * @see CommentBuilder
 * @see CommentIterator
 */
public class Comment {

    /** The text content of the comment. */
    private String text;

    /** The user ID of the person who wrote the comment. */
    private String userId;

    /** The ID of the content item this comment is attached to. */
    private String contentId;

    /** The unique identifier for this comment. */
    private String id;

    /** The user ID of the content author (owner of the post being commented on). */
    private String author;

    /**
     * Creates a comment with minimal information.
     *
     * @param text   the comment text
     * @param userId the commenter's user ID
     */
    public Comment(String text, String userId) {
        this.text = text;
        this.userId = userId;
    }

    /**
     * Creates a fully initialized comment with all fields.
     *
     * @param text      the comment text
     * @param userId    the commenter's user ID
     * @param contentId the ID of the content being commented on
     * @param id        the unique comment ID
     * @param author    the user ID of the content author
     */
    public Comment(String text, String userId, String contentId, String id, String author) {
        this.text = text;
        this.userId = userId;
        this.id = id;
        this.contentId = contentId;
        this.author = author;
    }

    /** @return the unique comment ID */
    public String getId() {
        return this.id;
    }

    /** @return the commenter's user ID */
    public String getUserId() {
        return this.userId;
    }

    /** @return the text content of this comment */
    public String getText() {
        return this.text;
    }

    /** @return the ID of the content item this comment is attached to */
    public String getContentId() {
        return this.contentId;
    }

    /** @return the user ID of the content author */
    public String getAuthorId() {
        return this.author;
    }
}