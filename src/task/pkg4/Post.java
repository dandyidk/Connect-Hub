package task.pkg4;

import java.util.Calendar;


public class Post extends ContentMedia implements Contentcreation{
    
    public Post(int contentId,int authorId,Calendar timeStamp,Content content){
        super(contentId, authorId, timeStamp, content);


    }
    public Post(int contentId,int authorId,Content content){
        super(contentId, authorId, content);
    }
    public JSON createContent(){

        JSON json = new JSON();
        json.put("Content ID",Integer.toString(this.contentId));
        json.put("Author ID",Integer.toString(this.authorId));
        json.put("timeStamp",this.timeStamp.getTime().toString());
        json.put("Content Text",this.content.getText());
        json.put("Content Text",this.content.getImage());

        return json;
    }
}
