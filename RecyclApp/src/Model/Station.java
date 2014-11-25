/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.awt.Color;
import java.awt.Graphics;


/**
 *
 * @author Vincent
 */
public class Station extends PlantComponant
{
    public RecoveryMatrix recoveryMatrix;
    private int numberOfExitsConnected;
  
            
    public Station()
    {    
        this.setDescription("Station");
        this.recoveryMatrix = new RecoveryMatrix();
        this.numberOfExits = 0;
        this.planColor = Color.BLUE;
        this.numberOfExitsConnected = 0;
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
    public boolean addExitConnection(){
        if(numberOfExitsConnected < numberOfExits){
            this.numberOfExitsConnected++;
            return true;
        }
        else return false;
    }
    public RecoveryMatrix getMatrix(){
        return this.recoveryMatrix;
    }

    public void AddLineToMatrix(Material.MaterialType type, int exitNumber, float pourcentage) {
        this.recoveryMatrix.addLineToMatrix(type, exitNumber, pourcentage);
    }

    public void clearMatrix() {
        this.recoveryMatrix = new RecoveryMatrix();
    }
    
}
