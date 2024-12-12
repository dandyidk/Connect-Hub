package ContentCreation;


import java.util.Date;


public class Post extends ContentMedia implements Contentcreation,Cloneable{
    
    public Post(String contentId,String authorId,Date timeStamp,Content content){
        super(contentId, authorId, timeStamp, content);


    }
    public Post(String contentId,String authorId,Content content){
        super(contentId, authorId, content);
    }
    public Post(String authorId,Content content){
        super(authorId, content);
    }
    public void createContent(){

        json json = new json();
        json.put("Content ID",this.contentId);
        json.put("Author ID",this.authorId);
        json.put("timeStamp",this.timeStamp.toString());
        json.put("Content Text",this.content.getText());
        json.put("Content Image",this.content.getImage());
        json.submitArray(authorId,"Content");

    }
}
