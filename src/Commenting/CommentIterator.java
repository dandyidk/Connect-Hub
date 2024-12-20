package Commenting;

import java.util.Iterator;

import ContentCreation.json;

public class CommentIterator implements Iterator<Comment> {
    private int currentIndex;
    private Comment comments[];

    public CommentIterator() {
        this.comments = new Comment[json.readComments().size()];
        int i = 0;
        for (Comment comment : json.readComments().values()) {
            this.comments[i] = comment;
            i++;
        }
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < comments.length;
    }

    @Override
    public Comment next() {
        Comment comment = comments[currentIndex];
        currentIndex++;
        return comment;
    }

    public Comment back() {
        if (currentIndex > 0) {
            Comment comment = comments[currentIndex];
            currentIndex--;
            return comment;
        }
        return null;
    }

}