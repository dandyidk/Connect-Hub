package Commenting;

public class Comment {

    private String text;
    private String userId;
    private String contentId;
    private String id;
    private String author;
    public Comment(String text,String userId){
        this.text = text;
        this.userId = userId;
    }
    public Comment(String text,String userId,String contentId,String id,String author){
        this.text = text;
        this.userId = userId;
        this.id = id;
        this.contentId = contentId;
        this.author = author;
    }
    public String getId(){
        return this.id;
    }
    public String getUserId(){
        return this.userId;
    }
    public String getText(){
        return this.text;
    }
    public String getContentId(){
        return this.contentId;
    }
    public String getAuthorId(){
        return this.author;
    }
}