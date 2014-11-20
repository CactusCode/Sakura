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
    
    
    
}
