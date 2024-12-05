package usermanagementsystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class JSON implements FILELOCATION {
    private String text;

    public JSON() {
        this.text = "";
    }

    public void put(String key, String object) { //create an object 
        this.text = this.text + "\"" + key + "\":\"" + object + "\",";
    }

    public String getText() {
        return "{"+this.text+"}";
    }


    private void createJson() {
        File file = new File(DATABASE);
        try {
            file.createNewFile();
            FileWriter myWriter = new FileWriter(file);
            String stuff = "{\n"+ //default stuff lol
            "\"Users\":[\n"+
            "{\n\n"+
            "\n"+
            "}\n"+
            "]\n"+
            "}";
            myWriter.write(stuff);
            myWriter.close();
        } catch (IOException f) {
            f.printStackTrace();
        }
    }




    public void submitProfile(String text){ //used to submit the profile of a new user, the text is the json.gettext after putting text
        File file = new File(DATABASE);
        String stuff = "";
        try {
            FileReader fileReader = new FileReader(DATABASE); //check if file is there
            fileReader.close();

        } catch (FileNotFoundException e) {
            createJson();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) { //get all file content
                stuff = stuff + myReader.nextLine();
            }
            String objects[] = stuff.split("\\{"); // get all objects inside the json
            for(int i =0;i<objects.length;i++){//split remove the { lol
                objects[i]= objects[i]+"{";
            }


            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            objects[objects.length-1] = objects[objects.length-1].split("}")[0]+"}"+","+text+"]}"; //add in a new object after the last object
            stuff = "";
            for(int i =0;i<objects.length;i++){ //write in the new file fter refresh
                stuff = stuff+objects[i];
            }
            br.write(stuff);
            br.close();
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void submitContent(String text, int ID) { //wip
        File file = new File(DATABASE);
        Profile profiles[] = new Profile[1];
        String stuff = "";
        try {
            FileReader fileReader = new FileReader(DATABASE); //check if file is there
            fileReader.close();

        } catch (FileNotFoundException e) {
            createJson();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) { //get all file content
                stuff = stuff + myReader.nextLine();
            }
            String objects[] = stuff.split("\\{"); // get all objects inside the json
            for(int i =0;i<objects.length;i++){//split remove the { lol
                objects[i]= objects[i]+"{";
            }

            for(int i = 3;i<objects.length;i++){
                if (Integer.parseInt(objects[i].split(":")[1].split(",")[0].split("")[1])==ID){ //if the id is equal then good
                    objects[i] = objects[i].split("}")[0]+"\"Content\" : ["+text+"]}{";
                }
                
            }
            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            stuff = "";
            for(int i =0;i<objects.length;i++){ //write in the new file fter refresh
                stuff = stuff+objects[i];
            }
            br.write(stuff);
            br.close();
            myReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static HashMap<String,Profile> readProfiles(){
        File file = new File(DATABASE);
        HashMap profiles = new HashMap<String ,Profile>();
        String stuff = "";
        try {
            FileReader fileReader = new FileReader(DATABASE); //check if file is there
            fileReader.close();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) { //get all file content
                stuff = stuff + myReader.nextLine();
            }
            String objects[] = stuff.split("\\{"); // get all objects inside the json
            for(int i =0;i<objects.length;i++){//split remove the { lol
                objects[i]= objects[i]+"{";
            }

            for(int i = 3;i<objects.length;i++){
                String data[] = objects[i].split(",");
                String ID = data[0].split(":")[1].replace("\"","");
                String name = data[1].split(":")[1].replace("\"","");
                String email = data[2].split(":")[1].replace("\"","");
                String password = data[3].split(":")[1].replace("\"","");
                String dob = data[4].split(":")[1].replace("\"","");
                String status = data[5].split(":")[1].replace("\"","");
                Profile profile = new Profile(ID,email, name, password, dob,status);
                profiles.put(ID, profile);
                
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        return profiles;

    }
}
