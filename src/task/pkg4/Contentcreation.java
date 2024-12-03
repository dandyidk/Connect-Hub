/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package task.pkg4;

import java.util.Calendar;


public class Contentcreation {
    public ContentMedia createContent(int contentID,Calendar timeStamp, int authorId,Content content){
        return new ContentMedia(contentID,authorId,timeStamp,content);
    }
    public ContentMedia createContent(int contentID, int authorId,Content content){
        return new ContentMedia(contentID,authorId,content);
    }
}