/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ContentCreation;


import java.util.Date;
import java.util.Calendar; 
public class ContentMedia {
    protected String contentId;
    protected String authorId;
    protected Date timeStamp;
    protected Content content;

    public ContentMedia(String contentId,String authorID,Date timeStamp,Content content){
        this.contentId = contentId;
        this.authorId = authorID;
        this.timeStamp = timeStamp;
        this.content = content;
    }
    public ContentMedia(String contentId,String authorID,Content content){
        this.contentId = contentId;
        this.authorId = authorID;
        this.timeStamp = Calendar.getInstance().getTime();
        this.content = content;
    }
    public ContentMedia(String authorID,Content content){
        this.authorId = authorID;
        this.timeStamp = Calendar.getInstance().getTime();
        this.content = content;
    }

    public String getUserId(){
        return this.authorId;
    }
    public void setContentId(String x){
        this.contentId = x;
    }
    public String getContentId(){
        return this.contentId;
    }
    public String getTimeStamp(){
        return this.timeStamp.toString();
    }
    public Content getContent(){
        return this.content;
    }
}