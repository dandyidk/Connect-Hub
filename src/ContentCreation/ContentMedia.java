/*
 * ContentMedia.java
 * Base class for all timestamped user-generated content (posts, stories, group content).
 */
package ContentCreation;

import java.util.Date;
import java.util.Calendar;

/**
 * Represents a piece of user-generated content with metadata including
 * a unique ID, the author's user ID, a creation timestamp, and the content body.
 * <p>
 * This serves as the base class for {@link Post} and {@link Story}, providing
 * common fields and constructors for content lifecycle management.
 *
 * @author ahmed
 * @see Post
 * @see Story
 * @see Content
 */
public class ContentMedia {

    /** Unique identifier for this content item. */
    protected String contentId;

    /** The user ID of the content author. */
    protected String authorId;

    /** The timestamp when this content was created. */
    protected Date timeStamp;

    /** The actual content body (text + optional image). */
    protected Content content;

    /**
     * Creates a ContentMedia with all fields specified.
     *
     * @param contentId the unique content identifier
     * @param authorID  the author's user ID
     * @param timeStamp the creation timestamp
     * @param content   the content body
     */
    public ContentMedia(String contentId, String authorID, Date timeStamp, Content content) {
        this.contentId = contentId;
        this.authorId = authorID;
        this.timeStamp = timeStamp;
        this.content = content;
    }

    /**
     * Creates a ContentMedia with an auto-generated timestamp (current time).
     *
     * @param contentId the unique content identifier
     * @param authorID  the author's user ID
     * @param content   the content body
     */
    public ContentMedia(String contentId, String authorID, Content content) {
        this.contentId = contentId;
        this.authorId = authorID;
        this.timeStamp = Calendar.getInstance().getTime();
        this.content = content;
    }

    /**
     * Creates a ContentMedia without a content ID (assigned later).
     *
     * @param authorID the author's user ID
     * @param content  the content body
     */
    public ContentMedia(String authorID, Content content) {
        this.authorId = authorID;
        this.timeStamp = Calendar.getInstance().getTime();
        this.content = content;
    }

    /** @return the author's user ID */
    public String getUserId() {
        return this.authorId;
    }

    /** @param x the content ID to assign */
    public void setContentId(String x) {
        this.contentId = x;
    }

    /** @return the unique content identifier */
    public String getContentId() {
        return this.contentId;
    }

    /** @return the creation timestamp as a formatted string */
    public String getTimeStamp() {
        return this.timeStamp.toString();
    }

    /** @return the content body (text + optional image) */
    public Content getContent() {
        return this.content;
    }
}