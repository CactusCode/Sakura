/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.awt.Color;

/**
 *
 * @author Vale
 */
public class Junction extends PlantComponant
{
   
  
    public Junction()
    {
        this.setDescription("Jonction");
        this.planColor = Color.red;
        this.numberOfExits = 1;
    }
    
    
}
