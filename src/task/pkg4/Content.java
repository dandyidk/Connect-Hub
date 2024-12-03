/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package task.pkg4;



public class Content {
    private String text;
    private String image;

    public Content(String text, String image){
        this.text = text;
        this.image = image;
    }
    public Content(String text){
        this.text = text;
        this.image = null;
    }
    public String getText(){
        return this.text;
    }
    public String getImage(){
        return this.image;
    }
}
