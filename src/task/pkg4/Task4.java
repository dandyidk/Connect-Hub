/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package task.pkg4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFileChooser;

/**
 *
 * @author FUJITSU
 */
public class Task4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Content cont = new Content("shit");
        // Story content = new Story(1, 3, cont);
        // System.out.println(content.createContent().getText());

        
        json file = new json();
        file.readProfiles();
        
    }
    
}
