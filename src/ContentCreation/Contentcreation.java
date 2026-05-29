/*
 * Contentcreation.java
 * Strategy interface for content creation (Post, Story, etc.).
 */
package ContentCreation;

/**
 * Strategy interface implemented by all content types that can be
 * created and persisted to the JSON database.
 *
 * @see Post
 * @see Story
 */
public interface Contentcreation {

    /**
     * Creates and persists this content item to the database.
     */
    public void createContent();
}