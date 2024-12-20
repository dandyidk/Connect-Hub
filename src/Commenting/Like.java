package Commenting;

import ContentCreation.ContentMedia;

public class Like {
    private String userId;
    private String contentId;
    private String id;
    private String author;
    public Like(String userId){
        this.userId = userId;
    }
    public Like(String userId,String contentId,String id,String author){

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
    public String getContentId(){
        return this.contentId;
    }
    public String getAuthorId(){
        return this.author;
    }
}

