/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import View.MainWindow;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 *
 * @author Vincent
 */
public class Station extends PlantComponant
{

    
  
            
    public Station()
    {    
        this.setDescription("Station");
        this.recoveryMatrix = new RecoveryMatrix();
        this.numberOfExits = 0;
        this.planColor = Color.BLUE;
      
    }
    public void setNumberOfExits(int _numberOfExits)
    {
        this.numberOfExits = _numberOfExits;
       
        for(int i=0;i<_numberOfExits;i++){
            this.recoveryMatrix.addLineToMatrix(Material.MaterialType.product1, i+1, 0);
        }
        for(int i=0;i<_numberOfExits;i++){
            this.recoveryMatrix.addLineToMatrix(Material.MaterialType.product2, i+1, 0);
        }
    }
        
    public int getNumberOfExits()
    {
        return this.numberOfExits;
    }
    


    public void AddLineToMatrix(Material.MaterialType type, int exitNumber, float pourcentage) {
        this.recoveryMatrix.addLineToMatrix(type, exitNumber, pourcentage);
    }

    public void clearMatrix() {
        this.recoveryMatrix = new RecoveryMatrix();
    }
    public void drawImage(MainWindow _observer){
        image =true;
        this.observer = _observer;
    }
    public void drawWithImage(Graphics g,int _fakeX, int _fakeY){
       File sourceimage = new File("factory.png");
       Image image=null;
        try {
            image = image = ImageIO.read(sourceimage);
        } catch (IOException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
         g.drawImage(image, (int)(position.x-drawSize/2)+_fakeX, (int)(position.y-drawSize/2)+_fakeY,30,30, observer);
    }
    
    
}
