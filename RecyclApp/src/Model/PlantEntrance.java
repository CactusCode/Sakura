/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.awt.Color;
/**
 *
 * @author Vincent
 */
public class PlantEntrance extends PlantComponant{
   
    public PlantEntrance ()
    {
        this.setDescription("Entrée Usine");
        this.planColor = Color.PINK;
        this.entranceIsConnected = true;
        this.numberOfExits = 1;
    }

     
 
}
