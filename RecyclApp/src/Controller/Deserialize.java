/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author orlandohuambachano
 */
public class Deserialize {
    
    private final File file;
    public Deserialize(File fileToOpen){
        file = fileToOpen;
    }
    
    public  RecyclApp load()
   {
      RecyclApp loadedApp = null;
      try
      {
         FileInputStream fileIn = new FileInputStream(file);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         loadedApp = (RecyclApp) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i)
      {
         i.printStackTrace();
         return loadedApp;
      }catch(ClassNotFoundException c)
      {
         System.out.println("RecyclApp class not found");
         c.printStackTrace();
         return loadedApp;
      }
      System.out.println("Deserialized Employee...");
      
      return loadedApp;
    }
}
