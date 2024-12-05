import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class organized {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\mohamed\\OneDrive\\Desktop\\Term 7\\Programming 2\\Projects\\Connect-Hub\\Profiles.json"; // Path to your minified JSON file

        try {
            // Parse the minified JSON file
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(filePath);
            JSONObject json = (JSONObject) parser.parse(reader);
            reader.close();

            // Write it back with pretty-printing
            FileWriter writer = new FileWriter(filePath);
            writer.write(json.toJSONString().replace(",", ",\n").replace("{", "{\n").replace("}", "\n}"));
            writer.close();

            System.out.println("JSON reformatted successfully.");
        } catch (IOException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
