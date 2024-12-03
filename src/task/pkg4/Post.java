package task.pkg4;

import java.util.Calendar;

public class Post extends ContentMedia{
    public Post(int contentId,int authorId,Calendar timeStamp,Content content){
        super(contentId, authorId, timeStamp, content);
    }
    public Post(int contentId,int authorId,Content content){
        super(contentId, authorId, content);
    }
}
