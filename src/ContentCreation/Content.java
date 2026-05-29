/*
 * Content.java
 * Represents the raw content data (text and optional image) for a post or story.
 */
package ContentCreation;

/**
 * Encapsulates the raw content of a post or story, consisting of
 * a text body and an optional image file path.
 *
 * @author ahmed
 */
public class Content {

    /** The text body of the content. */
    private String text;

    /** The file path to an associated image, or {@code null} if none. */
    private String image;

    /**
     * Creates a new Content with both text and an image.
     *
     * @param text  the text body of the content
     * @param image the file path to the associated image (may be {@code null})
     */
    public Content(String text, String image) {
        this.text = text;
        this.image = image;
    }

    /**
     * Creates a new Content with text only (no image).
     *
     * @param text the text body of the content
     */
    public Content(String text) {
        this.text = text;
        this.image = null;
    }

    /** @return the text body of this content */
    public String getText() {
        return this.text;
    }

    /** @return the image file path, or {@code null} if no image is attached */
    public String getImage() {
        return this.image;
    }
}
