/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package task.pkg4;


import java.util.Calendar;  
public class ContentMedia {
    protected int contentId;
    protected int authorId;
    protected Calendar timeStamp;
    protected Content content;

    public ContentMedia(int contentId,int authorID,Calendar timeStamp,Content content){
        this.contentId = contentId;
        this.authorId = authorID;
        this.timeStamp = timeStamp;
        this.content = content;
    }
    public ContentMedia(int contentId,int authorID,Content content){
        this.contentId = contentId;
        this.authorId = authorID;
        this.timeStamp = Calendar.getInstance();
        this.content = content;
    }
}