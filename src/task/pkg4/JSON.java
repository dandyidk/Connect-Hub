package task.pkg4;

public class JSON {
    private String text;
    public JSON (){
        this.text = "";
    }
    public void put(String key,String object){
        this.text = this.text+"\n\""+key+"\":\""+object+"\"";
    }
    public String getText(){
        return this.text;
    }
}
