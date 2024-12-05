/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package task.pkg4;


import java.util.Date;

public class Story extends ContentMedia{
    public Story(String contentId,String authorId,Date timeStamp,Content content){
        super(contentId, authorId, timeStamp, content);
    }
    public Story(String contentId,String authorId,Content content){
        super(contentId, authorId, content);
    }
    public Story(String authorId,Content content){
        super( authorId, content);
    }
    public boolean isExpired(){ 
        if (System.currentTimeMillis() >= this.timeStamp.getTime() + 24 * 60 * 60 * 1000){
            return true;
        }
        return false;
    }
    public void createContent(){
        json json = new json();
        json.put("Content ID",this.contentId);
        json.put("Author ID",this.authorId);
        json.put("timeStamp",this.timeStamp.toString());
        json.put("Content Text",this.content.getText());
        json.put("Content Image",this.content.getImage());
        json.put("Is Expired",Boolean.toString(isExpired()));

        json.submitContent(this.authorId);
    }

}
