/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package task.pkg4;

import java.util.Calendar;

public class Story extends ContentMedia{
    public Story(int contentId,int authorId,Calendar timeStamp,Content content){
        super(contentId, authorId, timeStamp, content);
    }
    public Story(int contentId,int authorId,Content content){
        super(contentId, authorId, content);
    }
    public boolean isExpired(){
        Calendar temp = (Calendar) this.timeStamp.clone();
        temp.add(Calendar.DAY_OF_MONTH,1);
        if (Calendar.getInstance().compareTo(temp)>0){
            return true;
        }
        return false;
    }
    public JSON createContent(){
        JSON json = new JSON();
        json.put("Content ID",Integer.toString(this.contentId));
        json.put("Author ID",Integer.toString(this.authorId));
        json.put("timeStamp",this.timeStamp.getTime().toString());
        json.put("Content Text",this.content.getText());
        json.put("Is Expired",Boolean.toString(isExpired()));

        return json;
    }

}
