/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author orlandohuambachano
 */
public class Serialize {
    private final File file;
    public Serialize(File fileToSave){
        file = fileToSave;
    }
    public void save(RecyclApp app){
    
        RecyclApp savedApp = app;
        
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(savedApp);
            out.close();
            fileOut.close();
            System.out.println("Fichier sauvegardé");
        }
        catch(IOException i){
            i.printStackTrace();
        
        }
    }
}
